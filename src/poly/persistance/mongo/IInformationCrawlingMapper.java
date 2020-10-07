package poly.persistance.mongo;

import java.util.List;

import poly.dto.InformationDTO;

public interface IInformationCrawlingMapper {

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
	public int insertInformation(List<InformationDTO> pList, String colNm) throws Exception;

	/*
	 * MongoDB 데이터 가져오기
	 * 
	 * @param colNm 가져올 컬렉션 이름
	 */
	public List<InformationDTO> getInformation(String colNm) throws Exception;

	// 골든리트리버
	public boolean createCollectionGoldenRetriever(String colNm) throws Exception;

	public int insertInformationGoldenRetriever(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getGoldenRetriever(String colNm) throws Exception;

	// 래브라도리트리버
	public boolean createCollectionLabradorRetriever(String colNm) throws Exception;

	public int insertInformationLabradorRetriever(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getLabradorRetriever(String colNm) throws Exception;

	// 말티즈
	public boolean createCollectionMaltese(String colNm) throws Exception;

	public int insertInformationMaltese(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getMaltese(String colNm) throws Exception;

	// 보더콜리
	public boolean createCollectionBorderCollie(String colNm) throws Exception;

	public int insertInformationBorderCollie(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getBorderCollie(String colNm) throws Exception;

	// 비숑
	public boolean createCollectionBichonfreze(String colNm) throws Exception;

	public int insertInformationBichonfreze(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getBichonfreze(String colNm) throws Exception;

	// 사모예드
	public boolean createCollectionSamoyed(String colNm) throws Exception;

	public int insertInformationSamoyed(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getSamoyed(String colNm) throws Exception;

	// 셔틀랜드
	public boolean createCollectionShertland(String colNm) throws Exception;

	public int insertInformationShertland(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getShertland(String colNm) throws Exception;

	// 요크셔테리어
	public boolean createCollectionYorkshireterrier(String colNm) throws Exception;

	public int insertInformationYorkshireterrier(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getYorkshireterrier(String colNm) throws Exception;

	// 스피츠
	public boolean createCollectionJaffaneseSpitz(String colNm) throws Exception;

	public int insertInformationJaffaneseSpitz(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getJaffaneseSpitz(String colNm) throws Exception;

	// 치와와
	public boolean createCollectionChihuahua(String colNm) throws Exception;

	public int insertInformationChihuahua(List<InformationDTO> pList, String colNm) throws Exception;

	public List<InformationDTO> getChihuahua(String colNm) throws Exception;
}
