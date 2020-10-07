package poly.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import poly.dto.TitleDTO;
import poly.persistance.mongo.ITitleCrawlingMapper;
import poly.service.ITitleCrawlingService;

@Service("TitleCrawlingService")
public class TitleCrawlingService implements ITitleCrawlingService {

	@Resource(name = "TitleCrawlingMapper")
	private ITitleCrawlingMapper TitleCrawlingMapper; // MongoDB에 저장할 Mapper

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectTitleCrawling() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".collectTitleCrawling Start!");

		int res = 0;

		List<TitleDTO> pList = new ArrayList<TitleDTO>();

		// 크롤링 하는 페이지
		String url = "http://www.happypet.co.kr/news/articleList.html?sc_sub_section_code=S2N5&view_type=sm";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("section.user-snb");// or article-list-content

		Iterator<Element> Crawling = element.select("div.list-block").iterator();

		while (Crawling.hasNext()) {

			Element animalnews = Crawling.next();

			String title = animalnews.select("strong").text(); // or a href?

			animalnews = null;

			TitleDTO pDTO = new TitleDTO();

			pDTO.setTitle(title);

			title = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Title"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		TitleCrawlingMapper.createCollection(colNm);

		log.info(this.getClass().getName() + ".insertTitle Method Start!");

		TitleCrawlingMapper.insertTitle(pList, colNm);

		log.info(this.getClass().getName() + ".insertTitle Method End!");

		return res;
	}

	@Override
	public List<TitleDTO> getTitleJspGo() throws Exception {
		log.info(this.getClass().getName() + ".뉴스 타이틀 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Title";

		List<TitleDTO> rList = TitleCrawlingMapper.getTitle(colNm);

		if (rList == null) {
			rList = new ArrayList<TitleDTO>();
		}

		log.info(this.getClass().getName() + ".뉴스 타이틀 셀렉트 종료");

		return rList;
	}
}
