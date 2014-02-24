var editor;
function getSimpleKE_NoImage() {
	editor = KindEditor.create('textarea[id="content"]', {
		resizeType : 1,
		autoHeightMode : true,
		afterCreate : function() {
			this.loadPlugin('autoheight');
		},

		afterBlur : function() {
			this.sync();
		},

		allowPreviewEmoticons : false,
		allowImageUpload : false,
		items : [ 'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
				'hilitecolor', 'bold', 'italic', 'underline', 'removeformat',
				'|', 'justifyleft', 'justifycenter', 'justifyright',
				'insertorderedlist', 'insertunorderedlist', '|', 'link' ]
	});
}


function getEditor(id) {
		KindEditor.ready(function(K) {
			editor = K.create(id,{
				resizeType : 1,
				autoHeightMode : true,
				afterCreate : function() {
					this.loadPlugin('autoheight');
				},
				afterBlur : function() {
					this.sync();
				},
				
				allowFileManager : true,
				allowPreviewEmoticons : true,
				allowImageUpload : true,
				items : [
				         'undo', 'redo', '|', 'preview', 'print', 'template', 'cut', 'copy', 'paste',
				         'plainpaste', 'wordpaste', '|', 'justifyleft', 'justifycenter', 'justifyright',
				         'justifyfull', 'insertorderedlist', 'insertunorderedlist', 'indent', 'outdent', 'subscript',
				         'superscript', 'clearhtml', 'quickformat', 'selectall', '|', 'fullscreen', '/',
				         'formatblock', 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold',
				         'italic', 'underline', 'strikethrough', 'lineheight', 'removeformat', '|', 'image', 'multiimage',
				         'flash', 'media', 'insertfile', 'table', 'hr', 'emoticons', 'baidumap', 'pagebreak',
				         'anchor', 'link', 'unlink', '|'
				        ]
			});
		});
}

function showEditDialog(obj) {
	var type = $(obj).attr("name");
	$("#dialogForm").attr("action",
			"content!updOrganization.action?flag=" + type);
	var title = "";
	if (type == "jigou")
		title = "修改组织概况-机构";
	else if (type == "zhangch")
		title = "修改组织概况-章程";
	$('#dialog_div').dialog(
			{
				title : title,
				width : 1000,
				modal : true,
				open : function(event, ui) {
					// 打开Dialog后创建编辑器
					editor = KindEditor.create('textarea[id="content"]', {
						resizeType : 1,
						autoHeightMode : true,
						afterCreate : function() {
							this.loadPlugin('autoheight');
						},

						afterBlur : function() {
							this.sync();
						},

						allowPreviewEmoticons : false,
						allowImageUpload : false,
						items : [ 'formatblock', 'fontname', 'fontsize', '|',
								'forecolor', 'hilitecolor', 'bold', 'italic',
								'underline', 'removeformat', '|',
								'justifyleft', 'justifycenter', 'justifyright',
								'insertorderedlist', 'insertunorderedlist',
								'|', 'image', 'link' ]
					});
					editor.html($("#" + type + "_div").html());
				},
				beforeClose : function(event, ui) {
					// 关闭Dialog前移除编辑器
					KindEditor.remove('textarea[id="content"]');
					window.location.reload();
				}
			});
}
function dialogSubmit() {
	var form = $("#dialogForm");
	$("#content").val(editor.html());
	$.ajax({
		url : form.attr('action'),
		type : form.attr('method'),
		data : form.serialize(),
		dataType : "json",
		success : function(data) {
			$("#dialog_div").dialog("close");
		},
		error : function() {
			$("#dialog_div").dialog("close");
		}
	});
}
function closeDialog() {
	$("#dialog_div").dialog("close");
}

