<details>
<summary>LMS 과제 1단계</summary>

# LMS 과제 1단계
## 신규 추가된 모델
- [x] `Answers` : `answer`을 `List`로 저장
- [x] `TimeStrategy` : 현재 시각을 만들어내는 전략 패턴
- [x] `LocalDateTimeProvider` : 현재 시각을 `LocalDateTime.now()`로 만들어내는 전략 패턴

## 문제 요구사항 목록
### 질문 삭제하기 요구사항 (STEP2)
- [x] 질문 데이터를 삭제하는 것이 아닌, 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
- [x] 삭제가 가능한 조건은 아래와 같음
  - [x] 로그인 사용자와 질문한 사람이 같아야 함
  - [x] 답변이 없어야 함
  - [x] 단, 질문자와 답변글의 모든 답변자가 같은 경우 삭제 가능
    - [x] 질문자와 답변자가 다른 경우, 삭제 불가능
- [x] 질문을 삭제할 때 답변 또한 삭제해아하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경
- [x] 질문과 답변 삭제 이력에 대한 정보를 `DeleteHistory`를 활용해 남김

### 리펙터링 요구사항 (STEP3)
- [x] QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
- [x] QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현
- [x] QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이며,
  도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 함
- [x] 도메인 모델의 TC의 경우, No Mock으로 테스트 코드 작성

### 요구사항 변경 (STEP4)
- [x] 강의가 진행 중인 상태에서도 수강신청이 가능
  - [x] 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태값을 분리
- [x] 강의는 하나 이상의 커버 이미지를 가질 수 있음
- [ ] 우테코(무료), 우테캠 PRO(유료)와 같이 선발된 인원만 수강 가능
  - [ ] 강사는 수강신청한 사람 중 선발된 인원에 대해서만 수강 승인이 가능
  - [ ] 강사는 수강신청한 사람 중 선발되지 않은 사람은 수강 취소 가능

### 조건
- [x] get 메서드 사용없이, 메세지를 보내기
- [x] 일급 컬렉션 사용 (Question의 List를 일급 컬렉션으로 구현)
- [x] 3개 이상 인스턴스 변수를 가진 클래스를 사용하지 않기
- [x] 도메인 모델에 setter 사용하지 않기
- [ ] 리펙터링시 컴파일 에러와 기존의 단위 테스트의 실패를 최소화하며 점진적인 리펙터링 진행
- [ ] DB테이블에 데이터가 존재한다는 가정하에, 즉, 기존에 쌓인 데이터를 제거하지 않은 상태로 리펙터링

</details>

# (STEP 2) 🗓️ 수강신청 프로젝트
1. 기능 요구 사항
2. 프로그래밍 요구 사항
3. 코드 구조
4. 테스트 코드

## 1. 기능 요구 사항
- [x] 과정(Course)
  - [x] 기수 단위로 운영
  - [x] 여러개의 강의(Sessions)를 가질 수 있음
  - [x] 강의(Session)
    - [x] 시작일과 종료일을 가짐
    - [x] 강의 커버 이미지 정보를 가짐
      - [x] 이미지 크기는 1MB 이하
      - [x] 이미지 타입은 gif, jpe(jpeg 포함), png, svg만 허용
      - [x] 이미지의 width는 300 pixel, height은 200 pixel 이상
      - [x] width : height = 3 : 2
    - [x] 무료 강의, 유료 강의로 나뉨
      - [x] 무료 강의의 최대 수강 인원 제한은 없음
      - [x] 유료 강의는 강의 최대 수강 인원을 초과할 수 없음
      - [x] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청 가능
    - [x] 강의 상태를 가짐
      - [x] 준비중, 모집중, 종료 3가지 상태
      - [x] 강의 수강 신청은 강의 상태가 모집중일 때만 가능
    - [x] 유료 강의의 경우, 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현
    - [x] 이미 수강 신청 완료된 강의는 결제 불가능
    - [x] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반환
- [x] 결제(Payments)
  - [x] 결제를 완료한 결제 정보들을 저장
  - [x] 결제 항목 (Payment)
    - [x] 유료 강의 비용과 결제 금액이 동일한지 판단

## 2. 프로그래밍 요구 사항
- [x] 도메인 모델을 DB 테이블과 매핑하고 데이터를 저장
- [x] CRUD 쿼리와 코드 구현에 집중하기 보다, 테이블을 설개하고 맵핑하는데 집중
  - [x] Payment 테이블 맵핑을 고려하지 않아도 됨

## 3. 코드 구조
- [x] Course
  - [x] Session
    - [x] PaidSession
    - [x] FreeSession
    - [x] Session
      - [x] CoverImages
        - [x] CoverImage
          - [x] ImageTypeEnum
          - [x] ImageFileSize
          - [x] ImageSize (단위 : KB)
      - [x] SessionStatusEnum
      - [x] isOpenForEnrollment
      - [x] SessionPeriod
- [x] Enrollment
- [x] SessionRepository
- [x] EnrollmentRepository
- [x] CoverImageRepository
- [x] JdbcSessionRepository
- [x] JdbcCoverImageRepository
- [x] JdbcEnrollmentRepository

## 4. 테스트 코드
- [x] CourseTest
  - [x] 유료 강의의 경우, 무료 강의를 1개씩 가진 Course 구현
- [x] FreeSessionTest
  - [x] 수강 신청 가능 기한일때만 강의 신청 가능
  - [x] 수강신청하려는 sessionId와 동일한 결제내역이 있으나 (= 이미 결제를 했음), 결제금액이 다를 경우 신청 불가능
  - [x] 유저가 수강 신청을 이미 완료한 강의는 신청 불가능
- [x] PaidSessionTest
  - [x] 수강 신청 가능 기한일때만 강의 신청 가능
  - [x] 수강 신청 인원이 마감됐을 경우 강의 신청 불가능
  - [x] 수강 신청 하려는 sessionId와 동일한 결제내역이 있으나 (= 이미 결제를 했음), 결제금액이 다를 경우 수강 신청 불가능
  - [x] 유저가 수강 신청을 이미 완료한 강의는 신청 불가능
- [ ] CoverImagesTest
  - [ ] 1개 이상의 커버 이미지를 가져야 함
- [x] CoverImageTest
  - [x] ImageTypeTest
    - [x] gif, jpg, jpeg, png, svg 만 허용
  - [x] ImageSizeTest
    - [x] width = 300 pixel 이상만 허용
    - [x] height = 200픽셀 이상만 허용
    - [x] width : height = 3 : 2만 허용
  - [x] ImageFileTest
    - [x] 1MB 이하만 가능
- [x] SessionStatusTest
  - [x] 강의 수강 신청은 강의 상태가 모집중일 때만 가능
- [x] SessionPeriodTest
  - [x] 강의 종료 시간이 시작 시간보다 앞설 수 없음
- [x] SessionRepositoryTest
  - [x] 무료강의를 저장하고 정상적으로 읽어옴
  - [x] 유료강의를 읽어오고 정상적으로 읽어옴
- [x] CoverImageRepositoryTest
  - [x] 커버 이미지를 정상으로 읽어옴
  - [x] 커버 이미지를 정상으로 저장
- [x] EnrollmentRepository
  - [x] 유저의 수강신청 정보를 정상으로 저장
  - [x] sessionId와 userId로 수강신청 정보 조회 정상 저장
