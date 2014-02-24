package cn.com.jandar.plugin;

import java.util.Date;

import it.sauronsoftware.cron4j.Scheduler;

import com.jfinal.plugin.IPlugin;

public class SchedulerPlugin implements IPlugin {

	private Scheduler scheduler = new Scheduler();
	/**
	 * 
	 */
	public boolean start() {
		//每分钟一次
		scheduler.schedule("* * * * *", new ReadMenuTask());
		scheduler.start();
		return true;
	}

	public boolean stop() {
		scheduler.stop();
		return true;
	}

}

class ReadMenuTask implements Runnable {
//	public static boolean createMenuForDb = true;

	public void run() {
		System.out.println("定时器开始了"+new Date().getMinutes());
//		if(createMenuForDb){
//		//数据库读取菜单
//		List<Record> list = Db.find("select * from TK_CARD ORDER BY TO_NUMBER(ID)");
//		Map<String,Record> map = new HashMap<String,Record>();
//		for (Iterator<Record> iterator = list.iterator(); iterator.hasNext();) {
//		Record menu = iterator.next();
//		map.put(menu.getStr("CODE"), menu);
//		}
//		PubComm.mapMenu = map;
//		File file = new File(PubComm.realUrl);
//		if(file.exists()){
//		file.delete();
//		}
//		//生成文件
//		FileKit.writeStaticFile(PubComm.createMenu(list), PubComm.realUrl, "utf-8");
//		createMenuForDb = false;
//		}
	}


}
