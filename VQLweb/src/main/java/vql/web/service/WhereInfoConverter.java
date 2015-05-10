package vql.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queryParser.vo.ConditionInfo;
import queryParser.vo.WhereInfo;
import queryParser.vo.WhereType;

public class WhereInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(WhereInfo whereInfo){
		List<Map<String, Object>> whereInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(WhereType whereType: whereInfo.getValueList()){
			Map<String, Object> whereInfoMap = null;
			
			// Contents
			switch(whereType.getClass().getSimpleName()){
			case "ConditionInfo":
			case "WhereInfo":
				whereInfoMap = InfoConverter.convertInfoToMap(whereType);
				break;
			}
		}
	
		return whereInfoMapList;
	}
}
