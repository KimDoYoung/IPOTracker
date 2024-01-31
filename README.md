# IPOTracker

## 개요

- IPO관련정보를 crawling해서 데이터를 가지고 온 후에 postgresq table에 넣는다.
- crawling부분과 insertdb 두 부분으로 나누어서 작성된다.

## 개발환경

- jdk 1.8
- command line
- 위치 ~/ws-assetedu-3.0/IPOTracker
- 컴파일 : 
	./gradlew clean customFatJar
	cd ./build/libs
	java -jar all-in-one-jar-1.0-SNAPSHOT.jar


