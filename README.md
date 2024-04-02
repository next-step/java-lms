# 학습 관리 시스템(Learning Management System)
## 1단계 - 레거시 코드 리팩터링
### 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든 답변자 같은 경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
### 리팩터링 요구사항
- QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

### 첫번째 피드백 사항
- [x] 리팩터링할 떼 무조건 복붙하지말고 그 도메인에 알맞는 예외 메시지를 설정하라(Answer, Answers)
- [x] QnaServiceTest는 변경없이 프로덕션코드만 리팩터링하여 재통과하는 사이클을 가져라
- [x] 더 이상 사용하지 않는 메서드는 제거하라
- [x] delete메서드와 addTo메서드의 로직을 다시 살펴보고 구현한 메서드는 무조건 테스트코드를 통해 검증하라
- [x] 사용하지 않는 import문은 삭제하라
- [x] 연관되어있는 메서드는 가깝게 배치시켜라 
- [x] 기능 목록 및 commit 로그 요구 사항을 작성하여 tdd를 의식적으로 연습하라(이후 지켜야할 행동)
- [x] 삭제 이력의 일급 컬렉션 목적성을 생각해보아라 
