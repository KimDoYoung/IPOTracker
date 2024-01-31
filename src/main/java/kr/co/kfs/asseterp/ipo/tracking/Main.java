package kr.co.kfs.asseterp.ipo.tracking;

import java.io.FileWriter;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final String START_URL = "https://www.38.co.kr/html/fund/index.htm?o=r";
	private static final String FILE_PATH = "C:/Users/deHong/tmp/ipo.txt";
	
    public static void main(String[] args) throws IOException {
        final Logger logger = LoggerFactory.getLogger(Main.class);
        logger.debug("");
        // Step 1: Crawl the website and save data to a text file
        crawlAndSaveToFile();

        // Step 2: Insert data into PostgreSQL
        insertDataIntoPostgres();

        System.out.println("Web Crawling and Data Insertion completed successfully!");
    }

    private static void insertDataIntoPostgres() throws IOException {
        Document doc = Jsoup.connect(START_URL).get();
        Elements links = doc.select("a[href]");

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            for (Element link : links) {
                writer.write("Link: " + link.attr("href") + "\n");
            }
        }
    }

    private static void crawlAndSaveToFile() {
        System.out.println("insert db");

    }
}
