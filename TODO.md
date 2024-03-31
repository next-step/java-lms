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

