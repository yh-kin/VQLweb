package vql.web.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {

	/*
	 * 추후 json -> javascript object로 변환시
	 * http://stackoverflow.com/questions/5873624/parse-json-string-into-a-particular-object-prototype-in-javascript
	 * 참고해 볼 것.
	 */
	
	@RequestMapping("/main")
	public ModelAndView home(Locale locale, Model model) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("version", "_testingVersionShow_");
		
		mv.setViewName("main");
		
		return mv;
	}
}
