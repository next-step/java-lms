# LMS 과제 1단계
## 신규 추가된 모델
- [x] `Answers` : `answer`을 `List`로 저장
- [x] `TimeStrategy` : 현재 시각을 만들어내는 전략 패턴
- [x] `LocalDateTimeProvider` : 현재 시각을 `LocalDateTime.now()`로 만들어내는 전략 패턴

## 문제 요구사항 목록
### 질문 삭제하기 요구사항
- [x] 질문 데이터를 삭제하는 것이 아닌, 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
- [x] 삭제가 가능한 조건은 아래와 같음 
  - [x] 로그인 사용자와 질문한 사람이 같아야 함
  - [x] 답변이 없어야 함
  - [x] 단, 질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능
    - [x] 질문자와 답변자가 다른 경우, 삭제 불가능
- [x] 질문을 삭제할 때 답변 또한 삭제해아하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
- [x] 질문과 답변 삭제 이력에 대한 정보를 `DeleteHistory`를 활용해 남김

### 리펙터링 요구사항
- [x] QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
- [x] QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- [x] QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이며, 
  도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 함
- [x] 도메인 모델의 TC의 경우, No Mock으로 테스트 코드 작성

### 조건
- [x] get 메서드 사용없이, 메세지를 보내기
- [x] 일급 컬렉션 사용 (Question의 List를 일급 컬렉션으로 구현)
- [x] 3개 이상 인스턴스 변수를 가진 클래스를 사용하지 않기
- [x] 도메인 모델에 setter 사용하지 않기