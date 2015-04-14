var _drawing_visual_panel;

var _drawing_SELECT_INNER_MARGIN = 10;
var _drawing_SELECT_OUTER_MARGIN = 20;

var _drawing_MIN_SELECT_WIDTH = 100;
var _drawing_MAX_SELECT_WIDTH = 200;

var _drawing_last_offset_x = 30;
var _drawing_last_offset_y = 30;

function drawing_QueryInfo(canvasId, queryInfo){
	_drawing_visual_panel = $("#" + canvasId);
	
	if (visual_panel.length == 0){
		console.error("There is NO visual panel!");
		return;
	} else if(visual_panel > 1){
		console.error("There is MORE THAN ONE visual panel");
		return;
	}
	
	if(queryInfo == undefined){
		console.error("This is NOT CORRECT query info: undefined");
		
	} else{
		var select_info_list = queryInfo.select_info_list;
		
		if(select_info_list != "undefined" && select_info_list.length > 0){
			for(var i = 0; i < select_info_list.length; i++){
				drawing_SelectInfo(select_info_list[i]);
			}
			
			// TODO block-info를 그리는 방법을 고민해 볼 것.
		}
	}
}

function drawing_statementBlock(blockName){
	var blockElement = $("<div class=\"block select\"></div>");

	// add block name
	blockElement.append("<div class=\"name\">" + blockName + "</div>");

	// add empty space
	blockElement.append("<div class=\"space\"></div>");

	// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
	blockElement.offset({top: _drawing_last_offset_y, left: _drawing_last_offset_x});
	
	blockElement.width(width);
	blockElement.height(height);
	
	_drawing_visual_panel.append(blockElement);
}

function drawing_SelectInfo(selectInfo){
	var select_element = $("<div class=\"element select\"></div>");
	
	// add table name
	select_element.append("<div>" + selectInfo.table_name + "</div>");
	
	// add column name
	select_element.append("<div class=\"column\">" + selectInfo.column_name + "</div>");
	
	// 왜인지는 모르지만 append하기 전에 offset을 설정해야 제대로 먹힘.
	select_element.offset({top: _drawing_last_offset_y, left: _drawing_last_offset_x});
	
	_drawing_visual_panel.append(select_element);
	
	// set width
	var width = select_element.width() + _drawing_SELECT_INNER_MARGIN;
	if(width < _drawing_MIN_SELECT_WIDTH){
		width = _drawing_MIN_SELECT_WIDTH;
		
	} else if(width > _drawing_MAX_SELECT_WIDTH){
		width = _drawing_MAX_SELECT_WIDTH;
		
	}
	select_element.width(width);
	
	// update _drawing_last_offset x, y
	_drawing_last_offset_x = _drawing_last_offset_x + select_element.width() + _drawing_SELECT_OUTER_MARGIN;
}