package vql.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import type.Element;

public class SelectInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(Collection<Element> selectInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(Element queryComponent : selectInfoList){
			Map<String, Object> selectInfoMap = InfoConverter.convertInfoToMap(queryComponent);
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
}
