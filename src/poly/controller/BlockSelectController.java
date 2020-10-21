package poly.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlockSelectController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/DExellent/BlockSelect")
	public String index(HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "########인덱스 화면 실행########");
		return "/DExellent/BlockSelect";
	}

}
