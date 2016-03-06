function _paintElement (infoElement, info){
	var paintingTechnique = undefined;
	
	switch(info.type){
	case "ColumnInfo":
		paintingTechnique = __column_DrawingTechnique;
		break;
		
	case "ConstInfo":
		paintingTechnique = __const_DrawingTechnique;
		break;
		
	case "FunctionInfo": // TODO 현재 임시 처리중..
		paintingTechnique = __function_DrawingTechnique;
		break;
		
	case "SubQueryInfo": // TODO 현재 임시 처리중..
		paintingTechnique = __subquery_DrawingTechnique;
		break;
		
	case "TableInfo":
		paintingTechnique = __table_DrawingTechnique;
		break;
		
	case "ConditionInfo":
		paintingTechnique = __condition_DrawingTechnique;
		break;
		
	case "WhereInfo":
		paintingTechnique = __where_DrawingTechnique;
		break;
	}
	
	if(paintingTechnique == undefined){
		console.error("INVALID Painting Type!! : [" + info.type + "]");
		
	}else{
		paintingTechnique(infoElement, info)
	}
}

// drawing Column Contents
function __column_DrawingTechnique(infoElement, info){
	// add table name
	$(infoElement).append("<div class=\"table_name\">" + info.table_name + "</div>");
	
	// add column name
	$(infoElement).append("<div class=\"column_name\">" + info.column_name + "</div>");
}

// drawing Constant Contents
function  __const_DrawingTechnique(infoElement, info){
	// add table name
	$(infoElement).append("<div class=\"type_name\">" + info.type_name + "</div>");
	
	// add column name
	$(infoElement).append("<div class=\"const_value\">" + info.const_value + "</div>");
}

// drawing Function Contents
// TODO 현재 function은 임시로 text그대로 보여주고 있음.
function __function_DrawingTechnique(infoElement, info){
	$(infoElement).append("<div class=\"function_text\">" + info.function_text + "</div>");
}

// drawing SubQuery Contents
function __subquery_DrawingTechnique(infoElement, info){
	// add subquery alias
	$(infoElement).append("<div class=\"alias\">" + info.alias + "</div>");
	
	// add subquery ID
	$(infoElement).append("<div class=\"subquery_id\">" + info.query_id + "</div>");
}


function __subquery_DrawingTechnique(infoElement, info){
	// add subquery alias
	$(infoElement).append("<div class=\"alias\">" + info.alias + "</div>");
	
	// add subquery ID
	$(infoElement).append("<div class=\"subquery_id\">" + info.query_id + "</div>");
}

function __table_DrawingTechnique(infoElement, info){
	// add subquery alias
	$(infoElement).append("<div class=\"alias\">" + info.alias + "</div>");
	
	// add subquery ID
	$(infoElement).append("<div class=\"table_name\">" + info.table_name + "</div>");
}

function __condition_DrawingTechnique(infoElement, info){
	var element = $("<div class=\"condition\"></div>");
	
	// add source element
	var sourceElement = $("<div class=\"source\"></div>");
	_paintElement(sourceElement, info.source);
	$(element).append(sourceElement);
	
	// add relational operation
	$(element).append("<div class=\"copr_op\">" + info.copr_op + "</div>");
	
	// add target element
	var targetElement = $("<div class=\"target\"></div>");
	_paintElement(targetElement, info.target);
	$(element).append(targetElement);
	
	$(infoElement).append(element);
}

function __where_DrawingTechnique(infoElement, info){
	// basic where div
	var element = $("<div class=\"lgcl_op " + info.lgcl_op.toLowerCase() + "\"></div>");
	
	$(element).append("<div class=\"name\">" + info.lgcl_op + "</div>"); // lgcl_op name
	$(element).append("<div class=\"cross_section\"></div>"); // empty div
	$(element).append("<div class=\"area\"></div>"); // lgcl_op area

	// condition info drawing_technique
	for(var i = 0; i < info.condition_list.length; i++){
		// area에 condition과 자식 whereInfo를 넣는다.
		_paintElement($(element).find(".area"), info.condition_list[i]);
	}
	
	$(infoElement).append(element);
}