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

import poly.dto.InformationDTO;
import poly.dto.TitleDTO;
import poly.persistance.mongo.IInformationCrawlingMapper;
import poly.service.IInformationCrawlingService;

@Service("InformationCrawlingService")
public class InformationCrawlingService implements IInformationCrawlingService {

	@Resource(name = "InformationCrawlingMapper")
	private IInformationCrawlingMapper InformationCrawlingMapper; // MongoDB에 저장할 Mapper

	// 로그 파일 생성 및 로그 출력을 위한 log4j 프레임워크의 자바 객체
	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public int collectInformationCrawling() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Information collectContentCrawling Start!");

		int res = 0;

		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki_category/%ea%b2%ac%ec%a2%85%eb%b0%b1%ea%b3%bc/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("div#content");

		Iterator<Element> Crawling = element.select("div.elementor-post__card").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Information = CrawlingData.html();

			int i = 0;
			Information = Information.replaceAll("<img width='300'", "<img width='330'");
			Information = Information.replaceAll("height='200'",
					"height='230' src='../../assets/img/DEIMG/dog/a" + i + ".jpg'");
			Information = Information.replaceAll("더 보기 » </a>",
					"더 보기 » </a><hr style='width: 70%; margin-top: 15px; margin-bottom: 25px;'>");
			Information = Information.replaceAll("<h3", "<h3 style='font-weight: bolder;'");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Information);

			Information = null;

			pList.add(pDTO);

			pDTO = null;

			i++;
		}

		String colNm = "Information"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollection(colNm);

		log.info(this.getClass().getName() + ".insert Information Method Start!");

		InformationCrawlingMapper.insertInformation(pList, colNm);

		log.info(this.getClass().getName() + ".insert Information Method End!");

		return res;
	}

	@Override
	public List<InformationDTO> getInformation() throws Exception {
		log.info(this.getClass().getName() + ".Information 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Information";

		List<InformationDTO> rList = InformationCrawlingMapper.getInformation(colNm);

		if (rList == null) {
			rList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Information 셀렉트 종료");

		return rList;
	}

	// ======================견종백과 Content=============================
	// 골든리트리버
	@Override
	public int GoldenRetriever() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".GoldenRetriever Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

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
			GoldenRetriever = GoldenRetriever.replaceAll("853", "0");
			GoldenRetriever = GoldenRetriever.replaceAll("856", "0");
			GoldenRetriever = GoldenRetriever.replaceAll("960", "0");
			GoldenRetriever = GoldenRetriever.replaceAll("924", "0");
			GoldenRetriever = GoldenRetriever.replaceAll("844", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(GoldenRetriever);

			GoldenRetriever = null;

			pList.add(pDTO);

			pDTO = null;
		}

		String colNm = "GoldenRetriever"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionGoldenRetriever(colNm);

		log.info(this.getClass().getName() + ".insert GoldenRetriever Start!");

		InformationCrawlingMapper.insertInformationGoldenRetriever(pList, colNm);

		log.info(this.getClass().getName() + ".insert GoldenRetriever End!");

		return res;
	}

	@Override
	public List<InformationDTO> getGoldenRetrieverJspGo() throws Exception {
		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "GoldenRetriever";

		List<InformationDTO> aList = InformationCrawlingMapper.getGoldenRetriever(colNm);

		if (aList == null) {
			aList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 종료");

		return aList;
	}

	// 래브라도 리트리버
	@Override
	public int LabradorRetriever() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".LabradorRetriever Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%eb%9e%98%eb%b8%8c%eb%9d%bc%eb%8f%84-%eb%a6%ac%ed%8a%b8%eb%a6%ac%eb%b2%84/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String LabradorRetriever = CrawlingData.html();

			LabradorRetriever = LabradorRetriever.replaceAll("1280", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("853", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("856", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("960", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("924", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("844", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("540", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("560", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("315", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("640", "0");
			LabradorRetriever = LabradorRetriever.replaceAll("694", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(LabradorRetriever);

			LabradorRetriever = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "LabradorRetriever"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionLabradorRetriever(colNm);

		log.info(this.getClass().getName() + ".insert LabradorRetriever Start!");

		InformationCrawlingMapper.insertInformationLabradorRetriever(pList, colNm);

		log.info(this.getClass().getName() + ".insert LabradorRetriever End!");

		return res;
	}

	@Override
	public List<InformationDTO> getLabradorRetrieverJspGo() throws Exception {
		log.info(this.getClass().getName() + ".LabradorRetriever 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "LabradorRetriever";

		List<InformationDTO> bList = InformationCrawlingMapper.getLabradorRetriever(colNm);

		if (bList == null) {
			bList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".LabradorRetriever 셀렉트 종료");

		return bList;
	}

	// 말티즈
	@Override
	public int Maltese() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Maltese Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%eb%a7%90%ed%8b%b0%ec%a6%88/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Maltese = CrawlingData.html();

			Maltese = Maltese.replaceAll("1280", "0");
			Maltese = Maltese.replaceAll("853", "0");
			Maltese = Maltese.replaceAll("856", "0");
			Maltese = Maltese.replaceAll("960", "0");
			Maltese = Maltese.replaceAll("924", "0");
			Maltese = Maltese.replaceAll("844", "0");
			Maltese = Maltese.replaceAll("1920", "0");
			Maltese = Maltese.replaceAll("600", "0");
			Maltese = Maltese.replaceAll("338", "0");
			Maltese = Maltese.replaceAll("1275", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Maltese);

			Maltese = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Maltese"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionMaltese(colNm);

		log.info(this.getClass().getName() + ".insert Maltese Start!");

		InformationCrawlingMapper.insertInformationMaltese(pList, colNm);

		log.info(this.getClass().getName() + ".insert Maltese End!");

		return res;
	}

	@Override
	public List<InformationDTO> getMalteseJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Maltese 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Maltese";

		List<InformationDTO> cList = InformationCrawlingMapper.getMaltese(colNm);

		if (cList == null) {
			cList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Maltese 셀렉트 종료");

		return cList;
	}

	// 보더콜리
	@Override
	public int BorderCollie() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".BorderCollie Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%eb%b3%b4%eb%8d%94%ec%bd%9c%eb%a6%ac/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String BorderCollie = CrawlingData.html();

			BorderCollie = BorderCollie.replaceAll("1280", "0");
			BorderCollie = BorderCollie.replaceAll("853", "0");
			BorderCollie = BorderCollie.replaceAll("856", "0");
			BorderCollie = BorderCollie.replaceAll("960", "0");
			BorderCollie = BorderCollie.replaceAll("924", "0");
			BorderCollie = BorderCollie.replaceAll("844", "0");
			BorderCollie = BorderCollie.replaceAll("635", "0");
			BorderCollie = BorderCollie.replaceAll("640", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(BorderCollie);

			BorderCollie = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "BorderCollie"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionBorderCollie(colNm);

		log.info(this.getClass().getName() + ".insert BorderCollie Start!");

		InformationCrawlingMapper.insertInformationBorderCollie(pList, colNm);

		log.info(this.getClass().getName() + ".insert BorderCollie End!");

		return res;
	}

	@Override
	public List<InformationDTO> getBorderCollieJspGo() throws Exception {
		log.info(this.getClass().getName() + ".BorderCollie 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "BorderCollie";

		List<InformationDTO> dList = InformationCrawlingMapper.getBorderCollie(colNm);

		if (dList == null) {
			dList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".BorderCollie 셀렉트 종료");

		return dList;
	}

	// 비숑
	@Override
	public int Bichonfreze() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Bichonfreze Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%eb%b9%84%ec%88%91-%ed%94%84%eb%a6%ac%ec%a0%9c/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Bichonfreze = CrawlingData.html();

			Bichonfreze = Bichonfreze.replaceAll("1280", "0");
			Bichonfreze = Bichonfreze.replaceAll("853", "0");
			Bichonfreze = Bichonfreze.replaceAll("856", "0");
			Bichonfreze = Bichonfreze.replaceAll("960", "0");
			Bichonfreze = Bichonfreze.replaceAll("924", "0");
			Bichonfreze = Bichonfreze.replaceAll("844", "0");
			Bichonfreze = Bichonfreze.replaceAll("1024", "0");
			Bichonfreze = Bichonfreze.replaceAll("819", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Bichonfreze);

			Bichonfreze = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Bichonfreze"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionBichonfreze(colNm);

		log.info(this.getClass().getName() + ".insert Bichonfreze Start!");

		InformationCrawlingMapper.insertInformationBichonfreze(pList, colNm);

		log.info(this.getClass().getName() + ".insert Bichonfreze End!");

		return res;
	}

	@Override
	public List<InformationDTO> getBichonfrezeJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Bichonfreze 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Bichonfreze";

		List<InformationDTO> eList = InformationCrawlingMapper.getBichonfreze(colNm);

		if (eList == null) {
			eList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Bichonfreze 셀렉트 종료");

		return eList;
	}

	// 사모예드
	@Override
	public int Samoyed() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Samoyed Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ec%82%ac%eb%aa%a8%ec%98%88%eb%93%9c/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Samoyed = CrawlingData.html();

			Samoyed = Samoyed.replaceAll("1280", "0");
			Samoyed = Samoyed.replaceAll("853", "0");
			Samoyed = Samoyed.replaceAll("856", "0");
			Samoyed = Samoyed.replaceAll("960", "0");
			Samoyed = Samoyed.replaceAll("924", "0");
			Samoyed = Samoyed.replaceAll("844", "0");
			Samoyed = Samoyed.replaceAll("740", "0");
			Samoyed = Samoyed.replaceAll("494", "0");
			Samoyed = Samoyed.replaceAll("869", "0");
			Samoyed = Samoyed.replaceAll("720", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Samoyed);

			Samoyed = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Samoyed"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionSamoyed(colNm);

		log.info(this.getClass().getName() + ".insert Samoyed Start!");

		InformationCrawlingMapper.insertInformationSamoyed(pList, colNm);

		log.info(this.getClass().getName() + ".insert Samoyed End!");

		return res;
	}

	@Override
	public List<InformationDTO> getSamoyedJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Samoyed 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Samoyed";

		List<InformationDTO> fList = InformationCrawlingMapper.getSamoyed(colNm);

		if (fList == null) {
			fList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Samoyed 셀렉트 종료");

		return fList;
	}

	// 셔틀랜드
	@Override
	public int Shertland() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Shertland Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ec%85%94%ed%8b%80%eb%9e%9c%eb%93%9c-%ec%89%bd%eb%8f%85/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Shertland = CrawlingData.html();

			Shertland = Shertland.replaceAll("1280", "0");
			Shertland = Shertland.replaceAll("853", "0");
			Shertland = Shertland.replaceAll("856", "0");
			Shertland = Shertland.replaceAll("960", "0");
			Shertland = Shertland.replaceAll("924", "0");
			Shertland = Shertland.replaceAll("844", "0");
			Shertland = Shertland.replaceAll("657", "0");
			Shertland = Shertland.replaceAll("637", "0");
			Shertland = Shertland.replaceAll("640", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Shertland);

			Shertland = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Shertland"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionShertland(colNm);

		log.info(this.getClass().getName() + ".insert Shertland Start!");

		InformationCrawlingMapper.insertInformationShertland(pList, colNm);

		log.info(this.getClass().getName() + ".insert Shertland End!");

		return res;
	}

	@Override
	public List<InformationDTO> getShertlandJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Shertland 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Shertland";

		List<InformationDTO> gList = InformationCrawlingMapper.getShertland(colNm);

		if (gList == null) {
			gList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Shertland 셀렉트 종료");

		return gList;
	}

	// 요크셔테리어
	@Override
	public int Yorkshireterrier() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Yorkshireterrier Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ec%9a%94%ed%81%ac%ec%85%94%ed%85%8c%eb%a6%ac%ec%96%b4/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Yorkshireterrier = CrawlingData.html();

			Yorkshireterrier = Yorkshireterrier.replaceAll("1280", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("853", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("856", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("960", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("924", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("844", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("2129", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("1500", "0");
			Yorkshireterrier = Yorkshireterrier.replaceAll("2251", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Yorkshireterrier);

			Yorkshireterrier = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Yorkshireterrier"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionYorkshireterrier(colNm);

		log.info(this.getClass().getName() + ".insert Yorkshireterrier Start!");

		InformationCrawlingMapper.insertInformationYorkshireterrier(pList, colNm);

		log.info(this.getClass().getName() + ".insert Yorkshireterrier End!");

		return res;
	}

	@Override
	public List<InformationDTO> getYorkshireterrierJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Yorkshireterrier 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Yorkshireterrier";

		List<InformationDTO> hList = InformationCrawlingMapper.getYorkshireterrier(colNm);

		if (hList == null) {
			hList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Yorkshireterrier 셀렉트 종료");

		return hList;
	}

	// 스피츠
	@Override
	public int JaffaneseSpitz() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".JaffaneseSpitz Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ec%9e%ac%ed%8c%a8%eb%8b%88%ec%8a%a4-%ec%8a%a4%ed%94%bc%ec%b8%a0/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String JaffaneseSpitz = CrawlingData.html();

			JaffaneseSpitz = JaffaneseSpitz.replaceAll("1280", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("853", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("856", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("960", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("924", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("844", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("560", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("314", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("3024", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("480", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("720", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("708", "0");
			JaffaneseSpitz = JaffaneseSpitz.replaceAll("398", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(JaffaneseSpitz);

			JaffaneseSpitz = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "JaffaneseSpitz"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionJaffaneseSpitz(colNm);

		log.info(this.getClass().getName() + ".insert JaffaneseSpitz Start!");

		InformationCrawlingMapper.insertInformationJaffaneseSpitz(pList, colNm);

		log.info(this.getClass().getName() + ".insert JaffaneseSpitz End!");

		return res;
	}

	@Override
	public List<InformationDTO> getJaffaneseSpitzJspGo() throws Exception {
		log.info(this.getClass().getName() + ".JaffaneseSpitz 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "JaffaneseSpitz";

		List<InformationDTO> iList = InformationCrawlingMapper.getJaffaneseSpitz(colNm);

		if (iList == null) {
			iList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".JaffaneseSpitz 셀렉트 종료");

		return iList;
	}

	// 치와와
	@Override
	public int Chihuahua() throws Exception {

		// 로그 찍기(추후 찍은 로그를 통해 이 함수에 접근했는지 파악하기 용이하다.)
		log.info(this.getClass().getName() + ".Chihuahua Start!");
		int res = 0;
		List<InformationDTO> pList = new ArrayList<InformationDTO>();

		// 크롤링 하는 페이지
		String url = "https://mypetlife.co.kr/wiki/%ec%b9%98%ec%99%80%ec%99%80/";

		// JSOUP 라이브러리를 통해 사이트 접속되면, 그 사이트의 전체 HTML소스 저장할 변수
		Document doc = null;
		doc = Jsoup.connect(url).get();

		Elements element = doc.select("article#the-post");

		Iterator<Element> Crawling = element.select("p").iterator();

		while (Crawling.hasNext()) {

			Element CrawlingData = Crawling.next();

			String Chihuahua = CrawlingData.html();

			Chihuahua = Chihuahua.replaceAll("1280", "0");
			Chihuahua = Chihuahua.replaceAll("853", "0");
			Chihuahua = Chihuahua.replaceAll("856", "0");
			Chihuahua = Chihuahua.replaceAll("960", "0");
			Chihuahua = Chihuahua.replaceAll("924", "0");
			Chihuahua = Chihuahua.replaceAll("844", "0");
			Chihuahua = Chihuahua.replaceAll("1024", "0");
			Chihuahua = Chihuahua.replaceAll("682", "0");
			Chihuahua = Chihuahua.replaceAll("681", "0");
			Chihuahua = Chihuahua.replaceAll("683", "0");

			CrawlingData = null;

			InformationDTO pDTO = new InformationDTO();

			pDTO.setInformation(Chihuahua);

			Chihuahua = null;

			pList.add(pDTO);

			pDTO = null;

		}

		String colNm = "Chihuahua"; // 생성할 컬렉션명

		// MongoDB Collection 생성하기
		InformationCrawlingMapper.createCollectionChihuahua(colNm);

		log.info(this.getClass().getName() + ".insert Chihuahua Start!");

		InformationCrawlingMapper.insertInformationChihuahua(pList, colNm);

		log.info(this.getClass().getName() + ".insert Chihuahua End!");

		return res;
	}

	@Override
	public List<InformationDTO> getChihuahuaJspGo() throws Exception {
		log.info(this.getClass().getName() + ".Chihuahua 셀렉트 서비스 시작");

		// 조회할 컬렉션 이름
		String colNm = "Chihuahua";

		List<InformationDTO> jList = InformationCrawlingMapper.getChihuahua(colNm);

		if (jList == null) {
			jList = new ArrayList<InformationDTO>();
		}

		log.info(this.getClass().getName() + ".Chihuahua 셀렉트 종료");

		return jList;
	}
}
