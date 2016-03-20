package vql.web.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import element.Query;
import static element.Query.Statement.*;
import lab.main.MainLaboratory;

public class VisualizeService {
	public Query getVisualQueryInfo(String queryString) throws Exception{
		Query query = new MainLaboratory().transform(queryString);
		
		return query;
	}
	
	public String convertQueryInfoToMap(Query queryInfo) throws JsonProcessingException{
		Map<String, Object> queryInfoMap = new HashMap<String, Object>();
		
		// SELECT
		List<Map<String, Object>> selectInfoMap = SelectInfoConverter.convertInfoToMap(queryInfo.getStatement(SELECT));
		queryInfoMap.put("select_info_list", selectInfoMap);
		
		// FROM
		List<Map<String, Object>> fromInfoMap = FromInfoConverter.convertInfoToMap(queryInfo.getStatement(FROM)); 
		queryInfoMap.put("from_info_list", fromInfoMap);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(queryInfoMap);
	}
}
