package poly.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import poly.dto.WeatherDTO;
import poly.service.IWeatherCrawlingService;

@Controller
public class WeatherController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "WeatherCrawlingService")
	private IWeatherCrawlingService WeatherCrawlingService;

	// 데이터 가져오기
	@RequestMapping(value = "/DExellent/getWeather")
	@ResponseBody
	public List<WeatherDTO> getWeather(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".Weather 셀렉트 컨트롤러 시작");

		List<WeatherDTO> rList = WeatherCrawlingService.getWeather();

		System.out.println("List<WeatherDTO> rList = WeatherCrawlingService.getWeather(); 실행됨");

		if (rList == null) {
			rList = new ArrayList<WeatherDTO>();
		}

		log.info(this.getClass().getName() + ".Weather 셀렉트 컨트롤러 종료");

		return rList;
	}

	// 데이터 가져와 jsp와 연결
	@RequestMapping(value = "/DExellent/Weather")
	public String Weather(HttpServletRequest requset, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".Weather 컨트롤러 (JSP출력) 시작");

		// 골든리트리버
		List<WeatherDTO> aList = WeatherCrawlingService.getGoldenRetrieverJspGo();

		// 골든리트리버
		model.addAttribute("aList", aList);
	
		aList = null;
	
		log.info(this.getClass().getName() + ".Weather 컨트롤러 (JSP출력) 종료");
		return "/DExellent/Weather";
	}
	

}
