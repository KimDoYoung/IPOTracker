package kr.co.kfs.asseterp.ipo.tracking;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DetailPageLinks {
	private String baseLink;
	private URL url;
	private String basePath; 
	
	private List<String> list;
	
	public DetailPageLinks(String baseLink) throws MalformedURLException {
		
		setBaseLink(baseLink);
		
		list = new ArrayList<>();
	}
	private void setBaseLink(String baseLink) throws MalformedURLException {
//        String urlStr = "https://www.38.co.kr/html/fund/index.htm?o=r";
        this.baseLink = baseLink;
        this.url = new URL(this.baseLink);
        this.basePath = baseLink.substring(0, baseLink.lastIndexOf('/')); 
	}
	public void add(String link) {
		String fLink = fullLink(link);
		list.add(fLink);
	}
	private String fullLink(String path) {
		String newUrl;
		if(path.startsWith(".")) {
			newUrl = this.basePath + path.substring(1);
		}else {
			newUrl = path;
		}
		return newUrl;
	}
	public void add(List<String> list) {
		for (String url : list) {
			add(url);
		}
		
	}
	public String[] getLinks() {
		return list.toArray(new String[0]);
	}
}
