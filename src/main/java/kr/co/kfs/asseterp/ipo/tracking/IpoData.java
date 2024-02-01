package kr.co.kfs.asseterp.ipo.tracking;

import lombok.Data;

@Data
public class IpoData {

	private String trackId;

	// 기업 개요 | Company Overview:
	private String stockName; // 종목명
	private String status; // 진행상황
	private String marketType; // 시장구분
	private String stockCode; // 종목코드
	private String industry; // 업종
	private String ceo; // 대표자
	private String businessType; // 기업구분
	private String headquartersLocation; // 본점소재지
	private String website; // 홈페이지
	private String phoneNumber; // 대표전화
	private String majorShareholder; // 최대주주
	private String revenue; // 매출액
	private String preTaxContinuingOperationsProfit; // 법인세비용차감전 계속사업이익
	private String netProfit; // 순이익
	private String capital; // 자본금

	// 공모 정보 | IPO Information:
	private String totalIpoShares; // 총공모주식수
	private String faceValue; // 액면가
	private String listingIpo; // 상장공모
	private String desiredIpoPrice; // 희망공모가액
	private String subscriptionCompetitionRate; // 청약경쟁률
	private String finalIpoPrice; // 확정공모가
	private String ipoProceeds; // 공모금액
	private String leadManager; // 주간사

	// 청약 일정 | Subscription Schedule:
	private String demandForecastDate; // 수요예측일
	private String ipoSubscriptionDate; // 공모청약일
	private String newspaperAllocationAnnouncementDate; // 배정공고일(신문)
	private String paymentDate; // 납입일
	private String refundDate; // 환불일
	private String listingDate; // 상장일
	private String irData;// IR일자
	private String irLocationTime; // IR장소/시간
	private String institutionalCompetitionRate; // 기관경쟁률
	private String lockUpAgreement; // 의무보유확약

    @Override
    public String toString() {
    	return String.format("-----TrackId:%s------\n", getTrackId()) +
        	   String.format("종목명(stockName)=%s\n", getStockName()) +
               String.format("진행상황(status)=%s\n", getStatus()) +
               String.format("시장구분(marketType)=%s\n", getMarketType()) +
               String.format("종목코드(stockCode)=%s\n", getStockCode()) +
               String.format("업종(industry)=%s\n", getIndustry()) +
               String.format("대표자(ceo)=%s\n", getCeo()) +
               String.format("기업구분(businessType)=%s\n", getBusinessType()) +
               String.format("본점소재지(headquartersLocation)=%s\n", getHeadquartersLocation()) +
               String.format("홈페이지(website)=%s\n", getWebsite()) +
               String.format("대표전화(phoneNumber)=%s\n", getPhoneNumber()) +
               String.format("최대주주(majorShareholder)=%s\n", getMajorShareholder()) +
               String.format("매출액(revenue)=%s\n", getRevenue()) +
               String.format("법인세비용차감전 계속사업이익(preTaxContinuingOperationsProfit)=%s\n", getPreTaxContinuingOperationsProfit()) +
               String.format("순이익(netProfit)=%s\n", getNetProfit()) +
               String.format("자본금(capital)=%s\n", getCapital()) +
               String.format("총공모주식수(totalIpoShares)=%s\n", getTotalIpoShares()) +
               String.format("액면가(faceValue)=%s\n", getFaceValue()) +
               String.format("상장공모(listingIpo)=%s\n", getListingIpo()) +
               String.format("희망공모가액(desiredIpoPrice)=%s\n", getDesiredIpoPrice()) +
               String.format("청약경쟁률(subscriptionCompetitionRate)=%s\n", getSubscriptionCompetitionRate()) +
               String.format("확정공모가(finalIpoPrice)=%s\n", getFinalIpoPrice()) +
               String.format("공모금액(ipoProceeds)=%s\n", getIpoProceeds()) +
               String.format("주간사(leadManager)=%s\n", getLeadManager()) +
               String.format("수요예측일(demandForecastDate)=%s\n", getDemandForecastDate()) +
               String.format("공모청약일(ipoSubscriptionDate)=%s\n", getIpoSubscriptionDate()) +
               String.format("배정공고일(신문)(newspaperAllocationAnnouncementDate)=%s\n", getNewspaperAllocationAnnouncementDate()) +
               String.format("납입일(paymentDate)=%s\n", getPaymentDate()) +
               String.format("환불일(refundDate)=%s\n", getRefundDate()) +
               String.format("상장일(listingDate)=%s\n", getListingDate()) +
               String.format("IR일자(irData)=%s\n", getIrData()) +
               String.format("IR장소/시간(irLocationTime)=%s\n", getIrLocationTime()) +
               String.format("기관경쟁률(institutionalCompetitionRate)=%s\n", getInstitutionalCompetitionRate()) +
               String.format("의무보유확약(lockUpAgreement)=%s\n", getLockUpAgreement());
    }
	
}
