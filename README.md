# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 수강신청 요구사항
* Course는 기수 단위로 운영, 여러 개의 Session을 가짐
* Session은 시작일과 종료일을 가짐 (DONE)
* Session은 강의 커버 이미지를 가짐 (DONE)
  (1MB 이하 / 타입 - gif, jpg, jpeg, png, svg만 허용 / width 300픽셀, height 200픽셀 이상) (DONE)
* Session은 준비중 / 모집중 / 종료 3가지 상태 가짐 (DONE)
* Session은 무료와 유료로 나뉨 (DONE)

* 무료 - 수강 인원 제한 X (DONE) / 유료 - 수강 인원 제한 초과 불가 (DONE) & 수강생 결제 금액과 수강료 일치 시 수강 신청 가능
* 수강신청은 강의 상태가 모집 중일 때 가능 (DONE)
* 유료 Session 결제는 이미 완료한 것으로 가정. payments 모듈로 완료된 결제 정보 관리. 결제 정보는 Payment 객체에 담겨 반환