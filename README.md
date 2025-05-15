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
- [x] 2차 피드백
  - [x] Enrollment
    - [x] 인터페이스로 분리
    - [x] 수강신청 validation 을 Enrollment 내부에서 수행
  - [x] 도메인 패키지 분리
## 3단계
- [x] 테이블 설계
- [x] ImageRepository 구현
- [x] SessionRepository 구현
  - [x] JdbcSessionRepository 구현
  - [x] Session 리팩토링
    - [x] SessionDto 구현
    - [x] SessionEnrollmentDto 구현
    - [x] SessionId 리팩토링
      - [x] courseId 필드 추가
      - [x] getter 추가
  - [x] getter 추가
    - [x] SessionInfo
    - [x] SessionBasicInfo
    - [x] SessionThumbnail
    - [x] SessionDetailInfo
- [x] SessionService 구현
  - [x] SessionDto 매핑
- [x] Enrollment 리팩토링 
  - [x] 생성자에 List<NsUser> 추가
  - [x] EnrollmentManager 추가
- [x] 피드백
  - [x] SessionRepository.update 제거
  - [x] Enrollment.isFull 리팩토링
  - [x] Enrollment.hasEnrolledUser 제거
# 4단계
- [x] 3단계 피드백
  - [x] SessionService 리팩토링
    - [x] UserService.findByUserIds 추가
    - [x] ImageRepository 의존성 제거
  - [x] Enrollment 검증 테스트 도입
- [x] 강의 진행 상태 / 모집 상태 분리
- [x] 강의 커버 이미지 다수 등록
- [x] 수강 신청 변경
  - [x] 수강 신청 상태 추가 (미승인/승인/취소)
    - [x] Enrollment 추가
    - [x] Enrollments 리팩토링
  - [x] 수강 신청 승인
  - [x] 수강 신청 취소
- [ ] 피드백
  - [x] Enrollments 를 Session 의 필드에서 제거
  - [x] Enrollment 메소드 위치 이동
    - [x] approve
    - [x] cancel
    - [x] 상태 확인 메소드
  - [x] Session 테스트 데이터 생성 방법 변경
  - [ ] AbstractEnrollments 추가
