package poly.controller;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/DExellent/mypage")
	public String index() {
		log.info(this.getClass().getName() + "########마이페이지 화면 실행########");

		return "/DExellent/mypage";
	}

}
