# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## LMS
### 개발 요구사항
* [x] 질문 삭제를 logical delete 기능으로 구현
* [x] 로그인 사용자와 질문자의 아이디 비교
* [x] 답변유무 체크
* [x] 삭제이력 추가

### 리팩터링 요구사항
* [x] service -> domain 로직 이동
* [x] 로직 이동시 TDD로 구현
* [x] 기존 test code 통과

### 도메인모델 설계
* [x] 과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다.
* [x] 강의는 시작일과 종료일을 가진다.
* [x] 강의는 강의 커버 이미지 정보를 가진다.
* [x] 강의는 무료 강의와 유료 강의로 나뉜다.
* [x] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
* [x] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
* [x] 강의는 강의 최대 수강 인원을 초과할 수 없다.

### DB
* [x] session/session_cover_image repository 생성
* [x] session/session_cover_image service 생성
