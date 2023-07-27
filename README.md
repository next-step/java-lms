# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---
## 1단계 - 레거시 코드 리팩터링
### todo 리스트
1. delete 로직 분리
    - Question 및 Answer 객체에서 직접 delete/유효성 테스트 진행
    - Question은 Answer에게 삭제하라고 메시지 전달
2. Answer 일급 콜렉션 화
    - Answer 리스트를 가지고 있는 Answers 객체 추가
    - Answers에도 delete 기능을 추가하여 여기서 일괄 삭제
3. 삭제 완료시 DeleteHistory 반환 
    - 위에서 구현한 모든 delete 기능은 DeleteHistory를 반환
    - Answers와 Question은 DeleteHistory 리스트를 반환

### 요구사항
#### 질문 삭제하기 요구사항
1. 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
2. 로그인 사용자와 질문한 사람이 같은 경우 삭제할 수 있다.
3. 답변이 없는 경우 삭제가 가능하다.
4. 질문자와 답 변들의 모든 답변자 같은 경우 삭제가 가능하다.
5. 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
6. 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
7. 질문과 답변 삭제 이력에 대한 정보를 Delete History를 활용해 남긴다.

#### 리팩터링 요구사항
1. nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
2. QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
3. QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
4. QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.