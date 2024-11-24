## 수강 신청 기능 요구사항

- [X] 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
- [X] 강의는 시작일과 종료일을 가진다.
- [X] 강의는 강의 커버 이미지 정보를 가진다.
  - [X] 이미지 크기는 1MB 이하여야 한다.
  - [X] 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
  - [X] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
- [X] 강의는 무료 강의와 유료 강의로 나뉜다.
  - [X] 무료 강의는 최대 수강 인원 제한이 없다.
  - [X] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
  - [X] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- [X] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
- [X] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- [X] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
  - [X] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

## 2단계 1차 리뷰 사항 

### 전반
- [X] 개행 컨벤션 
- [X] reformat 코드
- [X] 작은 단위로 커밋
- [X] 사용하지 않는 코드 제거
- [X] assertAll 메서드 활용 테스트

### Course
- [X] 기존 코드 유지해서 테스트 

### BaseTime
- [X] 클래스명 오타 BaseTIme

### CoverImage
- [X] id 필드 필요 여부
- [X] 이미지 파일 이름 변수 추가
- [X] 관계 있는 필드 객체 도출
- [X] 용량 표현 바이트 단위 표현
- [X] 비율 검증과 크기 검증 분리

### Session, PaidSession, FreeSession
- [X] Session 공통 필드 줄이기
- [X] 중복 신청 여부 검증
- [X] 모집중인 상태 여부 검증 메시지 sessionStatus 에 메시지 보내기
- [X] final 키워드 필요 여부
- [X] enroll 메서드 호출 Service 클래스 추가

### SessionPeriod
- [X] 사용하지 않는 코드 제거

### CourseRepository
- [X] infra 패키지 이동 관련

### CourseTest, CoverImageTest, PaidSessionTest, FreeSessionTest
- [X] assertAll 메서드 활용 테스트

## 2단계 2차 리뷰 사항

### 전반
- [X] assertAll 내부의 검증부는 assertj 로 통일
- [X] reformat 코드

### Course
- [X] 점층적 생성 패턴에 따라 주 생성자는 생성자 중 가장 마지막에 배치

### ImageDimension
- [X] 크기 검증과 비율 검증도 분리
- [X] 비율을 검증할 때에는 연산 오차를 제거하기 위해 int 타입으로 연산

### ImageExtension
- [X] 원시 타입 사용 (이펙티브 자바 아이템 61 참고)

### Session
- [X] 생성자에서 강의 상태 주입 
- [X] 신청 기능을 가진 객체 만들기 (sessionStatus, enrolledUsers)

### CoverImageTest
- [X] 확장자에 대한 책임이 ImageExtension 에 있으므로 확장자 테스트 skip

## 2단계 3차 리뷰 사항
- [X] 중복 신청 예외 처리 SessionEnrollment 이동
- [X] DisplayName 오타 수정
- [X] isEnrollmentFull 테스트 추가


## 3단계 1차 리뷰 사항

### EnrollmentRepository
- [X] enrollUser -> save 로 변경

### FreeSession
- [X] getFee 메서드 리턴값 0 고정

### PaidSession
- [X] validaPaymentAmount public -> private 으로 변경
- [X] isPaymentMismatched 에서 payment 객체에 결제금액 일치여부 메시지 보내기

### Session
- [X] 접근제한자와 abstract 위치, 컨벤션 확인

### SessionBody
- [X] courseId 필드 Session 클래스로 이동

### Session, SessionBody, SessionEnrollment
- [X] 필드 final 키워드 추가

### JdbcSessionRepository
- [X] 무료/유료 등 구현체 결정 책임 도메인으로 위임

### enrollment 테이블
- [X] 중복 신청 데이터가 존재할 수 있는 구조. unique 제약 추가. repository 중복 처리 로직 제거 가능

### cover_image 테이블
- [X] 1개의 강의는 1개의 이미지를 가지는 1:1 관계이므로 테이블 분리 불필요

### SessionEnrollmentTest
- [X] 매직넘버 추출하여 변수명으로 의도 나타내기

### EnrollmentRepositoryTest
- [X] 테스트의 의도대로 size 검증이 아닌 NsUser 값들을 검증

## 자체 추가

### SessionEnrollment
- [X] SessionStatus isNotOpen 메서드 사용

## 3단계 2차 리뷰 사항

### JdbcEnrollmentRepository
- [X] 수강생 수 만큼 쿼리 반복이 아닌 join 쿼리 사용

### SessionService
- [X] enroll 메서드 단위 테스트 추가 


## 4단계 변경된 기능 요구사항 

* 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
  - 변경
    - [x] 강의가 진행 중인 상태에서도 수강신청이 가능해야한다. 
    - [x] 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태 값을 분리해야 한다.

* 강의는 강의 커버 이미지 정보를 가진다. 
  - 변경
    - [x] 강의는 하나 이상의 커버 이미지를 가질 수 있다.

* 강사가 승인하지 않아도 수강 신청하는 모든 사람이 수강 가능하다.
  - 변경
    - [x] 우아한테크코스(무료), 우아한테크캠프 Pro(유료)와 같이 선발된 인원만 수강 가능해야 한다.
      - [x] 강사는 수강신청한 사람 중 선발된 인원에 대해서만 수강 승인이 가능해야 한다.
      - [x] 강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.

## 프로그래밍 요구사항

- 리팩터링할 때 컴파일 에러와 기존의 단위 테스트의 실패를 최소화하면서 점진적인 리팩터링이 가능하도록 한다.
- DB 테이블에 데이터가 존재한다는 가정하에 리팩터링해야 한다.
  - 즉, 기존에 쌓인 데이터를 제거하지 않은 상태로 리팩터링 해야 한다.

## 4단계 1차 리뷰 사항

### EnrolledUsers
- [x] 일급컬렉션 사용

### JdbcCoverImageRepository
- [x] batchUpdate 활용

### Repository 공통
- [x] RowMapper 정의 방법 통일

### NsUser
- [x] 수강생 객체 도출해서 책임 위임

### SessionService
- [x] NsUser id 를 파라미터로 넘기기

### schema.sql
- [x] 기존 데이터가 존재한다는 가정이므로 default 값이 있거나 null 을 허용해야 리팩토링 가능

### CoverImageRepositoryTest
- [x] usingRecursiveComparison 활용