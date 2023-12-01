# 학습 관리 시스템(Learning Management System)

## 1단계 - 레거시 코드 리팩터링
### 질문 삭제하기 리팩터링 요구사항
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드를 도메인 모델 객체에 구현한다
- QnaService의 비즈니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.
