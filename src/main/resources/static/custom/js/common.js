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

/*
 * 初始化tooltip 
 * @param direction （1：上，2：右，3：下，4：左）
 */
function initTips(direction){
	// 默认方向向下
	var dir = 3;
	if(direction){
		dir = direction;
	}
	
	// 绑定事件
	$(".open-event").mouseover(function(){
		var id = "#" + this.id;
		var msg = $(this).attr("data-title");
		layer.tips(msg, id, {
			 tips: [dir, '#0FA6D8'],
			 time: 100*1000
		});
	}).mouseout(function(){
		layer.closeAll();
	});
}

/*
 * 初始化日期控件
 */ 
function initDatePicker(){
	//绑定事件,显示年月
	$('.date-picker-monthonly').datepicker({
		minViewMode:1,
		language: GLOBAL_LANGUAGE,
		autoclose: true,
		todayHighlight: true
	})
	// 单击图标是触发事件
	.next().on(ace.click_event, function(){
		$(this).prev().focus();
	})
	;
	
	//开始日期失去焦点
	$(".startDate").blur(function(){
		if($(this).val()==null||$(this).val()==""){
			$(this).parent().find(".endDate").datepicker('setStartDate', null);
		}
	})
	//结束日期失去焦点
	$(".endDate").blur(function(){
		if($(this).val()==null||$(this).val()==""){
			$(this).parent().find(".startDate").datepicker('setEndDate', null);
		}
	})
	
	// 绑定事件,显示年月日
	$('.date-picker').datepicker({
		minViewMode:3,
		language: GLOBAL_LANGUAGE,
		autoclose: true,
		todayHighlight: true
	})
	.on('changeDate', function(){
		var parent=$(this).parent();
		if($(this).hasClass("startDate")){
			var endDateDom=parent.find(".endDate");
			var s_startDate=$(this).val();
			var s_endDate=endDateDom.val();
			if(s_startDate>s_endDate){
				endDateDom.val('');
			}
			endDateDom.datepicker('setStartDate', s_startDate);
		}
		if($(this).hasClass("endDate")){
			var startDateDom=parent.find(".startDate");
			var e_startDate=startDateDom.val();
			var e_endDate=$(this).val();
			if(e_startDate>e_endDate){
				startDateDom.val('');
			}
			startDateDom.datepicker('setEndDate', e_endDate);
		}
	})
	// 单击图标是触发事件
	.next().on(ace.click_event, function(){
		$(this).prev().focus();
	});
}

/*
 * 根据传入参数，转换Html在单元格内容
 * 
 * @param row1Content 第一行的内容
 * @param row2Content 第二行内容
 */ 
function getCellHtmlRow2Col1(row1Content, row2Content){
	return "  <div class='datatable-row1-col1'>" 
			+ row1Content
			+ "</div>"
			+ "<div class='datatable-row2-col1'>" 
			+ row2Content
			+ "</div>";
}

/*
 * 根据传入参数，转换Html在单元格内容
 * 
 * @param row1Col1Content 第一行第一列的内容
 * @param row1Col2Content 第一行第二列的内容
 * @param row2Content 第二行内容
 */ 
function getCellHtmlRow2Col2(row1Col1Content, row1Col2Content, row2Content){
	return "<div class='row no-padding background-transparent datatable-no-border'>" 
			+ "<div class=' datatable-row1-col1' style='width:100px'>"
			+ row1Col1Content
			+ "</div>"
			+ "<div class='col-xs-12 datatable-row1-col2'>" 
			+ row1Col2Content
			+ "</div>"
			+ "</div>"
			+ "<div class='row no-padding no-border background-transparent datatable-no-border'>" 
			+ "<div class='col-xs-12 datatable-row2-col1'>" 
			+ row2Content
			+ "</div>"
			+ "</div>";
}

//二选一checkbox
$(".search_checkbox").change(function(){
	   var flag=$(this).prop("checked");
	   $(".search_checkbox").each(function(){
		   $(this).prop("checked",false);
	   });
	   if(flag){
		   $(this).prop("checked",true);
	   }
})

//datatable宽度自适应计算
function dataTableWidthAuto(times){
	var headWidth=$("#contentTable_wrapper").width();//每页显示多少条DIV宽度
	var tableBodywidth=$(".dataTable").width();//table内容宽度
	if(Math.abs(headWidth-tableBodywidth) <=2 || headWidth > tableBodywidth){
		$(".dataTable").width(headWidth-2);
		
		if(!times) {
			dataTableWidthAuto(2);
		}
	} 
}
//datatable自动显示行号
function dataTableNoAuto(colIndex, api){
	var startIndex= api.context[0]._iDisplayStart;//获取到本页开始的条数
	api.column(colIndex).nodes().each(function(cell, i) {
		cell.innerHTML = startIndex + i + 1;
	}); 
}

