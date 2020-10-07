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
import poly.persistance.mongo.IContentCrawlingMapper;
import poly.service.IContentCrawlingService;

@Service("ContentCrawlingService")
public class ContentCrawlingSerivce implements IContentCrawlingService {

	@Resource(name = "ContentCrawlingMapper")
	private IContentCrawlingMapper ContentCrawlingMapper; // MongoDB에 저장할 Mapper

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectContentCrawling() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".collectContentCrawling Start!");

		int res = 0;

		List<TitleDTO> pList = new ArrayList<TitleDTO>();

		// 크롤링 하는 페이지
		String url = "http://www.happypet.co.kr/news/articleList.html?sc_sub_section_code=S2N5&view_type=sm";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;

		doc = Jsoup.connect(url).get();

		Elements element = doc.select("section.user-snb");// or article-list-content
		/* post-area */
		/* System.out.println("element : " + element); */

		Iterator<Element> Crawling = element.select(".list-image a").iterator();

		while (Crawling.hasNext()) {

			Element Content = Crawling.next();

			String Content_url = Content.attr("href");

			Content = null;

			TitleDTO pDTO = new TitleDTO();

			pDTO.setContent("http://www.happypet.co.kr" + Content_url);

			Content_url = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Content"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		ContentCrawlingMapper.createCollection(colNm);

		log.info(this.getClass().getName() + ".insertContent Method Start!");

		ContentCrawlingMapper.insertContent(pList, colNm);

		log.info(this.getClass().getName() + ".insertContent Method End!");

		return res;
	}

	@Override
	public List<TitleDTO> getContentJspGo() throws Exception {
		log.info(this.getClass().getName() + ".뉴스 콘텐츠 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Content";

		List<TitleDTO> rList = ContentCrawlingMapper.getContent(colNm);

		if (rList == null) {
			rList = new ArrayList<TitleDTO>();
		}

		log.info(this.getClass().getName() + ".뉴스 콘텐츠 셀렉트 종료");

		return rList;
	}
}
