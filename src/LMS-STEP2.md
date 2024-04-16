## Step 2 : 수강신청(도메인 모델)

### 📚 수강신청 기능 요구사항
```text
 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
 강의는 시작일과 종료일을 가진다.
 강의는 강의 커버 이미지 정보를 가진다.
    이미지 크기는 1MB 이하여야 한다.
    이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
    이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
️ 강의는 무료 강의와 유료 강의로 나뉜다.
    무료 강의는 최대 수강 인원 제한이 없다.
    유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
    유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
    결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.
```
### 해야할 일
- [X] Step1 리뷰 내용 반영
- [X] 도메인 구조 구상
- [ ] 기능 구현 및 테스트
### 기능
- [X] Enum으로 만들어야 할 부분 생성
    - ImageType, SessionStatus, SessionType
- [X] FreeSession 수강신청
    - SessionStatus RECRUIT가 아니면 IllegalStateException
    - 수강 신청 후 수강 인원 inscrese
- [X] PaidSession 수강신청
    - SessionStatus RECRUIT가 아니면 IllegalStateException
    - 최대수강인원 보다 현재 수강인원이 작거나 같으면 IllegalStateException
    - 정상적인 수강 신청 후 수강 인원 inscrese
    - Payment의 결제금액이 수강료와 일치 하지 않는 경우 IllegalStateException
- [X] CoverImage
    - 생성자(크기, 타입, 너비, 높이)
        - 이미지 크기 1MB 초과하면 IllegalStateException
        - 이미지 타입 ImageType(Enum)에 없으면 IllegalStateException
        - width >= 300 && height >= 200 && width / heigth == 3/2가 아닌 경우 IlleaglArgumentException
- [X] Session 추상 클래스로 생성
    - 생성자(강의 시작일, 강의 종료일, 커버이미지정보)
- [X] Course에 List< Session> -> Sessions 일급컬랙션
### 도메인 책임 및 구조
- 과정 Course
    - 강의 Sessions List< Session>
        - 기수 Id
        - 강의 타입 SessionType - Enum
            - 유료 PaidSession
            - 무료 FreeSession
        - 강의 기간 SessionPeriod - VO
            - 시작일 startDt
            - 종료일 endDt
        - 커버 이미지 정보 CoverImage
            - 이미지 크기 size
            - 이미지 타입 ImageType - Enum
            - 이미지 너비 width
            - 이미지 높이 height
        - 강의 상태 SessionStatus - Enum
            - 준비중 PREPARE
            - 모집중 RECRUIT
            - 종료 END
        - 학생 Students List< NsUser >
        - 수업료 Fee
        - 최대수강인원 maximumCapacity
- 결제 Payments
