package vql.web.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import element.Query;
import static element.Query.Statement.*;
import lab.main.MainLaboratory;
import type.Element;

public class VisualizeService {
	public Query getVisualQueryInfo(String queryString) throws Exception{
		Query query = new MainLaboratory().transform(queryString);
		
		return query;
	}
	
	public String convertQueryInfoToMap(Query queryInfo) throws JsonProcessingException{
		Map<String, Object> queryInfoMap = new HashMap<String, Object>();
		
		// SELECT
		List<Map<String, Object>> selectMap = this.convertElementToMap(queryInfo.getStatement(SELECT));
		queryInfoMap.put("select", selectMap);
		
		// FROM
		List<Map<String, Object>> fromMap = this.convertElementToMap(queryInfo.getStatement(FROM)); 
		queryInfoMap.put("from", fromMap);
		
		// WHERE
		List<Map<String, Object>> whereMap = this.convertElementToMap(queryInfo.getStatement(WHERE)); 
		queryInfoMap.put("where", whereMap);
		
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(queryInfoMap);
	}
	
	private List<Map<String, Object>> convertElementToMap(Collection<Element> elements){
		List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
		
		for(Element element : elements){
			Map<String, Object> map = InfoConverter.convertInfoToMap(element);
			mapList.add(map);
		}
		
		return mapList;
	}
}
