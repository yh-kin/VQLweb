package vql.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import type.Element;

public class FromInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(Collection<Element> fromInfoList){
		List<Map<String, Object>> fromInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(Element tableView : fromInfoList){
			Map<String, Object> fromInfoMap = InfoConverter.convertInfoToMap(tableView);
			fromInfoMapList.add(fromInfoMap);
		}
		
		return fromInfoMapList;
	}
}
