# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## TODO
### Step1
- [x] Question, Answer를 삭제할 권한이 있는지 검증하는 로직을 분리한다.
- [x] DeleteHistries에 추가하하는 로직을 분리한다.
- [x] TEST를 먼저 작성한다.
- [x] 상태 데이터를 꺼내지 말고 메시지를 보내도록 구현한다.
- [x] 일급 컬렉션을 적용한다.
- [X] 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- [x] 도메인 모델에 setter 메서드 추가하지 않는다.

#### 추가 고민 사항
Question 인스턴스 변수를 줄일 수 없을 지
delete 내부에서 DeleteHistories 를 생성하는 것과 실제 삭제 로직을 분리하는건 어떤지 고민해보기
validate도 마찬가지

## 도메인 설계
- Session (상위클래스)
  - 필드
    - 강의 시작일 (startDate: LocalDateTime)
    - 강의 종료일 (endDate: LocalDateTime)
    - 커버 이미지 정보 (coverImageInfo: CoverImageInfo)
    - 상태 (status: SessionStatus)
    - 현재 수강인원 (numberOfStudents: int)

- 커버 이미지 정보(CoverImageInfo)
  - 필드
    - 이미지 크기(size: int)
    - 이미지 타입(type: ImageType)
    - 이미지 너비(width: int)
    - 이미지 높이(height: int)
  - 생성 조건
    - 이미지 크기는 1MB 이하여야 한다.
    - 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
    - width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 heigth의 비율은 3:2여야 한다.

- 무료 강의(FreeSession) extends Session
  - 필드
    - 강의명 (name: String)
  - 메서드
    - 수강신청 (enrolment() -> void)
    - status가 모집 중이 아닌 경우 IllegalStateException
- 유료 강의(PaySession) extends Session
  - 필드
    - 강의명 (name: String)
    - 최대 수강인원 (maxNumberOfStudents: int)
    - 수강료 (price: long)
  - 메서드
    - 수강신청 (enrolment(payment: Payment) -> void)
      - status가 모집 중이 아닌 경우 IllegalStateException  
      - payment의 결제금액이 수강료가 일치하지 않는 경우 IllegalArgumentException
      - 최대 수강인원 <= 현재 수강인원인 경우 IllegalStateException
      - 예외 상황 없을 시 현재 수강인원 1 증가

## Todo
- [X] SessionStatus Enum 생성
  - READY, RECRUITING, END
- [X] FreeSession 수강 신청
  - status가 모집 중이 아닌 경우 IllegalStateException
  - 정상 완료되면, 현재 수강인원 increase 1
- [X] PaySession 수강 신청
  - status가 모집 중이 아닌 경우 IllegalStateException
  - payment의 결제금액이 수강료가 일치하지 않는 경우 IllegalArgumentException
  - 최대 수강인원 <= 현재 수강인원인 경우 IllegalStateException
  - 정상 완료되면, 현재 수강인원 increase 1
- [X] ImageType Enum 생성
  - GIF, JPG, JPEG, PNG, SVG
- [X] CoverImageInfo 클래스 생성
  - 생성자(크기, 타입, 너비, 높이)
    - 이미지 크기 1 초과인 경우 IlleaglArgumentException
    - 이미지 타입이 ImageType에 없는 경우 IlleaglArgumentException
    - width >= 300 && height >= 200 && width / heigth == 3/2아 아닌 경우 IlleaglArgumentException
- [X] Session 추상클래스 생성
  - 생성자(강의 시작일, 강의 종료일, 커버 이미지 정보)
- [X] 각 도메인에 대한 DDL 작성
    - Session 슈퍼/서브 타입은 Single Type 채택
    - session
      - startDate
      - endDate
      - status
      - numberOfStudents
      - maxNumberOfStudents
      - price
      - cover_image_info (fk)
    - cover_image
      - size
      - type
      - width
      - height
- [X] CoverImage 리포지토리
  - [X] 인터페이스 작성
  - [X] 인터페이스 구현
- [ ] Session 리포지토리
  - [ ] 인터페이스 작성
  - [ ] 인터페이스 구현
- [ ] 서비스 레이어에서 수강신청 로직 구현 (?)