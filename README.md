# SPRING_MAP
스프링을 이용한 다이로움 지도 만들기

# 1. 프로젝트 개요

- **프로젝트 명칭** : Diroum_Map
- **프로젝트 목적** : 개인 사이드 프로젝트로 Spring 사용 경험 및 숙련도 향상을 하여 진행함
- **프로젝트 소개** : 지역화폐 사용자들이 혜택을 받을 수 있는 지도를 한 눈에 볼 수 있도록 하고 소통도 할 수 있는 웹 사이트
- **개발 인원** : 1명(송유진)
- **주요 기능**
  - 폼 로그인 기능
  - 회원가입 기능
  - 게시판 기능(작성, 수정, 읽기, 삭제)
  - 게시글 좋아요 기능
  - 게시물 댓글 기능(작성, 삭제)
  - 관리자 기능
    - 유저 관리
    - 업체 관리
  - 지도 기능

- **백엔드 개발 언어** : `Java 8`
- **백엔드 개발 환경**
  - `IntelliJ IDEA Ultimate`
  - `SpringBoot`
  - `gradle`
  - `jpa(Spring Data JPA)`
  - `Spring Security`

- **프론트 개발 환경 및 언어**
  - `html`
  - `thymeleaf`
  - `javascript`
  - `jquery`
 
- **데이터베이스** : `MySQL`
- **형상관리** : `GitHub`
- **이슈관리** : `GitHub`

# 2. 프로젝트 요구사항

###### **주요 사항**
- 폼 로그인 기능
- 회원가입 유효성 검사
- 게시물 좋아요 기능
- 게시물 CRUD 기능
- 게시물 검색 기능
- 게시물 댓글 기능
- 관리자 기능
- 지도 기능

## 회원 기능

> 회원가입 시 `유효성 검사`/`중복 체크`를 통과해야 한다.
- 폼 로그인 기능을 사용할 수 있다.

## 커뮤니티(게시판) 기능

> 게시판에 글을 올릴 수 있다.
- 게시판에 글을 `작성`할 수 있다.
- 댓글을 `작성` 및 `삭제`할 수 있다.
- 글을 `검색`할 수 있고, `좋아요`를 누를 수 있다.

## 관리자 기능

> 관리자는 유저와 업체를 관리 할 수 있다.
- 유저를 `삭제`할 수 있다.
- 유저 정보를 볼 수 있다.
- 업체를 `추가`, `수정`, `삭제`할 수 있다.
- 업체를 `검색`할 수 있다.

# 3. 개발 내용