//支付画面的验收年月检索条件默认为上月
function deliveryInit(){
	var date=new Date();
	var month=date.getMonth().toString().length<2?("0"+date.getMonth()):date.getMonth();
	var searchDate=date.getFullYear()+"-"+month;
	$('.monthonly').val(searchDate);
}

/*
 * 计算两个时间相差的天数
 * 
 * @param fromDate 开始时间
 * @param toDate 结束时间
 * @return days 相差天数
 */ 
function daysDiff(fromDate,toDate){
	var date = new Date(toDate).getTime() - new Date(fromDate).getTime();   //时间差的毫秒数 
	var days=Math.floor(date/(24*3600*1000));
	return days;
}

/*
 * 生成表格中的按钮的HTML
 * @param label 文字描述
 * @param color 颜色
 * @param customClass 额外绑定的Class
 * @param disabled 是否Disable
 * @return 生成的HTML
 */
function getGridButtonHtml(label, color, customClass, disabled) {
	var result = "<button type='button' " + (disabled ? " disabled " : "") + " style='margin-left:5px' class='btn btn-sm ";
	result += ((disabled ? " grid-btn-disable " : ("btn-white btn-" + color)))  +" "+customClass + "'>";
	result += (label + "</button>");
	return result;
}

/*
 * 生成表格中的Mini按钮的HTML
 * @param label 文字描述
 * @param color 颜色
 * @param customClass 额外绑定的Class
 * @param disabled 是否Disable
 * @return 生成的HTML
 */
function getGridMiniButtonHtml(label,color, customClass, disabled) {
	var result = "<button type='button' " + (disabled ? " disabled " : "") + " style='margin-left:5px' class='btn btn-minier";
	result += " btn-" + color  +" "+customClass + "'>";
	result += (label + "</button>");
	return result;
}

/*
 * 控制元素最大、最小宽度
 * 使用方法:元素class中添加custom-max-width-xx,custom-min-width-xx
 * 
 */
function widthController(){
	var maxDomArray=$.find("[class*='custom-max-width']");
	$.each(maxDomArray,function(){
		var classArray=$(this).attr("class").split(" ");
		if(classArray!=null){
			for (var i = 0; i < classArray.length; i++) {
				if(classArray[i].indexOf("custom-max-width")>-1){
					var attrArray=classArray[i].split("-");
					$(this).css("max-width",attrArray[3]+"px");
				}
			}
		}
	});
	var minDomArray=$.find("[class*='custom-min-width']");
	$.each(minDomArray,function(){
		var classArray=$(this).attr("class").split(" ");
		if(classArray!=null){
			for (var i = 0; i < classArray.length; i++) {
				if(classArray[i].indexOf("custom-min-width")>-1){
					var attrArray=classArray[i].split("-");
					$(this).css("min-width",attrArray[3]+"px");
				}
			}
		}
	});
}
/*
* 多选空间中移除选中项
* 
*/
$(document).on("click", ".tagClose", (function() {
	$(this).parent().remove();
}));

$(function(){
	 widthController()
})

/*
 * 页面大小变化后，dataTable宽度自适应
 */
$(window).resize(function () {
    setTimeout(function(){dataTableWidthAuto()}, 200);
});

/*
 * 日期格式化
 * new Date().format('YYYY-MM-dd')
 * new Date(db取得的日期格式数据).format('YYYY-MM-dd')
 */
