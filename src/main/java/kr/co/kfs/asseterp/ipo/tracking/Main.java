package kr.co.kfs.asseterp.ipo.tracking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
    public static void main(String[] args) {
        final Logger logger = LoggerFactory.getLogger(Main.class);
        // Step 1: Crawl the website and save data to a text file
        crawlAndSaveToFile();

        // Step 2: Insert data into PostgreSQL
        insertDataIntoPostgres();

        System.out.println("Web Crawling and Data Insertion completed successfully!");
    }

    private static void insertDataIntoPostgres() {
        System.out.println("crawling...");

    }

    private static void crawlAndSaveToFile() {
        System.out.println("insert db");

    }
}
