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

import poly.dto.TitleDTO;
import poly.service.IContentCrawlingService;
import poly.service.ITitleCrawlingService;

@Controller
public class EmpathyController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "TitleCrawlingService")
	private ITitleCrawlingService TitleCrawlingService;

	@Resource(name = "ContentCrawlingService")
	private IContentCrawlingService ContentCrawlingService;

	// 데이터 가져오기
	@RequestMapping(value = "/DExellent/getTitle")
	@ResponseBody
	public List<TitleDTO> getTitle(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info(this.getClass().getName() + ".뉴스타이틀 셀렉트 컨트롤러 시작");

		List<TitleDTO> rList = TitleCrawlingService.getTitleJspGo();

		System.out.println("List<TitleDTO> rList = TitleCrawlingService.getTitle(); 실행됨");

		if (rList == null) {
			rList = new ArrayList<TitleDTO>();
		}

		log.info(this.getClass().getName() + ".뉴스타이틀 셀렉트 컨트롤러 종료");

		return rList;
	}

	// 데이터 가져와 jsp와 연결
	@RequestMapping(value = "/DExellent/Title")
	public String Title(HttpServletRequest requset, HttpServletResponse response, ModelMap model) throws Exception {
		log.info(this.getClass().getName() + ".뉴스 셀렉트 컨트롤러 (JSP출력) 시작");

		List<TitleDTO> eList = TitleCrawlingService.getTitleJspGo();
		List<TitleDTO> rList = ContentCrawlingService.getContentJspGo();

		model.addAttribute("eList", eList);
		model.addAttribute("rList", rList);

		eList = null;
		rList = null;

		log.info(this.getClass().getName() + ".뉴스 셀렉트 컨트롤러 (JSP출력) 종료");

		return "/DExellent/Title";
	}

}