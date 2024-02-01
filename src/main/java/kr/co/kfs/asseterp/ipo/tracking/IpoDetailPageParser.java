package kr.co.kfs.asseterp.ipo.tracking;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class IpoDetailPageParser {

	private Document doc ;
	
	public IpoDetailPageParser(Document doc) {
		this.doc = doc;
	}
	
	public IpoData parse() {

		IpoData  ipoData =new IpoData();
		
		//summary="기업개요"
		Element table =doc.selectFirst("table[summary=기업개요]");
		
		ipoData.setStockName(nextSibling(table, "종목명"));
		ipoData.setStatus(nextSibling(table, "진행상황"));
		ipoData.setMarketType(nextSibling(table, "시장구분"));
		ipoData.setStockCode(nextSibling(table, "종목코드"));
		ipoData.setIndustry(nextSibling(table, "업종"));
		ipoData.setCeo(nextSibling(table, "대표자"));
		ipoData.setBusinessType(nextSibling(table, "기업구분"));
		ipoData.setHeadquartersLocation(nextSibling(table, "본점소재지"));
		ipoData.setWebsite(nextSibling(table, "홈페이지"));
		ipoData.setPhoneNumber(nextSibling(table, "대표전화"));
		ipoData.setMajorShareholder(nextSibling(table, "최대주주"));
		ipoData.setRevenue(nextSibling(table, "매출액"));
		ipoData.setPreTaxContinuingOperationsProfit(nextSibling(table, "법인세비용차감전"));
		ipoData.setNetProfit(nextSibling(table, "순이익"));
		ipoData.setCapital(nextSibling(table, "자본금"));


		table =doc.selectFirst("table[summary=공모정보]");
		
		ipoData.setTotalIpoShares(nextSibling(table, "총공모주식수"));
		ipoData.setFaceValue(nextSibling(table, "액면가"));
		ipoData.setListingIpo(nextSibling(table, "상장공모"));
		ipoData.setDesiredIpoPrice(nextSibling(table, "희망공모가액"));
		ipoData.setSubscriptionCompetitionRate(nextSibling(table, "청약경쟁률"));
		ipoData.setFinalIpoPrice(nextSibling(table, "확정공모가"));
		ipoData.setIpoProceeds(nextSibling(table, "공모금액"));
		ipoData.setLeadManager(nextSibling(table, "주간사"));
		
		table =doc.selectFirst("table[summary=공모청약일정]");
		
		ipoData.setDemandForecastDate(nextSibling(table, "수요예측일"));
		ipoData.setIpoSubscriptionDate(nextSibling(table, "공모청약일"));
		ipoData.setNewspaperAllocationAnnouncementDate(nextSibling(table, "배정공고일(신문)"));
		ipoData.setPaymentDate(nextSibling(table, "납입일"));
		ipoData.setRefundDate(nextSibling(table, "환불일"));
		ipoData.setListingDate(nextSibling(table, "상장일"));
		ipoData.setIrData(nextSibling(table, "IR일자"));
		ipoData.setIrLocationTime(nextSibling(table, "IR장소/시간"));
		ipoData.setInstitutionalCompetitionRate(nextSibling(table, "기관경쟁률"));
		ipoData.setLockUpAgreement(nextSibling(table, "의무보유확약"));

		
		return ipoData;
	}

	private String nextSibling(Element parent, String name) {
		String selector = String.format("td:contains(%s)", name);
		Element elem = parent.select(selector).first();
		if(elem == null) {
			System.err.println(name + " 은 값이 null입니다. " + selector);
			return "";
		}else if (elem.nextElementSibling() == null){
			System.err.println("elem.nextElementSibling() 은 값이 null입니다. " + selector);
			return "";
		}else {
			String text = elem.nextElementSibling().text();
			return text;
		}
	}

	
}
