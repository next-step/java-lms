# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 기능 요구사항
* 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
* 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
* 답변이 없는 경우 질문 삭제가 가능하다.
* 질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.
* 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
한다.
* 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
* 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## TO-DO
### Answer
* [X] 답변을 삭제할 수 있다.
  * [X] 삭제를 할 경우 답변의 삭제 상태를 변경한다.
  * [X] 질문자와 답변자가 같은 경우 삭제가 가능하다.
  * [X] 질문자와 답변자가 다른 경우 예외를 반환한다.

### Answers
* [X] 답변들을 삭제할 수 있다.
  * [X] 삭제를 할 경우 답변들의 삭제 상태를 변경한다.
  * [X] 질문자와 답변자가 다른 답변이 있는 경우 예외를 반환한다.

* [X] 답변을 추가할 수 있다.

### Question
* [X] 답변이 없는 질문을 삭제할 수 있다.
  * [X] 삭제를 할 경우 질문의 삭제 상태를 변경한다.
  * [X] 로그인 사용자와 질문자가 같은 경우 삭제 가능하다.
  * [X] 로그인 사용자와 질문자가 다른 경우 예외를 반환한다.
####
* [X] 답변이 있는 질문을 삭제할 수 있다.
  * [X] 로그인 사용자와 질문자가 같은 경우 삭제 가능하다.
  * [X] 로그인 사용자와 질문자가 다른 경우 예외를 반환한다.
  * [X] 답변이 있을 경우 질문자와 답변글의 모든 답변자가 같으면 질문과 답변의 삭제 상태를 변경한다.
  * [X] 답변이 있는 질문 삭제 시 질문자와 답변글의 모든 답변자가 같지 않은 경우 예외를 반환한다.
  
* [X] 질문을 삭제하면 질문과 답변 이력이 객체로 저장된다.

### DeleteHistory
* [X] 질문과 답변 삭제 이력을 객체로 저장한다.

# 수강신청
## 기능 요구사항
* 과정
  * [X] 과정은 기수 단위로 운영되며, 여러 개의 강의를 가질 수 있다.
  * [ ] 과정에 강의를 추가할 수 있다.
* 강의 커버 이미지 정보
  * [X] 이미지 크기는 1MB 이하여야 한다.
  * [X] 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
  * [X] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
* 강의 상태
  * [X] 준비중, 모집중 상태는 오늘 날짜가 강의 시작일 이전인지 확인한다.
  * [X] 종료 상태는 오늘 날짜가 강의 종료일 이후인지 확인한다.
* 강의
  * [X] 시작일과 종료일을 가진다.
  * [X] 이미지 형식 제한에 맞는 강의 커버 이미지 정보를 가진다.
  * [X] 준비중, 모집중, 종료 3가지 상태를 가진다.
    * [X] 강의 시작일 이전이면 준비중, 모집중 상태를 갖는다.
    * [X] 강의 종료일 이후면 종료 상태를 갖는다.
  * [X] 강의에 수강 신청을 할 수 있다.
    * [X] 강의 상태가 모집중일 때 수강신청을 할 수 있다.
    * [X] 강의 상태가 준비중, 종료 상태일 때 수강신청을 하면 예외가 발생한다.
    * 무료 강의
    * [X] 최대 수강 인원 제한없이 수강신청 할 수 있다.
    * 유료 강의
    * [X] 최대 수강 인원을 초과하면 예외가 발생한다.
    * [ ] 수강생이 결제한 금액과 수강료가 일치하면 수강신청이 완료된다.
    * [X] 수강생이 결제한 금액과 수강료가 일치하지 않으면 예외가 발생한다.
* 결제
  * [ ] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
