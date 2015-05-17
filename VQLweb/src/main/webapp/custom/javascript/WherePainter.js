function _WherePainter (drawingPanel_param){
	if(drawingPanel_param == undefined || drawingPanel_param.length == 0){
		console.error("drawing_panel: [" + drawingPanel_param + "] is inappropriate!");
	}
	
	const STATEMENT_NAME = "where";

	var drawingPanel = drawingPanel_param;
	
	// "PUBLIC" drawing block and element
	this.paint = function(info){
		if(info == undefined){
			console.error(STATEMENT_NAME + " infoList: [" + info + "] is inappropriate!");
			
		}else if(info.length == 0){
			console.debug(STATEMENT_NAME + "infoList is EMPTY!");
		}
		
		var blockElement = paint_statementBlock();
		
		paintInfo($(blockElement).find(".space"), info);
		
		drawingPanel.append(blockElement);
	}
	
	// "PRIVATE" painting FROM Statement Block
	var paint_statementBlock = function(){
		var blockElement = $("<div class=\"block " + STATEMENT_NAME + "\"></div>");

		// add block name
		blockElement.append("<div class=\"name\">" + STATEMENT_NAME.toUpperCase() + " statement</div>");

		// add empty space
		blockElement.append("<div class=\"space\"></div>");

		return blockElement;
	}
	
	// "PRIVATE" painting individual element
	var paintInfo = function(blockElement, info){
		switch(info.type){
		case "WhereInfo":
		case "ConditionInfo":
			_paintElement(blockElement, info);
			break;
		}
	}
}