# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 질문 삭제하기
* QnaService deleteQuestion 개선
  * Question 도메인으로 비즈니스 로직 이동
  * Answer 도메인으로 비즈니스 로직 이동

* Question 도메인에 delete 추가
* delete 후 DeleteHistory 리턴하기

* Answer 도메인에 delete 추가
* delete 후 DeleteHistory 리턴하기

* answer는 question 삭제 시 발생하기 때문에 question 도메인에서 answer에 삭제 메시지 전달
* 음..answers를 삭제할 수 없다면 question도 삭제할 수 없기 때문에 answer 먼저 삭제