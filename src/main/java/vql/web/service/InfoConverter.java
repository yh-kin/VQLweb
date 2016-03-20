package vql.web.service;

import java.util.HashMap;
import java.util.Map;

import condition.Condition;
import element.Attribute;
import element.Constant;
import type.Element;

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
			
		case CONDITION:
			Condition condition = (Condition)obj;
			
			infoMap.put("source", convertInfoToMap(condition.getOperand1())); // TODO 이름 operand1로 바꾸기
			infoMap.put("copr_op", condition.getOperator().getSymbol()); // TODO 이름 operator로 바꾸기
			infoMap.put("target", convertInfoToMap(condition.getOperand2())); // TODO 이름 operand1로 바꾸기
			
			break;
		}
		
		return infoMap;
	}
}
