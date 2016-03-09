function _SelectPainter (drawingPanel_param){
	if(drawingPanel_param == undefined || drawingPanel_param.length == 0){
		console.error("drawing_panel: [" + drawingPanel_param + "] is inappropriate!");
	}
	
	const STATEMENT_NAME = "select";
	
	var drawingPanel = drawingPanel_param;
	
	// "PUBLIC" drawing block and element
	this.paint = function(infoList){
		if(infoList == undefined){
			console.error(STATEMENT_NAME + " infoList: [" + infoList + "] is inappropriate!");
			
		}else if(infoList.length == 0){
			console.debug(STATEMENT_NAME + "infoList is EMPTY!");
		}
		
		var blockElement = paint_statementBlock();
		
		for(var i = 0; i < infoList.length; i++){
			paintInfo($(blockElement).find(".space"), infoList[i]);
		}
		
		drawingPanel.append(blockElement);
	}
	
	// "PRIVATE" painting SELECT Statement Block
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
		var infoElement = $("<div class=\"element\"></div>");

		_paintElement(infoElement, info);
		blockElement.append(infoElement);
	}
}