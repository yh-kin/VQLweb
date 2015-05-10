package vql.web.service;

import java.util.HashMap;
import java.util.Map;

import queryParser.vo.ColumnInfo;
import queryParser.vo.ConditionInfo;
import queryParser.vo.ConstInfo;
import queryParser.vo.FunctionInfo;
import queryParser.vo.SubQueryInfo;
import queryParser.vo.TableInfo;
import queryParser.vo.WhereInfo;

public class InfoConverter {
	public static Map<String, Object> convertInfoToMap(Object obj){
		Map<String, Object> infoMap = new HashMap<String, Object>();
		
		// Column type
		infoMap.put("type", obj.getClass().getSimpleName());
		
		// Contents
		switch(obj.getClass().getSimpleName()){
		case "ColumnInfo":
			ColumnInfo columnInfo = (ColumnInfo)obj;
			
			infoMap.put("table_name", columnInfo.getTableName());
			infoMap.put("column_name", columnInfo.getColumnName());
			break;
			
		case "ConstInfo":
			ConstInfo constInfo = (ConstInfo)obj;
			
			infoMap.put("type_name", constInfo.getTypeName());
			infoMap.put("const_value", constInfo.getConstValue());
			break;
			
		case "FunctionInfo":
			FunctionInfo functionInfo = (FunctionInfo)obj;
			
			// TODO Function은 functionText 그대로 보여줄 것인가?
			infoMap.put("function_text", functionInfo.getFunctionText());
			break;
			
		case "SubQueryInfo":
			SubQueryInfo subQueryInfo = (SubQueryInfo)obj;
			
			infoMap.put("query_id", subQueryInfo.getCurrentQueryId());
			infoMap.put("alias", subQueryInfo.getAlias());
			break;
			
		case "TableInfo":
			TableInfo tableInfo = (TableInfo)obj;
			
			infoMap.put("table_name", tableInfo.getTableName());
			infoMap.put("alias", tableInfo.getAlias());
			break;
			
		case "ConditionInfo":
			ConditionInfo conditionInfo = (ConditionInfo)obj;
			
			infoMap.put("source", convertInfoToMap(conditionInfo.getSourceValue()));
			infoMap.put("copr_op", conditionInfo.getComparisionOp().toString());
			infoMap.put("target", convertInfoToMap(conditionInfo.getTargetValue()));
			break;
			
		case "WhereInfo":
			WhereInfo whereInfo = (WhereInfo)obj;
			
			infoMap.put("lgcl_op", whereInfo.getLogicalOp());
			break;
		}
		
		return infoMap;
	}
}
