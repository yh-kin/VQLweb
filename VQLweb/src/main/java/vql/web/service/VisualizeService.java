package vql.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import queryParser.executor.Executor;
import queryParser.vo.ColumnInfo;
import queryParser.vo.QueryComponentType;
import queryParser.vo.QueryFactory;
import queryParser.vo.QueryInfo;

public class VisualizeService {
	public QueryFactory getVisualQueryInfo(String queryString) throws Exception{
		QueryFactory queryFactory = Executor.execute(queryString);
		
		return queryFactory;
	}
	
	public String convertQueryInfoToMap(QueryInfo queryInfo) throws JsonProcessingException{
		Map<String, List<Map<String, Object>>> queryInfoMap = new HashMap<String, List<Map<String, Object>>>();
		
		List<Map<String, Object>> blockInfoMapList = new ArrayList<Map<String,Object>>();
		
		// SELECT
		List<Map<String, Object>> selectInfoMap = this.convertSelectInfoToMap(queryInfo.getSelectStmtInfo());
		queryInfoMap.put("select_info_list", selectInfoMap);
		
		if(selectInfoMap.size() > 0){
			Map<String, Object> blockInfoMap = new HashMap<String, Object>();
			blockInfoMap.put("block_type", "select");
			
			blockInfoMapList.add(blockInfoMap);
		}
		
		
		// FROM

		
		// BLOCK 
		queryInfoMap.put("block_info_list", blockInfoMapList);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(queryInfoMap);
	}
	
	private List<Map<String, Object>> convertSelectInfoToMap(List<QueryComponentType> selectInfoList){
		List<Map<String, Object>> selectInfoMapList = new ArrayList<Map<String,Object>>();
		
		for(QueryComponentType qc : selectInfoList){
			Map<String, Object> selectInfoMap = new HashMap<String, Object>();
			
			// Statement type
			selectInfoMap.put("stmt_type", "select");
			
			// Offset
			selectInfoMap.put("x_offset", 10);
			selectInfoMap.put("y_offset", 10);
			
			// Size
			selectInfoMap.put("height", 50);
			selectInfoMap.put("width", 80);

			// Column type
			selectInfoMap.put("type", qc.getClass().getSimpleName());
			
			// Contents
			switch(qc.getClass().getSimpleName()){
			case "ColumnInfo":
				ColumnInfo columnInfo = (ColumnInfo)qc;
				
				selectInfoMap.put("table_name", columnInfo.getTableName());
				selectInfoMap.put("column_name", columnInfo.getColumnName());
				break;
			}
			
			selectInfoMapList.add(selectInfoMap);
		}
		
		return selectInfoMapList;
	}
}
