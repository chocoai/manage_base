pie = {
	base: "",
	currencySign: "￥",
	currencyUnit: "元",
	priceScale: "2",
	priceRoundType: "roundHalfUp",
	orderScale: "2",
	orderRoundType: "roundHalfUp"
};

// 编辑器
//if(typeof(KE) != "undefined") {
//	KE.show({
//		id: "editor",
//		imageUploadJson: '../jsp/upload_json.jsp',
//		fileManagerJson: '../jsp/file_manager_json.jsp',
//		allowFileManager: true
//	});
//};
//var editor;
//KindEditor.ready(function(K) {
//        editor = K.create('#editor', {
//				uploadJson: '/template/common/editor/jsp/upload_json.jsp',
//        		fileManagerJson: '/template/common/editor/jsp/file_manager_json.jsp',
//        		allowFileManager: true,
//        		afterBlur: function(){this.sync();}
//        });
//});
$(function() {
	//给id为editor的textarea标签添加kindEditor编辑器
	getEditor("#editor");
});

$().ready( function() {

	/* ---------- List ---------- */
	
	var $listForm = $("#listForm");// 列表表单
	if ($listForm.size() > 0) {
		var $searchButton = $("#searchButton");// 查找按钮
		var $showAllButton = $("#showAllButton");// 显示全部按钮
		var $allCheck = $(".listTable input.allCheck");// 全选复选框
		var $idsCheck = $(".listTable input[name='ids']");// ID复选框
		var $deleteButton = $("#deleteButton");// 删除按钮
		var $searchText=$("#searchText");//搜索框
		var $pageNumber = $("#pageNumber");// 当前页码
		var $pageSize = $("#pageSize");// 每页显示数
		var $sort = $(".listTable .sort");// 排序
		var $orderBy = $("#orderBy");// 排序方式
		var $order = $("#order");// 排序字段
		var $pagesLi = $(".list #pager .pages li");// 排序字段
		
		// 全选
		$allCheck.click( function() {
			if ($(this).attr("checked") == true) {
				$idsCheck.attr("checked", true);
				var $idsChecked = $(".listTable input[name='ids']:checked");
				if($idsChecked.size()>0)
				$deleteButton.attr("disabled", false);
			} else {
				$idsCheck.attr("checked", false);
				$deleteButton.attr("disabled", true);
			}
		});
		
		// 无复选框被选中时,删除按钮不可用
		$idsCheck.click( function() {
			var $idsChecked = $(".listTable input[name='ids']:checked");
			if ($idsChecked.size() > 0) {
				$deleteButton.attr("disabled", "");
			} else {
				$deleteButton.attr("disabled", true)
			}
		});
		
		// 批量删除
		$deleteButton.click( function() {
			var url = $(this).attr("url");
			var $idsCheckedCheck = $(".listTable input[name='ids']:checked");
			$.dialog({type: "warn", content: "您确定要删除吗？", ok: "确 定", cancel: "取 消", modal: true, okCallback: batchDelete});
			function batchDelete() {
				$.ajax({
					url: url,
					data: $idsCheckedCheck.serialize(),
					dataType:"json",
					success: function(data) {
						if (data.status == "success") {
							$idsCheckedCheck.parent().parent().remove();
						}
						$deleteButton.attr("disabled", true);
						$allCheck.attr("checked", false);
						$idsCheckedCheck.attr("checked", false);
						$listForm.submit();
						$.message({type: "success", content: "删除成功！"});
					},
					error:function(){
						$.message({type: "error", content: "删除失败！提交出错！"});
					}
				});
			}
		});
	
		// 查找
		$searchButton.click( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
		// 查找
		$showAllButton.click( function() {
			$searchText.val("");
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 每页显示数
		$pageSize.change( function() {
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序
		$sort.click( function() {
			var $currentOrderBy = $(this).attr("name");
			if ($orderBy.val() == $currentOrderBy) {
				if ($order.val() == "") {
					$order.val("asc")
				} else if ($order.val() == "desc") {
					$order.val("asc");
				} else if ($order.val() == "asc") {
					$order.val("desc");
				}
			} else {
				$orderBy.val($currentOrderBy);
				$order.val("asc");
			}
			$pageNumber.val("1");
			$listForm.submit();
		});
	
		// 排序图标效果
		if ($orderBy.val() != "") {
			$sort = $(".listTable .sort[name='" + $orderBy.val() + "']");
			if ($order.val() == "asc") {
				$sort.removeClass("desc").addClass("asc");
			} else {
				$sort.removeClass("asc").addClass("desc");
			}
		}
		
		// 页码跳转
		$pagesLi.click(function(){
			if(!$(this).attr("title")==""){
				$pageNumber.val($(this).attr("title"));
				$listForm.submit();
			}
		});
		$.gotoPage = function(id) {
			$pageNumber.val(id);
			$listForm.submit();
		}
	}
	
	/* ---------- 菜单 ---------- */
	var $headMenu = $(".menu li a");// 一级菜单
	$headMenu.click(function() {
		if(self!=top){
			var menuContent=$(this).next().html();
			var topMenuDiv=$(top.menuFrame.document.getElementById("menuContent"));
			//修改二级菜单内容
			topMenuDiv.html(menuContent);
		}else{
			alert("当前页面不在框架中");
			window.location.href="/admin/index";
		}
	});
	
	
	/* ---------- Validate ---------- */
		
	var $validateForm = $("#validateForm");
	if ($validateForm.size() > 0) {
		$validateForm.validate({
			errorClass: "validateError",
			ignore: ".ignoreValidate",
			errorPlacement: function(error, element) {
				var messagePosition = element.metadata().messagePosition;
				if("undefined" != typeof messagePosition && messagePosition != "") {
					var $messagePosition = $(messagePosition);
					if ($messagePosition.size() > 0) {
						error.insertAfter($messagePosition).fadeOut(300).fadeIn(300);
					} else {
						error.insertAfter(element).fadeOut(300).fadeIn(300);
					}
				} else {
					error.insertAfter(element).fadeOut(300).fadeIn(300);
				}
			},
			submitHandler: function(form) {
				$(form).find(":submit").attr("disabled", true);
				form.submit();
			}
		});
	}
	
	
});