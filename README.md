# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

----

## Step 1
### Requirements

- [x] 질문 데이터 삭제가 아닌 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.(soft delete)
- [x] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- [x] 답변이 없는 경우 삭제 가능하다.
- [x] 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다.
- [x] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- [x] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### Refactoring Requirements

- [x] nextstep.qna.service.QnaService의 deleteQuestion()에 섞여있는 여러 책임들을 다른 도메인 모델 객체에 분할해야한다.
  - [x] TDD로 구현해야한다.
  - [x] deleteQuestion()의 단위 테스트는 로직 이동 후에도 항상 통과해야한다.