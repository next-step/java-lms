## 기능 요구사항
#### - 질문 삭제하기
<br />

### 리팩터링 요구사항
<hr />

- QnaService의 deleteQuestion() 메서드에 <br />
단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 <br />
  src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 
- 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

<br />


### 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
- Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 도메인 모델에 setter 메서드 추가하지 않는다.

<br />

### Q&A 서비스 객체
<hr />

- Question
- Answer
- DeleteHistory

<br />

### 질문 삭제하기 요구 사항
<hr />

- [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 <br />
  삭제 상태(deleted - boolean type)로 변경한다.
- [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- [X] 답변이 없는 경우 삭제가 가능하다.
- [X] 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다.
- [X] 질문을 삭제할 때 답변 또한 삭제해야 하며, <br />
  답변의 삭제 또한 삭제 상태(deleted)를 변경 한다.
- [X] 질문자와 답변자가 다른 경우 답변을 삭제 할 수 없다.
- [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

<br />


### 예외 처리
- 입력 값이 null이거나 빈 공백 문자일 경우 IllegalArgumentException throw

<br />


### BaseTime
- [X] 날짜 관련 변수를 하나의 도메인으로 뺀다.

<br />

### Question
- [X] Question의 List를 일급 콜렉션으로 리팩토링
- [X] 인스턴스 필드 3개 이하로 리팩토링
  - Question, QuestionInfo
- [X] getter, setter 삭제
  - QnAService -> Question 삭제 로직 도메인 객체로 이동
- [X] Question 정상적으로 생성 되는지 테스트
- [X] 로그인 사용자와 질문한 사람이 다른 경우 질문 삭제 불가능 테스트
- [X] 질문자와 답변 글의 답변자가 다른 경우 질문 삭제 불가능 테스트
- [X] 답변이 없는 경우 삭제가 가능한지 테스트
- [X] 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다.

<br />

### Answer
- [X] 인스턴스 필드 3개 이하로 리팩토링
  - AnswerInfo
- [X] getter, setter 삭제
- [X] 답변의 삭제 상태(deleted)를 변경 테스트
- [X] 질문자와 답변자가 같은 경우 답변 삭제 테스트
- [X] 질문자와 답변자가 다른 경우 답변을 삭제 할 수 없는지 테스트

<br />

### DeleteHistory
- [ ] 인스턴스 필드 3개 이하로 리팩토링
- [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
