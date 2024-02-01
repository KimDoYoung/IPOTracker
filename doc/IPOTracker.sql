SELECT * FROM public.ipo_data id ;
DELETE FROM  public.ipo_data ;

DROP TABLE IF EXISTS public.ipo_data;
CREATE TABLE IF NOT EXISTS  public.ipo_data (
	seq serial NOT NULL PRIMARY KEY,
    track_id VARCHAR(250) ,
    stock_name VARCHAR(250) ,  -- 종목명
    status VARCHAR(250),  -- 진행상황
    market_type VARCHAR(250),  -- 시장구분
    stock_code VARCHAR(250),  -- 종목코드
    industry VARCHAR(250),  -- 업종
    ceo VARCHAR(250),  -- 대표자
    business_type VARCHAR(250),  -- 기업구분
    headquarters_location VARCHAR(250),  -- 본점소재지
    website VARCHAR(250),  -- 홈페이지
    phone_number VARCHAR(250),  -- 대표전화
    major_shareholder VARCHAR(250),  -- 최대주주
    revenue VARCHAR(250),  -- 매출액
    pre_tax_continuing_operations_profit VARCHAR(250),  -- 법인세비용차감전 계속사업이익
    net_profit VARCHAR(250),  -- 순이익
    capital VARCHAR(250),  -- 자본금
    total_ipo_shares VARCHAR(250),  -- 총공모주식수
    face_value VARCHAR(250),  -- 액면가
    listing_ipo VARCHAR(250),  -- 상장공모
    desired_ipo_price VARCHAR(250),  -- 희망공모가액
    subscription_competition_rate VARCHAR(250),  -- 청약경쟁률
    final_ipo_price VARCHAR(250),  -- 확정공모가
    ipo_proceeds VARCHAR(250),  -- 공모금액
    lead_manager VARCHAR(250),  -- 주간사
    demand_forecast_date VARCHAR(250),  -- 수요예측일
    ipo_subscription_date VARCHAR(250),  -- 공모청약일
    newspaper_allocation_announcement_date VARCHAR(250),  -- 배정공고일(신문)
    payment_date VARCHAR(250),  -- 납입일
    refund_date VARCHAR(250),  -- 환불일
    listing_date VARCHAR(250),  -- 상장일
    ir_data VARCHAR(250),  -- IR일자
    ir_location_time VARCHAR(250),  -- IR장소/시간
    institutional_competition_rate VARCHAR(250),  -- 기관경쟁률
    lock_up_agreement VARCHAR(250)  -- 의무보유확약
);

COMMENT ON COLUMN public.ipo_data.track_id IS '트랙 ID';
COMMENT ON COLUMN public.ipo_data.stock_name IS '회사 개요: 종목명';
COMMENT ON COLUMN public.ipo_data.status IS '회사 개요: 진행상황';
COMMENT ON COLUMN public.ipo_data.market_type IS '회사 개요: 시장구분';
COMMENT ON COLUMN public.ipo_data.stock_code IS '회사 개요: 종목코드';
COMMENT ON COLUMN public.ipo_data.industry IS '회사 개요: 업종';
COMMENT ON COLUMN public.ipo_data.ceo IS '회사 개요: 대표자';
COMMENT ON COLUMN public.ipo_data.business_type IS '회사 개요: 기업구분';
COMMENT ON COLUMN public.ipo_data.headquarters_location IS '회사 개요: 본점소재지';
COMMENT ON COLUMN public.ipo_data.website IS '회사 개요: 홈페이지';
COMMENT ON COLUMN public.ipo_data.phone_number IS '회사 개요: 대표전화';
COMMENT ON COLUMN public.ipo_data.major_shareholder IS '회사 개요: 최대주주';
COMMENT ON COLUMN public.ipo_data.revenue IS '회사 개요: 매출액';
COMMENT ON COLUMN public.ipo_data.pre_tax_continuing_operations_profit IS '회사 개요: 법인세비용차감전 계속사업이익';
COMMENT ON COLUMN public.ipo_data.net_profit IS '회사 개요: 순이익';
COMMENT ON COLUMN public.ipo_data.capital IS '회사 개요: 자본금';
COMMENT ON COLUMN public.ipo_data.total_ipo_shares IS 'IPO 정보: 총공모주식수';
COMMENT ON COLUMN public.ipo_data.face_value IS 'IPO 정보: 액면가';
COMMENT ON COLUMN public.ipo_data.listing_ipo IS 'IPO 정보: 상장공모';
COMMENT ON COLUMN public.ipo_data.desired_ipo_price IS 'IPO 정보: 희망공모가액';
COMMENT ON COLUMN public.ipo_data.subscription_competition_rate IS 'IPO 정보: 청약경쟁률';
COMMENT ON COLUMN public.ipo_data.final_ipo_price IS 'IPO 정보: 확정공모가';
COMMENT ON COLUMN public.ipo_data.ipo_proceeds IS 'IPO 정보: 공모금액';
COMMENT ON COLUMN public.ipo_data.lead_manager IS 'IPO 정보: 주간사';
COMMENT ON COLUMN public.ipo_data.demand_forecast_date IS '청약 일정: 수요예측일';
COMMENT ON COLUMN public.ipo_data.ipo_subscription_date IS '청약 일정: 공모청약일';
COMMENT ON COLUMN public.ipo_data.newspaper_allocation_announcement_date IS '청약 일정: 배정공고일(신문)';
COMMENT ON COLUMN public.ipo_data.payment_date IS '청약 일정: 납입일';
COMMENT ON COLUMN public.ipo_data.refund_date IS '청약 일정: 환불일';
COMMENT ON COLUMN public.ipo_data.listing_date IS '청약 일정: 상장일';
COMMENT ON COLUMN public.ipo_data.ir_data IS '청약 일정: IR일자';
COMMENT ON COLUMN public.ipo_data.ir_location_time IS '청약 일정: IR장소/시간';
COMMENT ON COLUMN public.ipo_data.institutional_competition_rate IS '청약 일정: 기관경쟁률';
COMMENT ON COLUMN public.ipo_data.lock_up_agreement IS '청약 일정: 의무보유확약';

SELECT * FROM public.ipo_data id ;