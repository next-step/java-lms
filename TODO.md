### 3단계 - 수강신청(DB 적용)

```text
- h2 임베디드 설치해서 SQL 오류 확인하기
- 문자열은 작은 따옴표('')로 표기
- 테이블, 컬럼명은 snake_case 표기

[절차]
1) 인터페이스 정의 
2) 구현체 정의 
3) 테스트 클래스 생성
4) 테이블 추가시 schema.sql / 더미 데이터 data.sql 에 하기 
5) 테스트 작성

[주의]
1) 테이블 스키마에 pk 자동 증가 설정 후 data.sql 에 id 지정한 더미 데이터 저장시
save(..) 테스트시 테이블은 3L 까지 지정했는데 자동 증가는 1L부터 시작이라 충돌  
 
```

- [x] 강의 커버 이미지 테이블 설계
    - 테이블명 : cover_image
    - save(..), saveAll(..), findById(..), findBySessionId(..) 테스트
- [x] 강의 - 수강생 연관 관계 테이블 설계
    - 테이블명 : session_listener
    - save(..), saveAll(..), findAllBySessionId(..), delete(..) 테스트
- [x] 회원 Repository 기능 추가, 테스트
    - 테이블명 : ns_user
    - findById(..), findByIds(..), save(..), update(..) 추가
- [x] 강의 테이블 설계
    - 테이블명 : class_session
    - save(..), saveAll(..), findById(..), findByIds(..) 추가

---

### 2단계 - 수강신청(도메인 모델)

**Session**

- [x] 강의 신청 가능 여부를 확인한다
    - 강의 상태가 "모집중"일때만 신청 가능하다 -- isPossibleRegistration()
- [x] 강의는 강의 상태(준비중, 모집중, 종료)를 가진다
    - enum SessionState 생성
    - PREPARING, RECRUITING, FINISHED 정의
- [x] 강의는 시작일과 종료일 상태를 가진다

- [x] Payment 는 PaymentState 상태를 가진다
    - enum PaymentState 생성
    - PENDING, PAYMENT_COMPLETE, CANCELLED, COMPLETE

- [x] 강의 유형을 포함하여 강의 신청 가능 여부를 확인한다
    - [x] 무료 강의인 경우 최대 수강 인원 제한이 없다
    - [x] 유료 강의인 경우 최대 수강 인원을 초과할 수 없다
- [x] 수강 신청 가능한 경우 주문서를 생성한다

- [x] 예외 검사 후 수강 등록을 한다 -- Session join(..)
    - [x] 로그인 유저(NsUser)가 NULL 인 경우 예외 던진다
    - [x] 이미 등록된 수강생인 경우 예외 던진다
    - [x] 결제 정보(Payment)가 NULL 인 경우 예외 던진다
    - [x] 결제 정보(Payment)가 "결제완료" 상태가 아닌 경우 예외 던진다
    - [x] 결제 정보와 강의 아이디(session id), 유저 아이디(NsUser id), 결제 금액 일치하지 않는 경우 예외 던진다

- [x] 강의 커버 이미지 도메인을 추가한다 (CoverImage)
    - [x] 크기는 1MB (1024 * 1024) 이하만 가능하다
    - [x] 확장자는 gif, jpg, jpeg, png, svg 만 허용한다
    - [x] 너비(width) 300 pixel, 높이(height) 200 pixel 이상만 허용
    - [x] 너비와 높이는 3:2 비율이여야 한다

---

### 1단계

- [x] 질문 작성자와 로그인 유저를 비교한다
    - 작성자가 아닌 경우 CannotDeleteException 던진다
- [x] 답변 작성자와 로그인 유저를 비교한다
    - 작성자가 아닌 경우 CannotDeleteException 던진다
- [x] 질문 작성자와 일치하고 답변이 없는 경우 삭제 가능하다
- [x] 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다
- [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다
    - 작성자가 다른 경우 CannotDeleteException 던진다
- [x] Answers 일급 컬렉션 추가
    - add(..) , get(), toDeleteHistory() 생성

> Answer, Question 인스턴스 필드를 3개 미만으로 리팩토링

**Answer 클래스**

- [x] 생성일, 수정일 인스턴스 필드 가지는 추상 클래스 선언 -- BaseTimeEntity
- [x] Deleted (wrapper, VO) 클래스 생성
- [x] 질문의 id와 question 필드 가지는 AnswerKey 생성
- [x] 질문 writer와 contents 필드 가지는 AnswerContent 생성
- [x] AnswerKey와 AnswerContent 필드 가지는 AnswerBody 생성
- [x] 메소드 이동, 유효성 검사 이동
- [x] 주 생성자, 부 생성자 변경

**Question**

- [x] BaseTimeEntity 상속
- [x] Deleted 인스턴스 필드 변경
- [x] QuestionKey 생성 : id
- [x] QuestionContent 생성 : Contents, Answers
- [x] QuestionBody 생성
- [x] 메소드 이동, 유효성 검사 이동
- [x] 주 생성자, 부 생성자 변경
