package vql.web.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import queryParser.executor.Executor;
import queryParser.vo.QueryComponentType;
import queryParser.vo.QueryFactory;
import queryParser.vo.QueryInfo;

public class VisualizeService {
	public QueryFactory getVisualQueryInfo(String queryString) throws Exception{
		QueryFactory queryFactory = Executor.execute(queryString);
		
		return queryFactory;
	}
	
	public List<Map<String, String>> convertQueryInfoToMap(QueryInfo queryInfo){
		List<Map<String, String>> queryInfoMap = new ArrayList<Map<String,String>>();
		
		List<Map<String, String>> selectInfoMap = this.convertQueryInfoToMap(queryInfo);
		
		
		return queryInfoMap;
	}
	
	private List<Map<String, String>> convertSelectInfoToMap(List<QueryComponentType> selectInfoList){
		List<Map<String, String>> selectInfoMap = new ArrayList<Map<String,String>>();
		
		
		return selectInfoMap;
	}
}
