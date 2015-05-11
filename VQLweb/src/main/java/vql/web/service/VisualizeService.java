package vql.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import queryParser.executor.Executor;
import queryParser.vo.QueryFactory;
import queryParser.vo.QueryInfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class VisualizeService {
	public QueryFactory getVisualQueryInfo(String queryString) throws Exception{
		QueryFactory queryFactory = Executor.execute(queryString);
		
		return queryFactory;
	}
	
	public String convertQueryInfoToMap(QueryInfo queryInfo) throws JsonProcessingException{
		Map<String, Object> queryInfoMap = new HashMap<String, Object>();
		
		// SELECT
		List<Map<String, Object>> selectInfoMap = SelectInfoConverter.convertInfoToMap(queryInfo.getSelectStmtInfo());
		queryInfoMap.put("select_info_list", selectInfoMap);
		
		// FROM
		List<Map<String, Object>> fromInfoMap = FromInfoConverter.convertInfoToMap(queryInfo.getFromStmtInfo()); 
		queryInfoMap.put("from_info_list", fromInfoMap);
		
		// WHERE
		Map<String, Object> whereInfoMap = WhereInfoConverter.convertInfoToMap(queryInfo.getWhereStmtInfo());
		queryInfoMap.put("where_info", whereInfoMap);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(queryInfoMap);
	}
}
