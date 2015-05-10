function _SelectPainter (drawingPanel_param){
	if(drawingPanel_param == undefined || drawingPanel_param.length == 0){
		console.error("drawing_panel: [" + drawingPanel_param + "] is inappropriate!");
	}
	
	const STATEMENT_NAME = "select";
	
	const ELEMENT_INNER_MARGIN_X = 10;
	const ELEMENT_INNER_MARGIN_Y = 0;
	
	const ELEMENT_OUTER_MARGIN_X = 20;
	const ELEMENT_OUTER_MARGIN_Y = 20;
	
	const MIN_ELEMENT_WIDTH = 100;
	const MAX_SELECT_WIDTH = 200;
	
	const ELEMENT_HEIGHT = 40;

	const ELEMENT_DEFAULT_OFFSET_X = 30;

	var last_offset_x = ELEMENT_DEFAULT_OFFSET_X;
	var last_offset_y = 40;
	
	var blockElementEndPosition_Y = 0;
	
	var drawingPanel = drawingPanel_param;
	
	this.getBlockEndPosition_Y = function(){
		return blockElementEndPosition_Y;
	}
	
	this.paint = function(infoList, block_offset_x, block_offset_y){
		if(infoList == undefined){
			console.error(STATEMENT_NAME + " infoList: [" + infoList + "] is inappropriate!");
			
		}else if(infoList.length == 0){
			console.debug(STATEMENT_NAME + "infoList is EMPTY!");
		}
		
		// set offset
		last_offset_x = last_offset_x + block_offset_x;
		last_offset_y = last_offset_y + block_offset_y;
		
		for(var i = 0; i < infoList.length; i++){
			paintInfo(infoList[i]);
		}
		
		paint_statementBlock(block_offset_x, block_offset_y);
	}
	
	// SELECT Statement Block 그리기
	var paint_statementBlock = function(block_offset_x, block_offset_y){
		var blockElement = $("<div class=\"block " + STATEMENT_NAME + "\"></div>");

		// add block name
		blockElement.append("<div class=\"name\">" + STATEMENT_NAME.toUpperCase() + " statement</div>");

		// add empty space
		blockElement.append("<div class=\"space\"></div>");

		// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
		blockElement.offset({top: block_offset_y, left: block_offset_x});
		
		// 최종 Element offset(Y) - Block 시작 offset(Y) + Element Height + Element 외부 margin(Y)
		$(blockElement).height(last_offset_y - block_offset_y + ELEMENT_HEIGHT + ELEMENT_OUTER_MARGIN_Y);
		blockElementEndPosition_Y = block_offset_y + $(blockElement).height();
		
		drawingPanel.append(blockElement);
	}
	
	var paintInfo = function(info){
		var infoElement = $("<div class=\"element " + STATEMENT_NAME + "\"></div>");

		var drawer = undefined;
		switch(info.type){
		case "ColumnInfo":
			drawer = setContents_Column;
			break;
			
		case "ConstInfo":
			drawer = setContents_Const;
			break;
			
		case "FunctionInfo": // TODO 현재 임시 처리중..
			drawer = setContents_Function;
			break;
			
		case "SubQueryInfo": // TODO 현재 임시 처리중..
			drawer = setContents_Subquery;
			break;
			
		case "TableInfo":
			Console.error("SELECT statement CANNOT have TableInfo!!");
			break;
		}
		
		if(drawer == undefined){
			console.error("INVALID SelectInfo Type!!");
		}else{
			drawer(infoElement, info);
		}
		
		// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
		$(infoElement).offset({top: last_offset_y, left: last_offset_x});
		
		drawingPanel.append(infoElement);
		
		// set width
		var width = $(infoElement).width() + ELEMENT_INNER_MARGIN_X;
		if(width < MIN_ELEMENT_WIDTH){
			width = MIN_ELEMENT_WIDTH;
			
		} else if(width > MAX_SELECT_WIDTH){
			width = MAX_SELECT_WIDTH;
		}
		$(infoElement).width(width);
		$(infoElement).height(ELEMENT_HEIGHT);
		
		// re-positioning
		if(($(infoElement).position().left + width) >= $(drawingPanel).width()){
			last_offset_x = ELEMENT_DEFAULT_OFFSET_X;
			last_offset_y = $(infoElement).position().top + $(infoElement).height() + ELEMENT_OUTER_MARGIN_Y;
			
			var parentOffset = $(drawingPanel).offset();
			
			$(infoElement).offset({top: parentOffset.top + last_offset_y, left: parentOffset.left + last_offset_x});
		}
		
		// update last_offset x, y
		last_offset_x = last_offset_x + $(infoElement).width() + ELEMENT_OUTER_MARGIN_X;
	}
	
	// drawing Column Contents
	var setContents_Column = function(infoElement, info){
		// add table name
		$(infoElement).append("<div class=\"table_name\">" + info.table_name + "</div>");
		
		// add column name
		$(infoElement).append("<div class=\"column_name\">" + info.column_name + "</div>");
	}
	
	// drawing Constant Contents
	var setContents_Const = function(infoElement, info){
		// add table name
		$(infoElement).append("<div class=\"type_name\">" + info.type_name + "</div>");
		
		// add column name
		$(infoElement).append("<div class=\"const_value\">" + info.const_value + "</div>");
	}
	
	// drawing Function Contents
	// TODO 현재 function은 임시로 text그대로 보여주고 있음.
	var setContents_Function = function(infoElement, info){
		$(infoElement).append("<div class=\"function_text\">" + info.function_text + "</div>");
	}
	
	// drawing SubQuery Contents
	var setContents_Subquery = function(infoElement, info){
		// add subquery alias
		infoElement.append("<div class=\"alias\">" + info.alias + "</div>");
		
		// add subquery ID
		infoElement.append("<div class=\"subquery_id\">" + info.query_id + "</div>");
	}
}