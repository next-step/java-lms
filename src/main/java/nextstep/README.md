# 1단계 (레거시 코드 리팩터링)

## 질문 삭제하기 요구사항

- [ ] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- [ ] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- [ ] 답변이 없는 경우 삭제가 가능하다.
- [ ] 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
- [ ] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경 한다.
- [ ] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 리팩터링 요구사항
- [ ] nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- [ ] QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- [ ] QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- [ ] QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

## 구현 목록

- [x] Question의 delete(NsUser requester) 메서드를 구현한다.
  - [x] 요청자와 질문자가 다르면 예외를 발생시킨다.
  - [x] 답변이 있으면 삭제할 수 없다.
  - [x] 질문을 삭제한다.
  - [x] Answers에 삭제 요청을 보낸다.
  - [x] DeleteHistory를 생성한다.
  - [x] Answers로부터 List<DeleteHistory> 받는다.
  - [x] List<DeleteHistory>를 반환한다.
- [x] Answers 일급 컬렉션을 구현한다.
- [ ] DeleteHistories 일급 컬렉션을 구현한다.
- [x] Answer의 delete(NsUser requester) 메서드를 구현한다.
  - [x] 요청자와 답변자가 다르면 예외를 발생시킨다.
  - [x] 답변을 삭제한다.
  - [x] DeleteHistory를 생성하여 반환한다.
- [x] Answers의 deleteAll(NsUser requester) 메서드를 구현한다.
  - [x] Answer에 삭제 요청을 보낸다.
  - [x] 전달 받은 DeleteHistory들을 List로 묶어서 반환한다.
- [ ] 인스턴스 변수를 줄인다.. (요건 나중에 더 고민해보자)
  - [ ] dto를 구현한다.