- [요구사항 분석](https://velog.io/@song9471/%EC%9A%94%EA%B5%AC%EC%82%AC%ED%95%AD-%EB%B6%84%EC%84%9D)
- [도메인 설계](https://velog.io/@song9471/%EB%8F%84%EB%A9%94%EC%9D%B8-%EC%84%A4%EA%B3%84)
- [DB 설정](https://velog.io/@song9471/DB-%EC%84%A4%EC%A0%95)
- [엔티티 개발](https://velog.io/@song9471/%EC%97%94%ED%8B%B0%ED%8B%B0-%EA%B0%9C%EB%B0%9C)
- [도메인 개발](https://velog.io/@song9471/%EB%8F%84%EB%A9%94%EC%9D%B8-%EA%B0%9C%EB%B0%9C)
- [게시판 도메인 개발](https://velog.io/@song9471/%EA%B2%8C%EC%8B%9C%ED%8C%90-%EB%8F%84%EB%A9%94%EC%9D%B8-%EA%B0%9C%EB%B0%9C)
- [홈 화면 개발](https://velog.io/@song9471/%ED%99%88-%ED%99%94%EB%A9%B4-%EA%B0%9C%EB%B0%9C)
- [회원가입 페이지 개발](https://velog.io/@song9471/%ED%9A%8C%EC%9B%90-%EC%BB%A8%ED%8A%B8%EB%A1%A4%EB%9F%AC-%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85-%ED%8E%98%EC%9D%B4%EC%A7%80-%EA%B0%9C%EB%B0%9C)
- [로그인 기능 개발](https://velog.io/@song9471/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EB%B0%9C)
- [게시글 등록, 조회, 상세 조회 기능 개발](https://velog.io/@song9471/%EA%B2%8C%EC%8B%9C%EA%B8%80-%EB%93%B1%EB%A1%9D-%EC%A1%B0%ED%9A%8C-%EC%83%81%EC%84%B8-%EC%A1%B0%ED%9A%8C-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EB%B0%9C)
- [게시글 수정, 삭제 기능 개발](https://velog.io/@song9471/%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%88%98%EC%A0%95-%EC%82%AD%EC%A0%9C-%EA%B8%B0%EB%8A%A5-%EA%B0%9C%EB%B0%9C)
- [로그인 인증 체크](https://velog.io/@song9471/%EB%A1%9C%EA%B7%B8%EC%9D%B8-%EC%9D%B8%EC%A6%9D-%EC%B2%B4%ED%81%AC)
- [게시물 조회수 기능](https://velog.io/@song9471/%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%A1%B0%ED%9A%8C-%EC%88%98-%EA%B8%B0%EB%8A%A5)
- [관리자 페이지](https://velog.io/@song9471/%EA%B4%80%EB%A6%AC%EC%9E%90-%ED%8E%98%EC%9D%B4%EC%A7%80-%EB%A7%8C%EB%93%A4%EA%B8%B0)
- [다이로움 지도 개발](https://velog.io/@song9471/%EB%8B%A4%EC%9D%B4%EB%A1%9C%EC%9B%80-%EC%A7%80%EB%8F%84)
- [게시글 페이징 기능](https://velog.io/@song9471/%EA%B2%8C%EC%8B%9C%EA%B8%80-%ED%8E%98%EC%9D%B4%EC%A7%95-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84)
- [관리자 페이지 - 검색 기능 구현](https://velog.io/@song9471/%EA%B4%80%EB%A6%AC%EC%9E%90-%ED%8E%98%EC%9D%B4%EC%A7%80-%EA%B2%80%EC%83%89-%EA%B8%B0%EB%8A%A5-%EA%B5%AC%ED%98%84)
- [비밀번호 암호화](https://velog.io/@song9471/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-%EB%B9%84%EB%B0%80%EB%B2%88%ED%98%B8-%EC%95%94%ED%98%B8%ED%99%94)
- [스프링 시큐리티 설정](https://velog.io/@song9471/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)
- [게시글 작성자만 수정, 삭제](https://velog.io/@song9471/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%8B%9C%ED%81%90%EB%A6%AC%ED%8B%B0-%EA%B2%8C%EC%8B%9C%EA%B8%80-%EC%9E%91%EC%84%B1%EC%9E%90%EB%A7%8C-%EC%88%98%EC%A0%95-%EC%82%AD%EC%A0%9C-%EB%B2%84%ED%8A%BC-%EB%B3%B4%EC%9D%B4%EA%B2%8C-%EC%84%A4%EC%A0%95%ED%95%98%EA%B8%B0)
- [다이로움 지도 - 마커 찍는 속도 올리기](https://velog.io/@song9471/%EB%8B%A4%EC%9D%B4%EB%A1%9C%EC%9B%80-%EC%A7%80%EB%8F%84-%EB%A7%88%EC%BB%A4-%EC%B0%8D%EB%8A%94-%EC%86%8D%EB%8F%84-%EC%98%AC%EB%A6%AC%EA%B8%B0)

# 4. DB 설계

### ERD
<img width="775" alt="image" src="https://github.com/gitujin/SPRING_MAP/assets/97817358/14f8eb45-008b-486f-b6c7-9acd00766af9">

# 5. API 설계


