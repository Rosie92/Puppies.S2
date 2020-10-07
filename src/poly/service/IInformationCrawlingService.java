package poly.service;

import java.util.List;

import poly.dto.InformationDTO;

public interface IInformationCrawlingService {

	// 크롤링 실행 후 저장
	public int collectInformationCrawling() throws Exception;

	// DB에 있는 크롤링 Select
	public List<InformationDTO> getInformation() throws Exception;

	// 골든리트리버
	public int GoldenRetriever() throws Exception;

	public List<InformationDTO> getGoldenRetrieverJspGo() throws Exception;

	// 래브라도 리트리버
	public int LabradorRetriever() throws Exception;

	public List<InformationDTO> getLabradorRetrieverJspGo() throws Exception;

	// 말티즈
	public int Maltese() throws Exception;

	public List<InformationDTO> getMalteseJspGo() throws Exception;

	// 보더콜리
	public int BorderCollie() throws Exception;

	public List<InformationDTO> getBorderCollieJspGo() throws Exception;

	// 비숑
	public int Bichonfreze() throws Exception;

	public List<InformationDTO> getBichonfrezeJspGo() throws Exception;

	// 사모예드
	public int Samoyed() throws Exception;

	public List<InformationDTO> getSamoyedJspGo() throws Exception;

	// 셔틀랜드
	public int Shertland() throws Exception;

	public List<InformationDTO> getShertlandJspGo() throws Exception;

	// 요크셔테리어
	public int Yorkshireterrier() throws Exception;

	public List<InformationDTO> getYorkshireterrierJspGo() throws Exception;

	// 스피츠
	public int JaffaneseSpitz() throws Exception;

	public List<InformationDTO> getJaffaneseSpitzJspGo() throws Exception;

	// 치와와
	public int Chihuahua() throws Exception;

	public List<InformationDTO> getChihuahuaJspGo() throws Exception;

}
