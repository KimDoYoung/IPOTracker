# IPOTracker

## 개요

- IPO관련정보를 사이트 [https://www.38.co.kr/html/fund/index.htm?o=r](https://www.38.co.kr/html/fund/index.htm?o=r)에서 crawling해서 데이터를 가지고 온 후에 postgresq table에 넣는다.
- crawling부분과 insertdb 두 부분으로 나누어서 작성된다.
- gradle로 빌드
```
	./gradlew clean customFatJar
```

## 사용방법

- Usage: java -jar IPOTracker <properties file>

- properties file 내용
```
page.count=5
output.folder=C:/Users/deHong/tmp
db.url=jdbc:postgresql://localhost:5432/fbhdb
db.username=kdy987
db.password=kalpa987!
db.tablename=public.ipo_data
```

## info

```
https://www.38.co.kr/html/fund/index.htm?o=r

1. IPO/공모->수요예측일정->목록
2. 상세 
   2.1 기업개요
   2.2 공모정보
   2.3 청약일정

2페이지
https://www.38.co.kr/html/fund/index.htm?o=r&page=2
https://www.38.co.kr/html/fund/?o=v&no=2028&l=&page=1
https://www.38.co.kr/html/fund/?o=v&no=2029&l=&page=1
```

## 개발환경

- jdk 1.8
- command line
- 위치 ~/ws-assetedu-3.0/IPOTracker
- 컴파일 : 
	./gradlew clean customFatJar
	cd ./build/libs
	java -jar all-in-one-jar-1.0-SNAPSHOT.jar


