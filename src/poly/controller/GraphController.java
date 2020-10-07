package poly.controller;

import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GraphController {
	private Logger log = Logger.getLogger(this.getClass().getName());

	@RequestMapping(value = "/DExellent/Graph")
	public String Graph(HttpSession session) throws Exception {
		log.info(this.getClass().getName() + "########Graph 화면 실행########");
		return "/DExellent/Graph";
	}

}
