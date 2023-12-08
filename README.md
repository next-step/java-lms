# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# STEP 1 기능분석
기존의 절차지향적 코드를 분석하고, 테스트하기 쉬운 코드를 판별하여, 리팩터링할 목록들을 적으려고한다.
* [X] 도메인으로 이동 : 로그인 유저와 질문글 작성자 비교 코드
  * Question 도메인으로 이동
* [X] 도메인으로 이동 : 질문글의 답변 목록 받기
  * Question 도메인으로 이동
* [X] 도메인으로 이동 : 답변 목록별로 로그인 유저와 답변글 작성자 비교 코드
  * Answer 도메인으로 이동
* [X] 도메인으로 이동 : 질문글 Deleted 상태 변경
  * Question 도메인으로 이동
* [X] 도메인으로 이동 : 삭제 이력에 질문글 추가
  * Question 도메인으로 이동
* [X] 도메인으로 이동 : 답변글 Deleted 상태 변경
  * Answer 도메인으로 이동
* [X] 도메인으로 이동 : 삭제 이력에 답변글 추가
  * Answers 도메인으로 이동

=> 질문글 Database에 가져오는 기능, 삭제이력 Database에 저장하는 기능은 테스트하기 어려워 이동 X

# STEP 2 기능분석
* [X] 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다. 
* [X] 강의는 시작일과 종료일을 가진다. 
* [X] 강의는 강의 커버 이미지 정보를 가진다. 
  * [X] 이미지 크기는 1MB 이하여야 한다. 
  * [X] 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
  * [X] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다. 
* [X] 강의는 무료 강의와 유료 강의로 나뉜다. 
  * [X] 무료 강의는 최대 수강 인원 제한이 없다. 
  * [X] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다. 
  * [X] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다. 
* [X] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다. 
* [X] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다. 
* [X] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다. 
  * [X] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

# STEP 3 기능분석
* [X] schema sql 작성
  * [X] Session Schema sql 작성
  * [X] SessionType Schema sql 작성
  * [X] Thumbnail Schema sql 작성
  * [X] Students Schema sql 작성
* [X] data sql 작성
  * [X] Session 예시 데이터 data.sql 작성
  * [X] SessionType Schema sql 작성
  * [X] Thumbnail 예시 데이터 data.sql 작성
  * [X] Students 예시 데이터 data.sql 작성
* [ ] repository query 작성
  * [ ] Session Read,Update 쿼리 작성
  * [X] Thumbnail Read 쿼리 작성
  * [ ] Students Create, Read, Update 쿼리 작성
* [ ] service enroll 코드 작성