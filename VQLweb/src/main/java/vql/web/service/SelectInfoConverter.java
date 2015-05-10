package vql.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queryParser.vo.ColumnInfo;
import queryParser.vo.ConstInfo;
import queryParser.vo.FunctionInfo;
import queryParser.vo.QueryComponentType;
import queryParser.vo.SubQueryInfo;

public class SelectInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(List<QueryComponentType> selectInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(QueryComponentType queryComponent : selectInfoList){
			Map<String, Object> selectInfoMap = null;
			
			// Contents
			switch(queryComponent.getClass().getSimpleName()){
			case "ColumnInfo":
			case "ConstInfo":
			case "FunctionInfo":
			case "SubQueryInfo":
				selectInfoMap = InfoConverter.convertInfoToMap(queryComponent);
				break;
			
			case "TableInfo":
				// TODO SELECT info에서는 TableInfo가 올 수 없다.
				break;
			}
			
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
}
