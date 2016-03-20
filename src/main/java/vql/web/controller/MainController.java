package vql.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import element.Query;
import vql.web.service.VisualizeService;

@Controller
public class MainController {
	
	private static final String VERSION = "1.0.1-SNAPSHOT";

	/*
	 * 추후 json -> javascript object로 변환시
	 * http://stackoverflow.com/questions/5873624/parse-json-string-into-a-particular-object-prototype-in-javascript
	 * 참고해 볼 것.
	 */
	
	@RequestMapping("/")
	public ModelAndView queryInput() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("version", VERSION);
		
		modelAndView.setViewName("inputQuery");
		return modelAndView;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public ModelAndView home(WebRequest webRequest, HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("version", VERSION);
		
		String queryString = webRequest.getParameter("queryString");
		
		VisualizeService visualizeService = new VisualizeService();
		Query query = null;
		try {
			query = visualizeService.getVisualQueryInfo(queryString);
			
			String convertedQueryInfoString = visualizeService.convertQueryInfoToMap(query);
			
			modelAndView.addObject("mainQueryInfo", convertedQueryInfoString);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// TODO 임시 확인용
		modelAndView.addObject("queryString", queryString);
		
		modelAndView.setViewName("main");
		
		return modelAndView;
	}
}
