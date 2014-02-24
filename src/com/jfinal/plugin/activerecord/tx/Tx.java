package com.jfinal.plugin.activerecord.tx;

import java.sql.Connection;

import cn.com.jandar.action.admin.AdminBaseController;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.ActiveRecordException;
import com.jfinal.plugin.activerecord.DbKit;

/**
 * ActiveRecord declare transaction.
 * Example: @Before(Tx.class)
 */
public class Tx implements Interceptor {
	
	protected int getTransactionLevel() {
		return DbKit.getTransactionLevel();
	}
	
	public void intercept(ActionInvocation invocation) {
		if (DbKit.isExistsThreadLocalConnection())
			throw new ActiveRecordException("Nested transaction can not be supported. You can't execute transaction inside another transaction.");
		
		Connection conn = null;
		Boolean autoCommit = null;
		try {
			conn = DbKit.getConnection();
			autoCommit = conn.getAutoCommit();
			DbKit.setThreadLocalConnection(conn);
			conn.setTransactionIsolation(getTransactionLevel());	// conn.setTransactionIsolation(transactionLevel);
			conn.setAutoCommit(false);
			invocation.invoke();
			Controller con = invocation.getController();
			String tranflag = con.getAttr(AdminBaseController.TRANFLAG);
			if(!AdminBaseController.TRANERROR.equals(tranflag))
				conn.commit();
			else
				conn.rollback();
		} catch (Exception e) {
			if (conn != null)
				try {conn.rollback();} catch (Exception e1) {e1.printStackTrace();}
			throw new ActiveRecordException(e);
		}
		finally {
			try {
				if (conn != null) {
					if (autoCommit != null)
						conn.setAutoCommit(autoCommit);
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();	// can not throw exception here, otherwise the more important exception in previous catch block can not be thrown
			}
			finally {
				DbKit.removeThreadLocalConnection();	// prevent memory leak
			}
		}
	}
}
