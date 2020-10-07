package poly.service;

import java.util.List;

import poly.dto.TitleDTO;

public interface ITitleCrawlingService {

	// 크롤링 실행 후 저장
	public int collectTitleCrawling() throws Exception;

	// DB에 있는 크롤링 Select
	public List<TitleDTO> getTitleJspGo() throws Exception;

}
