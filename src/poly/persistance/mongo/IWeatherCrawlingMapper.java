package poly.persistance.mongo;

import java.util.List;

import poly.dto.WeatherDTO;

public interface IWeatherCrawlingMapper {

	/*
	 * MongoDB 컬렉션 생성
	 * 
	 * @param colNm 생성하는 컬렉션 이름
	 */

	public boolean createCollection(String colNm) throws Exception;

	/*
	 * MongoDB 데이터 저장하기
	 * 
	 * @param pDTO 저장될 정보
	 */
	public int insertWeather(List<WeatherDTO> pList, String colNm) throws Exception;

	/*
	 * MongoDB 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */
	public List<WeatherDTO> getWeather(String colNm) throws Exception;

	// 골든리트리버
	public boolean createCollectionGoldenRetriever(String colNm) throws Exception;

	public int insertWeatherGoldenRetriever(List<WeatherDTO> pList, String colNm) throws Exception;

	public List<WeatherDTO> getGoldenRetriever(String colNm) throws Exception;

}
