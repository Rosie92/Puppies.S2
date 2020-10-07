package poly.controller;

import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import poly.service.IContentCrawlingService;
import poly.service.IInformationCrawlingService;
import poly.service.ITitleCrawlingService;
import poly.util.CmmUtil;

@Controller
public class DEController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@Resource(name = "TitleCrawlingService")
	private ITitleCrawlingService titlecrawlingService;

	@Resource(name = "ContentCrawlingService")
	private IContentCrawlingService contentcrawlingService;

	@Resource(name = "InformationCrawlingService")
	private IInformationCrawlingService informationcrawlingService;

	@RequestMapping(value = "/DExellent/index")
	public String index(HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "########인덱스 화면 실행########");

		// 로그인 했을 시 (user_name세션에 값이 있을경우)
		if (!CmmUtil.nvl((String) session.getAttribute("user_name")).equals("")) {
			// 뉴스 제목 크롤링 해오기
			titlecrawlingService.collectTitleCrawling();
			// 뉴스 내용 크롤링 해우기
			contentcrawlingService.collectContentCrawling();
			// 견종백과 주소 크롤링 해오기
			informationcrawlingService.collectInformationCrawling();
			// 견종백과 내용 크롤링 해오기
			informationcrawlingService.GoldenRetriever();
			informationcrawlingService.LabradorRetriever();
			informationcrawlingService.Maltese();
			informationcrawlingService.BorderCollie();
			informationcrawlingService.Bichonfreze();
			informationcrawlingService.Samoyed();
			informationcrawlingService.Shertland();
			informationcrawlingService.Yorkshireterrier();
			informationcrawlingService.JaffaneseSpitz();
			informationcrawlingService.Chihuahua();

		}
		return "/DExellent/index";
	}

}
