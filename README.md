# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step 2 - 수강신청(도메인 모델)
### 기존 구현되어 있는 도메인 모델
* Course
* Payment
* NsUser
### 새로 구현이 필요한 도메인 모델
* Session : 강의
  * 시작일, 종료일 : SessionPeriod
  * 강의 커버 이미지 : NsImage
  * 강의 타입 : 유료/무료 : SessionType
  * 최대 수강인원 : Capacity
  * 강의료 : Price
  * 강의 상태 : 준비중/모집중/종료 : SessionStatus
* NsImage : 강의 커버 이미지
  * size
  * type
  * width / height