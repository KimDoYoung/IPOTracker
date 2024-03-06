package kr.co.kfs.asseterp.ipo.tracking;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
		
		//ipoData.setIrData(nextSibling(table, "IR일자"));
        Element irDateElement = table.select("font:contains(IR일자)").first().parent().nextElementSibling();
        String irDate = irDateElement.text();
        ipoData.setIrData(irDate);

        // "IR장소/시간" 값 추출
        Element irLocationTime = table.select("font:contains(IR장소/시간)").first().parent().nextElementSibling();
        String irLocationAndTime = irLocationTime.text();
        ipoData.setIrLocationTime(irLocationAndTime);
        
        // "기관경쟁률" 값 추출
        Element institutionCompetitionRate = table.select("font:contains(기관경쟁률)").first().parent().nextElementSibling();
        String competitionRate = institutionCompetitionRate.text();
        ipoData.setInstitutionalCompetitionRate(competitionRate);
        
        // "의무보유확약" 값 추출
        Element mandatoryHoldingCommitment = table.select("font:contains(의무보유확약)").first().parent().nextElementSibling();
        String mandatoryHolding = mandatoryHoldingCommitment.text();        
        ipoData.setLockUpAgreement(mandatoryHolding);
        
        //수요예측 참여내역 -> 참여건수

        Element imgElement = doc.selectFirst("img[alt=수요예측 참여내역]");
		if (imgElement != null) {

			  // imgElement로부터 모든 조상 요소들을 가져온다.
	        Elements parents = imgElement.parents();
	        
	        // 가장 가까운 <table> 조상 요소를 찾는다.
	        Element closestTable = null;
	        for (Element parent : parents) {
	            if (parent.tagName().equals("table")) {
	                closestTable = parent;
	                break; // 가장 가까운 <table>을 찾으면 반복 종료
	            }
	        }
			if (closestTable != null) {
				Element currentTable = closestTable.nextElementSibling(); // 참여건수를 갖는 테이블

				Elements tds = currentTable.select("td");
				for (Element td : tds) {
					if ("참여건수 (단위:건)".equals(td.text())) {
						Element parentTr = td.parent(); // 현재 td의 부모인 <tr> 요소
						Element nextTr = parentTr.nextElementSibling(); // 찾은 <tr>의 바로 다음 형제 요소인 다음 <tr>

						if (nextTr != null) {
							Element valueTd = nextTr.child(0); // 다음 <tr>의 첫 번째 <td> 요소
							String value = valueTd.text(); // 이 <td>의 텍스트 값
							System.out.println("참여건수: " + value);
							break;
						}
					}
				}
			}
		}
        
        
        
        
        // "참여건수"에 해당하는 <td>를 찾고, 해당 <td>의 부모 <tr>의 바로 다음 <tr>에서 첫 번째 <td>의 텍스트 추출
//        Element participationCountElement = doc.select("td:contains(참여건수)").first();
//        Elements nextRowTds = participationCountElement.parent().nextElementSibling().select("td");
//        String participationCount = nextRowTds.first().text();
//        System.out.println("참여건수: " + participationCount);
		
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
