package vql.web.service;

import java.util.Map;

import queryParser.vo.WhereInfo;

public class WhereInfoConverter {
	public static Map<String, Object> convertInfoToMap(WhereInfo whereInfo){
		return InfoConverter.convertInfoToMap(whereInfo);
	}
}
