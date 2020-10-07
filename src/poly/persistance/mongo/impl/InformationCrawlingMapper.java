package poly.persistance.mongo.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import poly.dto.InformationDTO;
import poly.persistance.mongo.IInformationCrawlingMapper;
import poly.util.CmmUtil;

@Component("InformationCrawlingMapper")
public class InformationCrawlingMapper implements IInformationCrawlingMapper {

	@Autowired
	private MongoTemplate mongodb;

	private Logger log = Logger.getLogger(this.getClass());

	@Override
	public boolean createCollection(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollection Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Information", 1),
				"Information");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollection End!");

		return res;
	}

	@Override
	public int insertInformation(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformation Start!");

		log.info("몽고디비 Information 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Information 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getInformation(String colNm) throws Exception {

		log.info(this.getClass().getName() + ".Information 셀렉트 매퍼 시작");

		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> rList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			rList.add(rDTO); // List에 저장

			rDTO = null;

		}

		log.info(this.getClass().getName() + ".Information 셀렉트 매퍼 종료");

		return rList;

	}

	// ==========================견종백과===========================
	// 골든리트리버
	@Override
	public boolean createCollectionGoldenRetriever(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionGoldenRetriever Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("GoldenRetriever", 1),
				"GoldenRetriever");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionGoldenRetriever End!");

		return res;
	}

	@Override
	public int insertInformationGoldenRetriever(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationGoldenRetriever Start!");
		log.info("몽고디비 GoldenRetriever 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 GoldenRetriever 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getGoldenRetriever(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> aList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			aList.add(rDTO); // List에 저장
			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".GoldenRetriever 셀렉트 매퍼 종료");

		return aList;

	}

	// 래브라도 리트리버
	@Override
	public boolean createCollectionLabradorRetriever(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionLabradorRetriever Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("LabradorRetriever", 1),
				"LabradorRetriever");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionLabradorRetriever End!");

		return res;
	}

	@Override
	public int insertInformationLabradorRetriever(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationLabradorRetriever Start!");
		log.info("몽고디비 LabradorRetriever 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 LabradorRetriever 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getLabradorRetriever(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".LabradorRetriever 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> bList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			bList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".LabradorRetriever 셀렉트 매퍼 종료");

		return bList;

	}

	// 말티즈
	@Override
	public boolean createCollectionMaltese(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionMaltese Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Maltese", 1),
				"Maltese");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionMaltese End!");

		return res;
	}

	@Override
	public int insertInformationMaltese(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationMaltese Start!");

		log.info("몽고디비 Maltese 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Maltese 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getMaltese(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Maltese 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> cList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			cList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Maltese 셀렉트 매퍼 종료");

		return cList;

	}

	// 보더콜리
	@Override
	public boolean createCollectionBorderCollie(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionBorderCollie Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("BorderCollie", 1),
				"BorderCollie");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionBorderCollie End!");

		return res;
	}

	@Override
	public int insertInformationBorderCollie(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationBorderCollie Start!");

		log.info("몽고디비 BorderCollie 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 BorderCollie 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getBorderCollie(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".BorderCollie 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> dList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			dList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".BorderCollie 셀렉트 매퍼 종료");

		return dList;

	}

	// 비숑 프란체
	@Override
	public boolean createCollectionBichonfreze(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionBichonfreze Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Bichonfreze", 1),
				"Bichonfreze");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionBichonfreze End!");

		return res;
	}

	@Override
	public int insertInformationBichonfreze(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationBichonfreze Start!");

		log.info("몽고디비 Bichonfreze 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Bichonfreze 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getBichonfreze(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Bichonfreze 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> eList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			eList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Bichonfreze 셀렉트 매퍼 종료");

		return eList;

	}

	// 사모예드
	@Override
	public boolean createCollectionSamoyed(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionSamoyed Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Samoyed", 1),
				"Samoyed");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionSamoyed End!");

		return res;
	}

	@Override
	public int insertInformationSamoyed(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationSamoyed Start!");

		log.info("몽고디비 Samoyed 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Samoyed 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getSamoyed(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Samoyed 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> fList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			fList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Samoyed 셀렉트 매퍼 종료");

		return fList;

	}

	// 셔틀랜드
	@Override
	public boolean createCollectionShertland(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionShertland Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Shertland", 1),
				"Shertland");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionShertland End!");

		return res;
	}

	@Override
	public int insertInformationShertland(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationShertland Start!");

		log.info("몽고디비 Shertland 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Shertland 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getShertland(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Shertland 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> gList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			gList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Shertland 셀렉트 매퍼 종료");

		return gList;

	}

	// 요크셔테리어
	@Override
	public boolean createCollectionYorkshireterrier(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionYorkshireterrier Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Yorkshireterrier", 1),
				"Yorkshireterrier");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionYorkshireterrier End!");

		return res;
	}

	@Override
	public int insertInformationYorkshireterrier(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationYorkshireterrier Start!");

		log.info("몽고디비 Yorkshireterrier 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Yorkshireterrier 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getYorkshireterrier(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Yorkshireterrier 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> hList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			hList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Yorkshireterrier 셀렉트 매퍼 종료");

		return hList;

	}

	// 재패니즈 스피츠
	@Override
	public boolean createCollectionJaffaneseSpitz(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionJaffaneseSpitz Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("JaffaneseSpitz", 1),
				"JaffaneseSpitz");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionJaffaneseSpitz End!");

		return res;
	}

	@Override
	public int insertInformationJaffaneseSpitz(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationJaffaneseSpitz Start!");

		log.info("몽고디비 JaffaneseSpitz 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 JaffaneseSpitz 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getJaffaneseSpitz(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".JaffaneseSpitz 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> iList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			iList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".JaffaneseSpitz 셀렉트 매퍼 종료");

		return iList;

	}

	// 치와와
	@Override
	public boolean createCollectionChihuahua(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Information createCollectionChihuahua Start!");

		boolean res = false;

		// 기존에 등록된 컬렉션 이름이 존재하는지 체크하고, 존재하면 기존 컬렉션 삭제함
		if (mongodb.collectionExists(colNm)) {
			mongodb.dropCollection(colNm); // 기존 컬렉션 삭제
		}

		// 컬렉션 생성 및 인덱스 생성, MongoDB에서 데이터 가져오는 방식에 맞게 인덱스는 반드시 생성하자!
		// 데이터 양이 많지 않으면 문제되지 않으나, 최소 10만건 이상 데이터 저장시 속도가 약 10배 이상 발생함
		mongodb.createCollection(colNm).createIndex(new BasicDBObject("collect_time", 1).append("Chihuahua", 1),
				"Chihuahua");

		res = true;

		log.info(this.getClass().getName() + ".Information createCollectionChihuahua End!");

		return res;
	}

	@Override
	public int insertInformationChihuahua(List<InformationDTO> pList, String colNm) throws Exception {
		log.info(this.getClass().getName() + ".insertInformationChihuahua Start!");

		log.info("몽고디비 Chihuahua 인서트 실행 시작");

		mongodb.insert(pList, colNm);

		log.info("몽고디비 Chihuahua 인서트 실행 완료");

		return 1;
	}

	@Override
	public List<InformationDTO> getChihuahua(String colNm) throws Exception {
		log.info(this.getClass().getName() + ".Chihuahua 셀렉트 매퍼 시작");
		// 데이터를 가져올 컬렉션 선택
		DBCollection rCol = mongodb.getCollection(colNm);
		System.out.println("##################데이터를 가져올 컬렉션 선택##############");
		// 컬렉션으로부터 전체 데이터 가져오기
		Iterator<DBObject> cursor = rCol.find();
		System.out.println("########컬렉션으로부터 전체 데이터 가져오기 ###########");
		// 컬렉션으로부터 전체 데이터 가져온 것을 List 형태로 저장하기 위한 변수 선언
		List<InformationDTO> jList = new ArrayList<InformationDTO>();

		InformationDTO rDTO = null;

		while (cursor.hasNext()) {

			rDTO = new InformationDTO();

			int i = 0;
			final DBObject current = cursor.next();

			String Crawling_Data = CmmUtil.nvl((String) current.get("information")); // 크롤링데이터

			rDTO.setInformation(Crawling_Data);

			jList.add(rDTO); // List에 저장

			rDTO = null;
			i++;
		}

		log.info(this.getClass().getName() + ".Chihuahua 셀렉트 매퍼 종료");

		return jList;

	}
}
