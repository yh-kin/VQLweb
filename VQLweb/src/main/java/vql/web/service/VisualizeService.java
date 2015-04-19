package vql.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queryParser.executor.Executor;
import queryParser.vo.ColumnInfo;
import queryParser.vo.ConstInfo;
import queryParser.vo.FunctionInfo;
import queryParser.vo.QueryComponentType;
import queryParser.vo.QueryFactory;
import queryParser.vo.QueryInfo;
import queryParser.vo.SubQueryInfo;
import queryParser.vo.TableInfo;
import queryParser.vo.TableViewType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VisualizeService {
	public QueryFactory getVisualQueryInfo(String queryString) throws Exception{
		QueryFactory queryFactory = Executor.execute(queryString);
		
		return queryFactory;
	}
	
	public String convertQueryInfoToMap(QueryInfo queryInfo) throws JsonProcessingException{
		Map<String, List<Map<String, Object>>> queryInfoMap = new HashMap<String, List<Map<String, Object>>>();
		
		// SELECT
		List<Map<String, Object>> selectInfoMap = this.convertSelectInfoToMap(queryInfo.getSelectStmtInfo());
		queryInfoMap.put("select_info_list", selectInfoMap);
		
		// FROM
		List<Map<String, Object>> fromInfoMap = this.convertFromInfoToMap(queryInfo.getFromStmtInfo()); 
		queryInfoMap.put("from_info_list", fromInfoMap);

		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(queryInfoMap);
	}
	
	private List<Map<String, Object>> convertSelectInfoToMap(List<QueryComponentType> selectInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(QueryComponentType queryComponent : selectInfoList){
			Map<String, Object> selectInfoMap = new HashMap<String, Object>();
			
			// Column type
			selectInfoMap.put("type", queryComponent.getClass().getSimpleName());
			
			// Contents
			switch(queryComponent.getClass().getSimpleName()){
			case "ColumnInfo":
				ColumnInfo columnInfo = (ColumnInfo)queryComponent;
				
				selectInfoMap.put("table_name", columnInfo.getTableName());
				selectInfoMap.put("column_name", columnInfo.getColumnName());
				break;
				
			case "ConstInfo":
				ConstInfo constInfo = (ConstInfo)queryComponent;
				
				selectInfoMap.put("type_name", constInfo.getTypeName());
				selectInfoMap.put("const_value", constInfo.getConstValue());
				break;
				
			case "FunctionInfo":
				FunctionInfo functionInfo = (FunctionInfo)queryComponent;
				
				// TODO Function은 functionText 그대로 보여줄 것인가?
				selectInfoMap.put("function_text", functionInfo.getFunctionText());
				break;
				
			case "SubQueryInfo":
				break;
				
			case "TableInfo":
				// TODO SELECT info에서는 TableInfo가 올 수 없다.
				break;
			}
			
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
	
	private List<Map<String, Object>> convertFromInfoToMap(List<TableViewType> fromInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(TableViewType tableView : fromInfoList){
			Map<String, Object> selectInfoMap = new HashMap<String, Object>();
			
			// Column type
			selectInfoMap.put("type", tableView.getClass().getSimpleName());
			
			// Contents
			switch(tableView.getClass().getSimpleName()){
			case "SubQueryInfo":
				SubQueryInfo subQueryInfo = (SubQueryInfo)tableView;
				
				// SubQuery ID 대신 SubQuery Description 이라던가.. 표시할만한 내용을 추가할 것.
				selectInfoMap.put("query_id", subQueryInfo.getCurrentQueryId());
				selectInfoMap.put("alias", subQueryInfo.getAlias());
				break;
				
			case "TableInfo":
				TableInfo tableInfo = (TableInfo)tableView;
				
				selectInfoMap.put("table_name", tableInfo.getTableName());
				selectInfoMap.put("alias", tableInfo.getAlias());
				break;
			}
			
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
}
