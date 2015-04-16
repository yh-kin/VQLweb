function _FromPainter (drawingPanel_param){
	if(drawingPanel_param == undefined || drawingPanel_param.length == 0){
		console.error("drawing_panel: [" + drawingPanel_param + "] is inappropriate!");
	}
	
	const STATEMENT_NAME = "from";
	
	const ELEMENT_INNER_MARGIN = 10;
	const ELEMENT_OUTER_MARGIN = 20;

	const MIN_ELEMENT_WIDTH = 100;
	const MAX_SELECT_WIDTH = 200;
	
	const ELEMENT_HEIGHT = 50;
	
	const ELEMENT_DEFAULT_OFFSET_X = 30;
	const ELEMENT_DEFAULT_OFFSET_Y = 10;

	var last_offset_x = ELEMENT_DEFAULT_OFFSET_X;
	var last_offset_y = ELEMENT_DEFAULT_OFFSET_Y;
	
	var drawingPanel = drawingPanel_param;
	
	this.getBlockEnd_y = function(){
		return last_offset_y;
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
		
		// TODO
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
		blockElement.offset({top: last_offset_y, left: last_offset_x});
		
		// TODO 임시
		$(blockElement).width(150);
		$(blockElement).height(height);
		
		drawingPanel.append(blockElement);
	}
	
	var paintInfo = function(info){
		var infoElement = $("<div class=\"element " + STATEMENT_NAME + "\"></div>");
		
		// add table name
		infoElement.append("<div>" + info.table_name + "</div>");
		
		// add column name
		infoElement.append("<div class=\"column\">" + info.column_name + "</div>");
		
		// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
		infoElement.offset({top: last_offset_y, left: last_offset_x});
		
		drawingPanel.append(infoElement);
		
		// set width
		var width = infoElement.width() + ELEMENT_INNER_MARGIN;
		if(width < MIN_ELEMENT_WIDTH){
			width = MIN_ELEMENT_WIDTH;
			
		} else if(width > MAX_SELECT_WIDTH){
			width = MAX_SELECT_WIDTH;
			
		}
		infoElement.width(width);
		
		
		// update last_offset x, y
		last_offset_x = last_offset_x + infoElement.width() + ELEMENT_OUTER_MARGIN;
	}
}