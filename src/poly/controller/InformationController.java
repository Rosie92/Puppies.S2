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

import poly.dto.InformationDTO;
import poly.service.IInformationCrawlingService;

@Controller
public class InformationController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "InformationCrawlingService")
	private IInformationCrawlingService InformationCrawlingService;

	// 데이터 가져오기
	@RequestMapping(value = "/DExellent/getInformation")
	@ResponseBody
	public List<InformationDTO> getInformation(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		log.info(this.getClass().getName() + ".Information 셀렉트 컨트롤러 시작");

		List<InformationDTO> rList = InformationCrawlingService.getInformation();

		System.out.println("List<InformationDTO> rList = InformationCrawlingService.getInformation(); 실행됨");

		if (rList == null) {
			rList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Information 셀렉트 컨트롤러 종료");

		return rList;
	}

	// 데이터 가져와 jsp와 연결
	@RequestMapping(value = "/DExellent/Information")
	public String Information(HttpServletRequest requset, HttpServletResponse response, ModelMap model)
			throws Exception {
		log.info(this.getClass().getName() + ".Information 컨트롤러 (JSP출력) 시작");

		// 골든리트리버
		List<InformationDTO> aList = InformationCrawlingService.getGoldenRetrieverJspGo();
		// 래브라도 리트리버
		List<InformationDTO> bList = InformationCrawlingService.getLabradorRetrieverJspGo();
		// 말티즈
		List<InformationDTO> cList = InformationCrawlingService.getMalteseJspGo();
		// 보더콜리
		List<InformationDTO> dList = InformationCrawlingService.getBorderCollieJspGo();
		// 비숑프리제
		List<InformationDTO> eList = InformationCrawlingService.getBichonfrezeJspGo();
		// 사모예드
		List<InformationDTO> fList = InformationCrawlingService.getSamoyedJspGo();
		// 셔틀랜드
		List<InformationDTO> gList = InformationCrawlingService.getShertlandJspGo();
		// 요크셔테리어
		List<InformationDTO> hList = InformationCrawlingService.getYorkshireterrierJspGo();
		// 스피츠
		List<InformationDTO> iList = InformationCrawlingService.getJaffaneseSpitzJspGo();
		// 치와와
		List<InformationDTO> jList = InformationCrawlingService.getChihuahuaJspGo();

		// 골든리트리버
		model.addAttribute("aList", aList);
		// 래브라도 리트리버
		model.addAttribute("bList", bList);
		// 말티즈
		model.addAttribute("cList", cList);
		// 보더콜리
		model.addAttribute("dList", dList);
		// 비숑프리제
		model.addAttribute("eList", eList);
		// 사모예드
		model.addAttribute("fList", fList);
		// 셔틀랜드
		model.addAttribute("gList", gList);
		// 요크셔테리어
		model.addAttribute("hList", hList);
		// 스피츠
		model.addAttribute("iList", iList);
		// 치와와
		model.addAttribute("jList", jList);

		aList = null;
		bList = null;
		cList = null;
		dList = null;
		eList = null;
		fList = null;
		gList = null;
		hList = null;
		iList = null;
		jList = null;

		log.info(this.getClass().getName() + ".Information 컨트롤러 (JSP출력) 종료");
		return "/DExellent/Information";
	}

}
