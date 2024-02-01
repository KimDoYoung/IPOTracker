package kr.co.kfs.asseterp.ipo.tracking;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWorker {

	private final String driverName = "org.postgresql.Driver";
	private String password;
	private String username;
	private String url;
	private String tableName;
	
	private Connection connection = null;

	public DatabaseWorker(String url, String username, String password, String tableName) {
		this.url = url;
		this.username = username;
		this.password = password;
		this.tableName = tableName;
	}
	
	public void insert(IpoData ipoData) throws SQLException {
		connection = getConnection();
		String sql = "INSERT INTO "+this.tableName+" (track_id, stock_name, status, market_type, stock_code" 
				+ ",industry, ceo, business_type, headquarters_location, website, phone_number"
				+ ",major_shareholder, revenue, pre_tax_continuing_operations_profit, net_profit, capital "
				+ ",total_ipo_shares, face_value, listing_ipo, desired_ipo_price, subscription_competition_rate "
				+ ",final_ipo_price, ipo_proceeds, lead_manager, demand_forecast_date, ipo_subscription_date "
				+ ",newspaper_allocation_announcement_date, payment_date, refund_date, listing_date, ir_data "
				+ ",ir_location_time, institutional_competition_rate, lock_up_agreement "				
				+")VALUES("
				+"?,?,?,?,?"
				+",?,?,?,?,?,?"
				+",?,?,?,?,?"
				+",?,?,?,?,?"
				+",?,?,?,?,?"
				+",?,?,?,?,?"
				+",?,?,?"
				+ ")";
		try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			// 한글 주석 추가
			preparedStatement.setString(1, ipoData.getTrackId()); // 트랙 ID
			preparedStatement.setString(2, ipoData.getStockName()); // 종목명
			preparedStatement.setString(3, ipoData.getStatus()); // 진행상황
			preparedStatement.setString(4, ipoData.getMarketType()); // 시장구분
			preparedStatement.setString(5, ipoData.getStockCode()); // 종목코드
			preparedStatement.setString(6, ipoData.getIndustry()); // 업종
			preparedStatement.setString(7, ipoData.getCeo()); // 대표자
			preparedStatement.setString(8, ipoData.getBusinessType()); // 기업구분
			preparedStatement.setString(9, ipoData.getHeadquartersLocation()); // 본점소재지
			preparedStatement.setString(10, ipoData.getWebsite()); // 홈페이지
			preparedStatement.setString(11, ipoData.getPhoneNumber()); // 대표전화
			preparedStatement.setString(12, ipoData.getMajorShareholder()); // 최대주주
			preparedStatement.setString(13, ipoData.getRevenue()); // 매출액
			preparedStatement.setString(14, ipoData.getPreTaxContinuingOperationsProfit()); // 법인세비용차감전 계속사업이익
			preparedStatement.setString(15, ipoData.getNetProfit()); // 순이익
			preparedStatement.setString(16, ipoData.getCapital()); // 자본금
			preparedStatement.setString(17, ipoData.getTotalIpoShares()); // 총공모주식수
			preparedStatement.setString(18, ipoData.getFaceValue()); // 액면가
			preparedStatement.setString(19, ipoData.getListingIpo()); // 상장공모
			preparedStatement.setString(20, ipoData.getDesiredIpoPrice()); // 희망공모가액
			preparedStatement.setString(21, ipoData.getSubscriptionCompetitionRate()); // 청약경쟁률
			preparedStatement.setString(22, ipoData.getFinalIpoPrice()); // 확정공모가
			preparedStatement.setString(23, ipoData.getIpoProceeds()); // 공모금액
			preparedStatement.setString(24, ipoData.getLeadManager()); // 주간사
			preparedStatement.setString(25, ipoData.getDemandForecastDate()); // 수요예측일
			preparedStatement.setString(26, ipoData.getIpoSubscriptionDate()); // 공모청약일
			preparedStatement.setString(27, ipoData.getNewspaperAllocationAnnouncementDate()); // 배정공고일(신문)
			preparedStatement.setString(28, ipoData.getPaymentDate()); // 납입일
			preparedStatement.setString(29, ipoData.getRefundDate()); // 환불일
			preparedStatement.setString(30, ipoData.getListingDate()); // 상장일
			preparedStatement.setString(31, ipoData.getIrData()); // IR일자
			preparedStatement.setString(32, ipoData.getIrLocationTime()); // IR장소/시간
			preparedStatement.setString(33, ipoData.getInstitutionalCompetitionRate()); // 기관경쟁률
			preparedStatement.setString(34, ipoData.getLockUpAgreement()); // 의무보유확약

			preparedStatement.executeUpdate();

		}
	}
	
	private Connection getConnection() {
		if (this.connection != null)
			return this.connection;
		try {
			Class.forName(this.driverName);
			connection = DriverManager.getConnection(this.url, this.username, this.password);
			return connection;
		} catch (SQLException e) {
			this.connection = null;
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			this.connection = null;
			e.printStackTrace();
		}
		return connection;
	}

}
