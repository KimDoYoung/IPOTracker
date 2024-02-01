package kr.co.kfs.asseterp.ipo.tracking;

import lombok.Data;

@Data
public class IpoConfig {

	private Integer pageCount; //리스트페이지 갯수
	private String  outputFolder; // crawling 데이터가 쌓을 폴더
	
    private String dbUrl; //dburl
    private String dbUsername; //db user name
    private String dbPassword; //db password
    private String tableName;
    
}
