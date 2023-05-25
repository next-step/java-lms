# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 수강신청 리팩토링 1단계(레거시 코드 리팩토링)
### Question
  - [x] 로그인 사용자가 질문한 사람이 아닐 경우에 CannotDeleteException 예외를 던진다.
  - [ ] questionId로 조회되는 Question이 없을 경우에 NotFoundException 예외를 던진다.
  - [ ] 삭제가 가능할 경우에는 deleted 필드값을 true으로 변경한다.
  - [ ] List<Answer> answer -> 일급 컬렉션으로 변경한다.
    - null 체크 등등 (나중에 다시 추가하기)

### Answer
  - [x] 질문자와 답변글의 모든 답변자 같은 경우 답변을 삭제 가능하다(예외를 던지지 않는다).
  - [x] 질문자와 답변자가 다를 경우 CannotDeleteException 예외를 던진다. 
  - [ ] 삭제가능 할 경우, 삭제시 deleted 필드 값을 true로 변경한다.

### DeleteHistory
  - [ ] 질문을 삭제할 경우 답변글도 삭제한다.
  - [ ] 삭제할 시 질문, 답변글의 상태값을 모두 변경한다.
  - [ ] 삭제할 시 DelieteHistory 에 이력을 남긴다.
  - [ ] List<DelieteHistory> -> 일급 컬렉션으로 변경한다(사용하는 곳들 모두)