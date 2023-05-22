## 학습 관리 시스템(Learning Management System)

---

### Step1. 레거시 코드 리팩터링
### 질문 삭제하기 요구사항
- [X] 질문 데이터 상태를 삭제 상태로 변경(deleted - boolean type)
- [X] 잘문 삭제 가능 조건 
  - [X] 로그인 사용자와 질문한 사람이 같은 경우
  - [X] 답변이 없는 경우
  - [X] 질문자와 답변글의 모든 답변자 같은 경우
- [X] 삭제 내용
  - [X] 질문을 삭제할 때 답변도 같이 삭제 처리
  - [X] 답변의 삭제 또한 삭제 상태(deleted)를 변경
- [ ] 답변 삭제
  - [X] 질문자와 답변자가 다른 경우 답변 삭제 불가능
  - [ ] 질문과 답변 삭제 이력 저장 : DeleteHistory

### 리팩터링 요구사항
- QnaService deleteQuestion()는 질문 삭제 기능을 구현한 코드
- QnaService deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
- QnaService 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD 구현
- QnaService deleteQuestion() 메서드에 대한 단위 테스트는 QnaServiceTest
- 도메인 모델로 로직을 이동한 후에도 QnaServiceTest 모든 테스트는 통과해야 함