# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step1 요구사항
QnAService의 deleteQuestion() 메서드 리펙토링
- [x] 로그인 한 유저가 삭제할 수 있는 질문인지 체크할 수 있는 기능 추가
- [x] 질문과 그에 따른 답변을 삭제할 수 있는 기능 추가
- [x] 삭제 히스토리 추가 기능 추가
- [x] 리펙토링 후 QnaServiceTest의 모든 테스트는 통과
- [x] 도메인 객체에 get, set 메서드를 최대한 제거
- [x] Question Answers 필드 일급 컬렉션으로 추가

## Step2 요구사항
수강 신청 기능 추가
- [x] Course은 기수 단위로 운영되며 여러개의 Session를 가질 수 있다.
  - [x] Course에 Session 등록 기능
- [X] Session은 시작일과 종료일을 가진다.
- [x] Session은 강의 커버 이미지 (Image)를 가진다.
  - [x] Image의 크기는 1MB 이하여야 한다.
  - [x] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
  - [x] Image는 타입을 가진다.
  - [x] Image 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
- [x] Session은 강의 타입(SessionType)을 가진다.
  - [x] SessionType은 무료 강의(Free)와 유료 강의(Paid)로 나뉜다.
  - [x] 무료강의는 최대 수강 인원 제한이 없다.
  - [x] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
- [x] Session은 Payments를 가진다.
  - [x] Payments는 동일한 유저가 같은 강의를 두번 결재할 수는 없다.
  - [x] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- [x] Session은 상태(Status)를 가진다.
  - [x] 준비중, 모집중, 종료 3가지 상태를 가진다.
  - [x] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- [x] Session은 수강신청 유저 정보를 가지고 있다.
- [x] Session의 필드 값은 3개로 관리한다.

## Step3 요구사항
- [x] session table 스키마 정의
- [x] Session Repository 만들기
  - [x] save 로직 추가
  - [x] findById 로직 추가
- [x] Payment Repository 만들기
  - [x] save 로직 추가
  - [x] findBySession 로직 추가


## Step4 요구사항
- [x] 강의 수강신청은 강의 상태가 모집중일 때만 가능
  - [x] 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태가 세분화 된다.
  - [x] 강의가 진행 중인 상태에서도 수강신청이 가능
- [x] 강의는 하나 이상의 커버 이미지를 가진다.
- [x] 강사가 승인하지 않아도 수강 신청하는 모든 사람이 수강 가능하다.
  - [x] 결재 정보는 승인 상태를 가진다. (대기중, 승인 완료, 승인 취소)
  - [x] 강사는 우아한테크코스(무료), 우아한테크캠프 Pro(유료) 유저를 수강 승인을 할 수 있다.
  - [x] 강사는 선발 인원이 아니면 수강 취소를 할 수 있다.
