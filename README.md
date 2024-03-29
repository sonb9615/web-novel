# 📔 웹소설 서비스 클론코딩 프로젝트

- 백엔드 개발자로서, 테이블 설계, API설계, API 개발을 담당하였습니다.
<br>

## 📌 프로젝트 고려사항

- 동시성 이슈

 공유된 자원에 동시에 여러 작업들이 접근하는 과정에서 동시성 이슈가 발생 할 수 있습니다. 이는 프로그램의 성능 저하를 일으키는 원인이 되므로 해당 이슈를 고려한 설계를 진행해야합니다. 캐시와 소설 이용권 사용 로직에 해당 이슈를 고려하여 설계하였습니다.

- Redis 활용

 트랜잭션의 수가 많은 이벤트의 경우 DB를 사용하는 것이 성능면에서 좋지 않다고 판단하였습니다. 그렇기 때문에 Redis를 사용하여 속도개션이 이루어질 수 있도록 설계하였습니다. 랭킹 서비스 구현 시, 고려하여 설계하였습니다.

<br>

## 📌 테이블 설계
![web-novel-230720](https://github.com/sonb9615/web-novel/assets/50348496/2c167935-c428-47f3-8ad1-f3d57a067392)

<br>

## 📌 API 문서
[Web Novel Service API문서](https://www.notion.so/rini--/bdaa86b7ef6e42baa966abb2c6c239b3?v=18c0010bc5bb4cfab50d6c7038a3caa5&pvs=4)

<br>

## 📌 Project Spec
    - Java11
    - Spring boot 2.7
    - JPA
    - MySQL
    - Redis
    - Docker

    
