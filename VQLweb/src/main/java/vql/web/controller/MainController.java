package vql.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import queryParser.Comm.QueryCommVar;
import queryParser.vo.QueryFactory;
import vql.web.service.VisualizeService;

@Controller
public class MainController {

	/*
	 * 추후 json -> javascript object로 변환시
	 * http://stackoverflow.com/questions/5873624/parse-json-string-into-a-particular-object-prototype-in-javascript
	 * 참고해 볼 것.
	 */
	
	@RequestMapping("/inputQuery")
	public ModelAndView queryInput() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("version", QueryCommVar.QUERY_PARSER_VERSION);
		
		mv.setViewName("inputQuery");
		return mv;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public ModelAndView home(WebRequest webRequest, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("version", QueryCommVar.QUERY_PARSER_VERSION);
		
		String queryString = webRequest.getParameter("queryString");
		
		VisualizeService vs = new VisualizeService();
		QueryFactory qf = null;
		try {
			qf = vs.getVisualQueryInfo(queryString);
			
			String convertedQueryInfoString = vs.convertQueryInfoToMap(qf.getMainQueryInfo());
			
			mv.addObject("mainQueryInfo", convertedQueryInfoString);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO 임시 확인용
		mv.addObject("queryString", queryString);
		
		mv.setViewName("main");
		
		return mv;
	}
}
