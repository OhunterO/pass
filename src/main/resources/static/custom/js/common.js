/*
 * 表格组件中的checkbox全选
 */
function checkAllTable(obj){
	var checkFlag = $(obj).prop("checked");
	$(".table-checkbox").each(function(){
		if(!$(this).attr("disabled")){
			$(this).prop("checked", checkFlag);
		}
	})
}

//datatable自动显示行号
function dataTableNoAuto(colIndex, api){
	var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
	api.column(colIndex).nodes().each(function(cell, i) {
		cell.innerHTML = startIndex + i + 1;
	}); 
}
