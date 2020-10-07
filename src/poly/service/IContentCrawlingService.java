package poly.service;

import java.util.List;

import poly.dto.TitleDTO;

public interface IContentCrawlingService {

	// 크롤링 실행 후 저장
	public int collectContentCrawling() throws Exception;

	// jsp로 넘길 값 가져오기
	public List<TitleDTO> getContentJspGo() throws Exception;

}
