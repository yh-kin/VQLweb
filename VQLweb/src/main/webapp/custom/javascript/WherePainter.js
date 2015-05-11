function _WherePainter (drawingPanel_param){
	if(drawingPanel_param == undefined || drawingPanel_param.length == 0){
		console.error("drawing_panel: [" + drawingPanel_param + "] is inappropriate!");
	}
	
	const STATEMENT_NAME = "where";
	
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
	
	this.paint = function(info, block_offset_x, block_offset_y){
		if(info == undefined){
			console.error(STATEMENT_NAME + " info: [" + info + "] is inappropriate!");
			
		}else if(info.length == 0){
			console.debug(STATEMENT_NAME + "info is EMPTY!");
		}
		
		// inner element offset = last time offset(in this time, set initial value) + offset of block 
		last_offset_x = last_offset_x + block_offset_x;
		last_offset_y = last_offset_y + block_offset_y;
		
		paintInfo(info);
		
		paint_statementBlock(block_offset_x, block_offset_y);
	}
	
	// WHERE Statement Block 그리기
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
		var infoElement = $("<div class=\"condition_list " + STATEMENT_NAME + "\"></div>");

		if(info.type == "WhereInfo"){
			_paintElement(infoElement, info);
			
		}else{
			
		}
		
		// TODO 초기 positioning 하고 width 설정하고 다시 re-positioning하는거
		// refactoring이 필요할 듯.
		
		// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
		infoElement.offset({top: last_offset_y, left: last_offset_x});
		
		drawingPanel.append(infoElement);
		
		// set width
		var width = infoElement.width() + ELEMENT_INNER_MARGIN_X;
		if(width < MIN_ELEMENT_WIDTH){
			width = MIN_ELEMENT_WIDTH;
			
		} else if(width > MAX_SELECT_WIDTH){
			width = MAX_SELECT_WIDTH;
			
		}
		infoElement.width(width);
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
}