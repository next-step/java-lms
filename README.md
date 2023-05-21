# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)


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
- [x] `QnaService`의 `deleteQuestion()` 메서드에 단위 테스트 가능한 코드(핵심 비즈니스 로직)를 도메인 모델 객체에 구현
- [x] `QnaService`의 비즈니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- [x] `QnaService`의 `deleteQuestion()` 메서드에 대한 단위 테스트는 `src/test/java` 폴더 내 `nextstep.qna.service.QnaServiceTest`이다.
  > 도메인 모델로 로직을 이동한 후에도 `QnaServiceTest`의 모든 테스트는 통과해야 한다.