package vql.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import type.Element;
import vo.query.ElementSet;

public class FromInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(ElementSet fromInfoList){
		List<Map<String, Object>> fromInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(Element tableView : fromInfoList.get()){
			Map<String, Object> fromInfoMap = InfoConverter.convertInfoToMap(tableView);
			fromInfoMapList.add(fromInfoMap);
		}
		
		return fromInfoMapList;
	}
}
