package vql.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import type.Element;
import vo.query.ElementSet;

public class SelectInfoConverter {
	public static List<Map<String, Object>> convertInfoToMap(ElementSet selectInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(Element queryComponent : selectInfoList.get()){
			Map<String, Object> selectInfoMap = InfoConverter.convertInfoToMap(queryComponent);
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
}
