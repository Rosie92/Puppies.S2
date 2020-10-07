package poly.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KakaoMapController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/DExellent/Kindergarten")
	public String index(HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "######## 카카오 맵 컨트롤러 실행 ########");

		return "/DExellent/Kindergarten";
	}

}
