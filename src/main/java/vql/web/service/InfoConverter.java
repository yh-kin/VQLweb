package vql.web.service;

import java.util.HashMap;
import java.util.Map;

import type.Element;
import vo.element.Attribute;
import vo.element.Constant;

public class InfoConverter {
	public static Map<String, Object> convertInfoToMap(Element obj){
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		// Column type
		infoMap.put("type", obj.getType().name());
		
		// Contents
		switch(obj.getType()){
		case ATTRIBUTE:
			Attribute columnInfo = (Attribute)obj;
			
			// TODO 일단 바로 위 parent만
			infoMap.put("table_name", columnInfo.getParent().getName());
			infoMap.put("column_name", columnInfo.getName());
			break;
			
		case CONSTANT:
			Constant constInfo = (Constant)obj;
			
			infoMap.put("const_value", constInfo.getValue());
			break;
		}
		
		return infoMap;
	}
}
