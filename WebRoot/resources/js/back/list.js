/**
 * 调用后台批量删除方法
 */
function delelteBatch(basePath) {
	$("#mainForm").attr("action", basePath+"DeleteBatchServlet.action");
	$("#mainForm").submit();
}