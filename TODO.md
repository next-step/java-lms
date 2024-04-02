### 2단계 - 수강신청(도메인 모델)

**Session**

- [x] 강의 신청 가능 여부를 확인한다
    - 강의 상태가 "모집중"일때만 신청 가능하다 -- isPossibleRegistration()
- [x] 강의는 강의 상태(준비중, 모집중, 종료)를 가진다
    - enum SessionState 생성
    - PREPARING, RECRUITING, FINISHED 정의

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