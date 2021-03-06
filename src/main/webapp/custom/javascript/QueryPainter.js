function _QueryPainter(drawingPanel_ID){
	if(drawingPanel_ID == undefined || drawingPanel_ID.length == 0){
		console.error("drawing_panel: [" + drawingPanel_ID + "] is inappropriate!");
	}
	
	const STATEMENT_MARGIN = 10;
	
	var offset_x = 0;
	var offset_y = 0;
	
	var drawingPanel = $("#" + drawingPanel_ID);
	
	var selectPainter = new _SelectPainter(drawingPanel);
	var fromPainter = new _FromPainter(drawingPanel);
	var wherePainter = new _WherePainter(drawingPanel);

	this.paint = function(queryInfo){
		if(queryInfo == undefined){
			console.error("queryInfo: [" + queryInfo + "] is inappropriate!");
		}
		
		selectPainter.paint(queryInfo.select);
		
		fromPainter.paint(queryInfo.from);
		
		if(queryInfo.where != undefined && queryInfo.where.length == 1){
			wherePainter.paint(queryInfo.where[0]);
		}

		if(queryInfo.where_info != undefined){
			wherePainter.paint(queryInfo.where_info);
		}
	}
}