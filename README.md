# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 1단계
- [x] Question.delete 추가
- [x] Answer.createDeleteHistory 추가
- [x] QnAService.deleteQuestion 리팩토링
- [x] 피드백
  - [x] Question.setDelete 삭제
  - [x] Answer.delete 추가
## 2단계
- [x] SessionThumbnail
  - [x] 객체 구현
  - [x] 리팩토링
    - [x] width, height ImageSize 객체로 통합
    - [x] ImageFileName 객체 생성
- [x] Session
  - [x] 객체 구현
    - [x] SessionStatus 구현
    - [x] SessionType 구현
    - [x] Session 구현
  - [x] 리팩토링
    - [x] Session 내 필드 캡슐화 및 단위 테스트 작성
    - [x] 객체 메시지 구현
- [x] 서비스 구현
- [x] 피드백
  - [x] ImageExtension 미사용 코드 제거
  - [x] Enrollment
    - [x] 수강생 필드 추가
    - [x] 무료 강의 대응
  - [x] SessionTest 실패 테스트 수정
  - [x] Session 인스턴스 변수 개수 감소
  - [x] Payment.getAmount 제거
  - [x] SessionStatus 단위 테스트 추가
  - [x] ImageSizeTest 가로가 작은 경우, 세로가 작은 경우 독립적으로 테스트
  - [x] SessionThumbnailTest 중복 테스트 제거
- [ ] 2차 피드백
  - [x] Enrollment
    - [x] 인터페이스로 분리
    - [x] 수강신청 validation 을 Enrollment 내부에서 수행
  - [x] 도메인 패키지 분리