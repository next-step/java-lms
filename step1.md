# 1단계 - 레거시 코드 리팩터링

### 기능 요구사항
- 질문 데이터는 완전 삭제가 아니라 상태를 삭제로 변경(deleted - boolean type)
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능
- 답변이 없는 경우 삭제 가능
- 질문자와 답변의 모든 답변자가 같은 경우 삭제 가능
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변 또한 완전 삭제가 아니라 상태를 삭제로 변경(deleted - boolean type)
- 질문자와 답변자가 다른 경우 삭제 불가
- 질문과 답변 삭제 이력에 대한 정보는 DeleteHistory를 활용해 남긴다

### 리팩터링 요구사항
- [] `QnaService`의 `deleteQuestion()` 메서드에 단위 테스트 가능한 코드(핵심 비즈니스 로직)를 도메인 모델 객체에 구현
- [] `QnaService`의 비즈니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- [] `QnaService`의 `deleteQuestion()` 메서드에 대한 단위 테스트는 `src/test/java` 폴더 내 `nextstep.qna.service.QnaServiceTest`이다.
  > 도메인 모델로 로직을 이동한 후에도 `QnaServiceTest`의 모든 테스트는 통과해야 한다.