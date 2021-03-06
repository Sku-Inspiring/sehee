### 🛠 Skill Stack
* Language : Java
* FrameWork : Spring
* Web : HTML, CSS
* Database : Oracle
* OS : Windows
* OpenSource : Bootstrap
* IDE : IntelliJ
* Connection pool : HikariCP 

### 👀 Software Architecture
![image](https://user-images.githubusercontent.com/53335160/113684193-f4a1bf80-96ff-11eb-8007-0d35da2595e1.png)

### 💡 핵심기능
1️⃣ **재고 관리** - 현재 재고 조회, 재고 수량 조정, 부족한 재고 확인<br>
2️⃣ **입출고 관리 및 통계** - 기간별 입출고 수량 집계, 입출고 추이 그래프<br>
3️⃣ **재고 교환** - 사업자끼리 남는 재고 교환

### 💡 문제와 해결방법
문제: 크롬으로 켰을때 큰 화면에서 bootstrap 오류로 드롭다운이 계속 펼쳐져있는 상태<br>
해결방법: header.jsp파일에서 버전을 3.5.1로 바꿈

문제: 오라클 DBMS에서 비밀번호 만료 오류(오류번호-28001)<br>

해결방법
1. system 계정으로 오라클 접속
2. 연결되어있는 DB 계정 변경 - ALTER USER user_id IDENTIFIED BY new_password;
3. ALTER PROFILE DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED; (비번 만료 거부 설정)

### ✨ RestAPI
![image](https://user-images.githubusercontent.com/53335160/113683275-fae36c00-96fe-11eb-9757-0c39d3bb9591.png)

### ✨ Pakage
org.zerock.config – 설정 클래스들의 보관 패키지<br>
org.zerock.controller - MVC의 컨트롤러들의 보관 패키지<br>
org.zerock.service – Service 인터페이스와 구현 클래스 패키지<br>
org.zerock.domain – VO, DTO 클래스들의 패키지<br>
org.zerock.security – 스프링 security 관련 패키지<br>
org.zerock.aop – 스프링 AOP 관련 패키지<br>
org.zerock.mapper – Mybatis Mapper 인터페이스 패키지<br>

### ✨ ERD
![화면 캡처 2021-04-17 222628](https://user-images.githubusercontent.com/53335160/115114790-0b7fc600-9fcc-11eb-96ec-dd902d8c6ea2.png)

### 🤞 Docker
https://subicura.com/2017/01/19/docker-guide-for-beginners-1.html
