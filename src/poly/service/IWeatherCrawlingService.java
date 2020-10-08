package poly.service;

import java.util.List;

import poly.dto.WeatherDTO;

public interface IWeatherCrawlingService {

	// 크롤링 실행 후 저장
	public int collectWeatherCrawling() throws Exception;

	// DB에 있는 크롤링 Select
	public List<WeatherDTO> getInformation() throws Exception;

	/*
	 * // 골든리트리버 public int GoldenRetriever() throws Exception;
	 * 
	 * public List<InformationDTO> getGoldenRetrieverJspGo() throws Exception;
	 */

}
