package vql.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queryParser.vo.SubQueryInfo;
import queryParser.vo.TableInfo;
import queryParser.vo.TableViewType;

public class FromInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(List<TableViewType> fromInfoList){
		List<Map<String, Object>> fromInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(TableViewType tableView : fromInfoList){
			Map<String, Object> fromInfoMap = null;
			
			// Contents
			switch(tableView.getClass().getSimpleName()){
			case "SubQueryInfo":
			case "TableInfo":
				fromInfoMap = InfoConverter.convertInfoToMap(tableView);
				break;
			}
			
			fromInfoMapList.add(fromInfoMap);
		}
		
		return fromInfoMapList;
	}
}
