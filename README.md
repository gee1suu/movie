# Movie-Tickets-Booking-System
[영화 예매 프로그램](https://github.com/gee1suu/movie-booking)의 리팩토링
## 주제
영화 정보를 조회하고 영화를 예매하고 영화의 평점을 남길 수 있는 사이트
## 메뉴 구성
+ **공통**: 메인(예매율 순 1 ~ 10위인 영화 목록 조회)
+ **회원 관리**: 회원가입, 로그인, 비밀번호 찾기(메일 인증), 회원정보 수정, 비밀번호 변경, 회원탈퇴, 내 평점 목록, 예매 내역
+ **영화 조회**: 전체영화(현재상영작, 상영예정작), 영화상세
+ **영화 예매**: 영화, 상영관, 시간표, 관객종류, 좌석, 결제수단 선택
## 개발 환경
+ **프로젝트 기간**: 2023.08.29 ~ 2023.09.04
+ **개발 웹서버**: Apache Tomcat 9.0
+ **개발 프레임워크**: Spring Boot 2.7.15, JPA, Thymeleaf, Spring Security, Java Mail
+ **개발 툴**: IntelliJ IDEA, Oracle SQL Developer
+ **사용 언어**: Java(JDK 1.8), SQL, HTML/CSS, JavaScript
## 데이터베이스(DB)
![DB_ERD](https://github.com/gee1suu/movie/assets/80879666/20961385-628e-4b25-b122-9922cb9288c5)
+ [이전의 데이터베이스](https://github.com/gee1suu/movie-booking#%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4db)의 테이블들(멤버십, 관람등급, 장르, 영화종류에 따른 가격, 사람종류에 따른 가격, 결제수단)을 공통 코드 테이블로 묶어서 재설계
+ [이전의 데이터베이스](https://github.com/gee1suu/movie-booking#%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4db)의 좌석, 시간표별 좌석 테이블이 불필요한 테이블인 것을 확인하고 티켓(Ticket) 테이블의 column으로 통합
## 사이트 예시
![chrome-capture-2023-8-4](https://github.com/gee1suu/movie/assets/80879666/522b4bee-591d-4af7-82fb-95ff3813272b)
![chrome-capture-2023-8-4 (1)](https://github.com/gee1suu/movie/assets/80879666/15b97b1e-4d8d-4d61-af55-9bbe88cd3992)
![chrome-capture-2023-8-4 (2)](https://github.com/gee1suu/movie/assets/80879666/b3ab89ee-ea40-4203-9eff-31cb9c6761ae)
![chrome-capture-2023-8-4 (3)](https://github.com/gee1suu/movie/assets/80879666/f155b3db-64cb-42df-885a-966211d17ea2)
![chrome-capture-2023-8-4 (4)](https://github.com/gee1suu/movie/assets/80879666/adb695af-78db-46ea-8e18-2f87494d9dbe)
![chrome-capture-2023-8-4 (5)](https://github.com/gee1suu/movie/assets/80879666/4821d7a8-8ed3-41ef-8601-d3dad1004436)
![chrome-capture-2023-8-4 (6)](https://github.com/gee1suu/movie/assets/80879666/3d210684-0a12-4bc3-947f-6f200dce839e)
