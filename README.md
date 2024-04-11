# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## Step1

### 리팩터링

- `Answer` 에 delete 기능 추가 필요.
  - 작성자와 로그인 유저가 다른 경우 에러 발생
  - `DeleteHistory` 반환
  - 이미 삭제된 답변을 삭제하려고 할 경우 에러 발생
- `Answers` 추가
  - delete 추가.
- `Question` delete 추가
  - `DeleteHistory` 여러 개를 반환
  - 연관된 `Answer`도 같이 삭제.
  - 이미 삭제된 질문을 삭제하려고 할 경우 에러 발생

## Step2

### 기능 목록
- [ ] 과정(Course)는 기수 단위로 운영하며, 여러 개의 강의(Session)을 가질 수 있다.
- [ ] 강의는 시작일과 종료일을 가진다.
- [x] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
  - [x] 모집 중일 때만, 강의 수강신청이 가능하다.
- [ ] 유료 강의와 무료 강의가 있다.
  - [ ] 유료 강의는 최대 수강 인원 제한이 있고, 유료 강의는 없다.
- [x] 강의는 강의 커버 이미지 정보를 가진다.
  - [x] 이미지 크기는 1MB 이하이다.
  - [x] 이미지 타입은 gif, jpg, jpeg, png, svg만 허용한다.
  - [x] 이미지의 width 는 300픽셀, height는 200픽셀 이상이어야 하며, width : height 은 3:2 여야 한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)