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

## 1.2 기술 선택 이유
### Spring이 아닌 SpringBoot를 선택한 이유
강의를 들을 때 기존 스프링을 사용하여 개발할 때는 여러 설정을 직접 해야 했습니다. 대표적으로 프로젝트를 생성할 때마다 `Component Scan`, `DispatcherServlet`, `DataSource`, `EntityManagerFactory` 같은 설정을 한 다음, WAS까지 연동해야 개발을 시작할 수 있었습니다.

SpringBoot는 다음과 같은 문제를 해결할 수 있습니다.
1. 내장 서버
2. Auto Configuration
3. starter로 의존성 쉬운 의존성 관리
4. jar파일로 쉬운 배포

SpringBoot를 사용하면서 `ComponentScan`을 지정하고, `ViewResolver`를 등록하고 프로젝트와 WAS를 연결하는 번거로운 일이 자동화되고 더 개발에만 집중할 수 있는 환경을 얻을 수 있었습니다. 또한 `jar`로 빌드해서 쉬운 배포가 가능하기 때문에 스프링부트를 선택하였습니다.

### JPA
`JPA`는 러닝 커브가 있기 때문에 `MyBatis`를 선택하면 더 빨리 개발을 진행할 수 있었습니다. 하지만 `MyBatis`를 사용할 때 몇 가지 단점이 있다고 배웠습니다.
1. 중복되는 SQL
2. SQL을 평문으로 관리(오타 찾기 어려움)

이런 단점에도 불구하고 `MyBatis`는 훌륭한 도구라고 생각하지만, 무엇보다 간단한 CRUD 쿼리는 물론이고 쿼리메서드와 같이 네이밍컨벤션을 이용하여 원하는 쿼리를 만들 수 있다는 점이 JPA를 선택한 이유입니다. 처음엔 직접 SQL을 작성하는 것이 빠를 수 있어도 익숙해질수록 생산성이 훨씬 좋아질 것이라는 생각에 JPA를 선택하게 되었습니다.

### Spring Security
스프링 시큐리티는 `Login`, `remember-me`, `PasswordEncoder`, `CSRF` 등 보안기능을 제공하기 때문에 선택하게 되었습니다.

### Lombok
`getter/setter`나 생성자, `Builder`를 만드는 등 기계적인 작업을 애노테이션으로 대체할 수 있기 때문에 선택하였습니다.

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


