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

import poly.dto.WeatherDTO;
import poly.persistance.mongo.IWeatherCrawlingMapper;
import poly.service.IWeatherCrawlingService;

@Service("WeatherCrawlingService")
public class WeatherCrawlingService implements IWeatherCrawlingService {

	@Resource(name = "WeatherCrawlingMapper")
	private IWeatherCrawlingMapper WeatherCrawlingMapper; // MongoDB에 저장할 Mapper

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectWeatherCrawling() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Weather collectContentCrawling Start!");

		int res = 0;

		List<WeatherDTO> pList = new ArrayList<WeatherDTO>();

		// 크롤링 하는 페이지
		String url = "https://www.weather.go.kr/w/index.do";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("div#temp");

		Iterator<Element> Crawling = element.select("img").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Weather = CrawlingData.html();

			int i = 0;
			Weather = Weather.replaceAll("<img width='300'", "<img width='330'");
			Weather = Weather.replaceAll("height='200'",
					"height='230' src='../../assets/img/DEIMG/dog/a" + i + ".jpg'");
			Weather = Weather.replaceAll("더 보기 » </a>",
					"더 보기 » </a><hr style='width: 70%; margin-top: 15px; margin-bottom: 25px;'>");
			Weather = Weather.replaceAll("<h3", "<h3 style='font-weight: bolder;'");

			CrawlingData = null;

			WeatherDTO pDTO = new WeatherDTO();

			pDTO.setWeather(Weather);

			Weather = null;

			pList.add(pDTO);

			pDTO = null;

			i++;
		}

		String colNm = "Weather"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		WeatherCrawlingMapper.createCollection(colNm);

		log.info(this.getClass().getName() + ".insert Weather Method Start!");

		WeatherCrawlingMapper.insertWeather(pList, colNm);

		log.info(this.getClass().getName() + ".insert Weather Method End!");

		return res;
	}

	@Override
	public List<WeatherDTO> getWeather() throws Exception {
		log.info(this.getClass().getName() + ".Weather 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Weather";

		List<WeatherDTO> rList = WeatherCrawlingMapper.getWeather(colNm);

		if (rList == null) {
			rList = new ArrayList<WeatherDTO>();
		}

		log.info(this.getClass().getName() + ".Weather 셀렉트 종료");

		return rList;
	}

	// ======================견종백과 Content=============================
	// 골든리트리버
	@Override
	public int GoldenRetriever() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".GoldenRetriever Start!");
		int res = 0;
		List<WeatherDTO> pList = new ArrayList<WeatherDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ea%b3%a8%eb%93%a0%eb%a6%ac%ed%8a%b8%eb%a6%ac%eb%b2%84/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String GoldenRetriever = CrawlingData.html();

			GoldenRetriever = GoldenRetriever.replaceAll("1280", "0");
			
			CrawlingData = null;

			WeatherDTO pDTO = new WeatherDTO();

			pDTO.setWeather(GoldenRetriever);

			GoldenRetriever = null;

			pList.add(pDTO);

			pDTO = null;
		}

		String colNm = "GoldenRetriever"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		WeatherCrawlingMapper.createCollectionGoldenRetriever(colNm);

		log.info(this.getClass().getName() + ".insert GoldenRetriever Start!");

		WeatherCrawlingMapper.insertWeatherGoldenRetriever(pList, colNm);

		log.info(this.getClass().getName() + ".insert GoldenRetriever End!");

		return res;
	}

	@Override
	public List<WeatherDTO> getGoldenRetrieverJspGo() throws Exception {
		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "GoldenRetriever";

		List<WeatherDTO> aList = WeatherCrawlingMapper.getGoldenRetriever(colNm);

		if (aList == null) {
			aList = new ArrayList<WeatherDTO>();
		}

		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 종료");

		return aList;
	}
}