Date.prototype.format = function(format){
	    var o = {
		"M+" : this.getMonth() + 1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter
		"S" : this.getMilliseconds()
	//millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}

  /*
   * 下载文件
   * @param ctx 上下文
   * @param url url
   * @param methodType 请求方式(GET/POST)
   * @param fileNotExitMsg 文件不存在Msg(直接在此js里用spring国际化标签没有作用)
   */
function commonDowndFile(path,saveFileName,realFileName){
	var checkUrl=ctx+"/download/fileExitCheck?path="+path+"&fileName="+saveFileName;
	var downloadUrl=ctx+"/download?path="+path+"&saveFileName="+saveFileName+"&realFileName="+realFileName;
	$.ajax({
		type : "POST",
		url : checkUrl,
		contentType : 'application/json;charset=utf-8', //设置请求头信息
		dataType : "json",
		success : function(data) {
			if(data=='1'){
				var form = $("<form>");
				form.attr('style','display:none');
				form.attr('class','increaseForm');
				form.attr('target','');
			    form.attr('method','POST');
			    form.attr('action',downloadUrl);
			    $('body').append(form);
			    form.submit();
			    $('body').remove(".increaseForm");
			}else{//文件不存在
				layer.alert(fileNotExitMsg);
			}
		}
	});
}

/*
 * .ajaxError事件定位到document对象，文档内所有元素发生ajax请求异常，
 * 都将冒泡到document对象的ajaxError事件执行处理
 */
$(document).ajaxError(
    //所有ajax请求异常的统一处理函数，处理(options,exc)
    function(event,xhr){
    	var status = xhr.status;
        if(status == 'undefined'){
            return;
        } else if (status == 403){
            // 未授权异常
        	layer.alert("系统拒绝：您没有访问权限。");
        } else if (status == 404){
            // 页面不存在
        	layer.alert("您访问的资源不存在。");
        } else {
            // 返回消息内容区分
            var rspTxt = xhr.responseText;
            if(rspTxt.indexOf("OptimisticCheckException") != -1){
            	layer.alert("当前操作记录已经被更新过，请刷新页面重试。");
            } else if(rspTxt.indexOf("AlreadyExistCheckException") != -1){
            	layer.alert(rspTxt.substring(rspTxt.indexOf("{") + 1, rspTxt.lastIndexOf("}")));
            } else {
            	layer.alert("发生系统异常，请重试或者联系系统管理员");
            }
        }
    }
);

/**
 * 上传插件初始化
 * @className 需要初始化的class名
 * @noFileStr 初始化时控件显示的文言
 * @initDom 指定需要初始化的Dom
 * @beforeRemoveFuc 移除文件的回调方法
 */
function uploadInit(className,noFileStr,initDom,beforeRemoveFuc){
	var noFile=(noFileStr==null||noFileStr=="")?"没有选择文件":noFileStr;
	var inputFileDom;
	if(className!=null&&className!=""){
		inputFileDom=$("."+className);
	}else{
		inputFileDom=initDom;
	}
	if(beforeRemoveFuc==null){
		beforeRemoveFuc=function(){
			var form=inputFileDom.parent().parent();
			form.attr("tempFileName","");
			form.attr("fileShowName","");
			return true;
		}
	}
	var option = {
		no_file : noFile,
		btn_choose : '选择文件',
		btn_change : '重选',
		droppable : false,
		onchange : null,
		thumbnail : 'small',
		before_remove : beforeRemoveFuc
	  }
	inputFileDom.ace_file_input(option);
}

/**
 * 自定义validate方法。（整数或者两位X位小数，无需调用）
 * <p>用法validate[funcCall[numberLimit[2]]</p>
 * @param field
 * @param rules
 * @param i
 * @param options
 * @returns
 */
function numberLimit(field, rules, i, options){
	var max = rules[i + 2];
	var reg =new RegExp("/^0{1}([.]\\d{1,"+max+"})?$|^[1-9]\\d*([.]{1}[0-9]{1,"+max+"})?$/");
	  if (!reg.test(field.val())) {
	    return options.allrules.numberLimit.alertText+max+options.allrules.numberLimit.alertText2;
	  }
}

/**
 * datable行选中，控制checkbox
 * @callBackFunction 回调函数
 */
function dataTableTrClick(callBackFunction){
	$(".dataTables_scrollBody").find("tbody").find("tr").each(function(){
		$(this).unbind();
	})
	
	$(".dataTables_scrollBody").find("tbody").find("tr").click(function(){
		var checkbox=$(this).find("td").eq(0).find("input[type='checkbox']");
		if(checkbox.is(":checked")){
			checkbox.prop("checked",false);
		}else{
			if(!checkbox.attr("disabled")){
				checkbox.prop("checked",true);
			}
		}
		if(callBackFunction!=null){
			callBackFunction(checkbox);
		}
		
	})
	$(".dataTables_scrollBody").find("tbody").find("tr").each(function(){
		$(this).find("input").click(function(e){
			e.stopPropagation();
		});
		$(this).find("input").parent().click(function(e){
			e.stopPropagation();
		});
		
	})
	
}

/**
 * 文字转数字
 * @param str 字符
 * @param digit 保留的小数位数
 * @returns 数字
 */
function stringToNumber(str,digit){
	if(str==null||str==""){
		return 0;
	}
	var num=parseFloat(str);
	if( typeof num=="number"&&!isNaN(num)){
		return parseFloat(num.toFixed(digit));
	}else{
		return 0;
	}
}

/**
 * 下载
 * @param className 下载class名
 */
function downloadInit(className){
	//下载
    $("."+className).click(function () {
        var path = $(this).attr("filePath");
        var saveFileName = $(this).attr("saveFileName");
        var realFileName = $(this).attr("realFileName");
        commonDowndFile(path, saveFileName, realFileName);
    })
}

/**
 * 判断字符串是否为空
 * @param str
 */
function isNotNullAndEmpty(str){
    if(str==null||str==""||str.trim()==""){
    	return false;
    }
    return true;
}
