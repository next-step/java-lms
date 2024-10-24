# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 수강신청 1단계 요구사항
- [x] `QnaService`의 `deleteQuestion` 메서드의 단위 테스트 가능한 코드를 도메인 모델로 옮긴다.
    - [x] 이 때, TDD를 기반으로 리팩토링 한다.
- [x] 질문 삭제하기 요구사항을 모두 만족하여야 한다.
    - [x] 질문 삭제하기는 `soft delete` 기반의 삭제이다. (실제 삭제가 아닌 상태 변경)
    - [x] 로그인 한 사용자와 질문한 사람이 같은 경우에만 삭제 가능하다.
    - [x] 답변이 없는 경우 삭제가 가능하다.
    - [x] 질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.
    - [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
    - [x] 질문과 답변 삭제 이력에 대한 정보를 `DeleteHistory`를 활용해 저장한다.