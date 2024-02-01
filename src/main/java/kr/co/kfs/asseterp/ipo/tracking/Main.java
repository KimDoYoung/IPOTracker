package kr.co.kfs.asseterp.ipo.tracking;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final String START_URL = "https://www.38.co.kr/html/fund/index.htm?o=r";
	private static DetailPageLinks detailPageLinks = null; 
	private static IpoConfig ipoConfig = null;
	
	Logger logger = LoggerFactory.getLogger(Main.class);
    public static void main(String[] args) throws IOException {
    
    	//argument
        if (args.length < 1) {
            System.out.println("Usage: java -jar IPOTracker <properties file>");
            return;
        }
        //config
        ipoConfig = new IpoConfig();
        
        String propertiesFile = args[0];
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(propertiesFile)) {
            // Properties 파일 로딩
            prop.load(input);

            // 데이터베이스 연결 정보 읽기
            String pageCount= prop.getProperty("page.count");
            String outputFolder= prop.getProperty("output.folder");
            String url = prop.getProperty("db.url");
            String user = prop.getProperty("db.username");
            String password = prop.getProperty("db.password");
            String tableName = prop.getProperty("db.tablename");
            Integer iPageCount = Integer.parseInt(pageCount);
            
            ipoConfig.setPageCount(iPageCount);
            ipoConfig.setOutputFolder(outputFolder);
            ipoConfig.setDbUrl(url);
            ipoConfig.setDbUsername(user);
            ipoConfig.setDbPassword(password);
            ipoConfig.setTableName(tableName);
            
        } catch (IOException ex) {
        	System.err.println(ex.getMessage()+"\n");
            ex.printStackTrace();
        }
        String trackId = newTrackId();
        
        System.out.println("-----------------------------------------------------------");
        System.out.println("- site : https://www.38.co.kr traking :" + trackId);
        System.out.println("-----------------------------------------------------------");
    	//각페이지를 crawling해서 IpoData를 만들고 리스트에 넣는다.
    	detailPageLinks = new DetailPageLinks(START_URL);
    	List<IpoData> ipoDataList = crawl();
    	
    	
    	for (IpoData ipoData : ipoDataList) {
    		ipoData.setTrackId(trackId);
		}
        
    	//file에 write한다.
    	String fileName = writeToFile(trackId, ipoDataList);
    	
    	//db에 insert
    	insertToTable(ipoDataList);

        System.out.println("-----------------------------------------------------------");
        String msg = String.format("DONE! trackingId:%s, tracking count : %d", trackId, ipoDataList.size());
        System.out.println(msg);
        msg = String.format("output file : %s, table : ",fileName, ipoConfig.getTableName());
        System.out.println("-----------------------------------------------------------");
    }

    private static String writeToFile(String trackId, List<IpoData> ipoDataList) {
    	String fileName = ipoConfig.getOutputFolder()+"/"+ trackId + ".dat";
    	
    	 try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), StandardCharsets.UTF_8))) {
            // 파일에 문자열 작성
        	for (IpoData ipoData : ipoDataList) {
        		writer.write(ipoData.toString());
			}
        } catch (IOException e) {
            e.printStackTrace();
        }		
    	return fileName;
	}

    /**
     * ipoConfig에 기술된 db에 write한다
     * @param ipoDataList
     */
	private static void insertToTable(List<IpoData> ipoDataList) {
		String url = ipoConfig.getDbUrl();
		String username = ipoConfig.getDbUsername();
		String password = ipoConfig.getDbPassword();
		String tableName = ipoConfig.getTableName();
		
		DatabaseWorker dbWorker = new DatabaseWorker(url, username, password, tableName);
		
		for (IpoData ipoData : ipoDataList) {

			try {
				dbWorker.insert(ipoData);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    	
	}

	/**
     * crawling ID : yyyyMMddHHmmddss
     * @return
     */
    private static String newTrackId() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date now = new Date();
        String formattedDate = dateFormat.format(now);
        return formattedDate;
	}
    
    private static List<IpoData> crawl() throws IOException {
    	List<String> list = gatherAllDetailPageAnchor();
    	detailPageLinks.add(list);

    	List<IpoData> dataList = new ArrayList<>();
    	
    	for (String pageUrl : detailPageLinks.getLinks()) {
    		Document doc = Jsoup.connect(pageUrl).get();
    		IpoDetailPageParser parser = new IpoDetailPageParser(doc);
			IpoData ipoData = parser.parse();
			dataList.add(ipoData);
		}
    	return dataList;
    }


	/**
     * 수요예측 일정 리스트를 정해진 페이지 갯수만큼 가져와서 링크를 뽑아낸다.
     * @throws IOException
     */
	private static List<String> gatherAllDetailPageAnchor() throws IOException {
		List<String> list = new ArrayList<>();
		int pageCount = ipoConfig.getPageCount();
        for(int i=1; i <= pageCount; i++) {
        	String url = START_URL + "&page=" + i;
        	Document doc = Jsoup.connect(url).get();
        	Element table = doc.select("table[summary=수요예측일정]").first();

             if (table != null) {
                 // 테이블 내의 anchor 요소들을 추출합니다.
                 Elements links = table.select("a");

                 for (Element link : links) {
                     String href = link.attr("href");
//                     String text = link.text();
                     list.add(href);
                 }
             } else {
                 System.out.println("해당 summary 속성을 가진 테이블을 찾을 수 없습니다.");
             }        	
        }
        return list;
	}
}
