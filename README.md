# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)
## step1 기능 목록
* Question
    * [ ] 삭제 가능한지 검증
    * [ ] 상태값 변경
* Answers: List<Answer>를 관리하는 일급 컬렉션
    * [ ] 삭제 메시지를 각각의 Answer에게 전달
* Answer
    * [ ] 삭제 가능한지 검증
    * [ ] 상태값 변경

## step2 기능 목록
## Todo
- [x] SessionStatus Enum 생성
  - READY, RECRUITING, END
- [x] FreeSession 수강 신청
  - status가 모집 중이 아닌 경우 IllegalStateException
  - 정상 완료되면, 현재 수강인원 increase 1
- [x] PaySession 수강 신청
  - status가 모집 중이 아닌 경우 IllegalStateException
  - payment의 결제금액이 수강료가 일치하지 않는 경우 IllegalArgumentException
  - 최대 수강인원 <= 현재 수강인원인 경우 IllegalStateException
  - 정상 완료되면, 현재 수강인원 increase 1
- [x] ImageType Enum 생성
  - GIF, JPG, JPEG, PNG, SVG
- [x] CoverImageInfo 클래스 생성
  - 생성자(크기, 타입, 너비, 높이)
    - 이미지 크기 1 초과인 경우 IlleaglArgumentException
    - 이미지 타입이 ImageType에 없는 경우 IlleaglArgumentException
    - width >= 300 && height >= 200 && width / heigth == 3/2아 아닌 경우 IlleaglArgumentException
- [x] Session 추상클래스 생성
  - 생성자(강의 시작일, 강의 종료일, 커버 이미지 정보)
- [x] 서비스 레이어에서 수강신청 로직 구현

## step3 TODO
- [x] 각 도메인에 대한 DDL 작성
  - Session 슈퍼/서브 타입은 Single Type 채택
  - session
    - startDate
    - endDate
    - status
    - numberOfStudents
    - maxNumberOfStudents
    - price
    - cover_image (fk)
  - cover_image
    - size
    - type
    - width
    - height
- [x] CoverImage 리포지토리
  - [x] 인터페이스 작성
  - [x] 인터페이스 구현
- [x] Session 리포지토리
  - [x] 인터페이스 작성
  - [x] 인터페이스 구현
- [x] 서비스 레이어에서 수강신청 로직 구현

## step4 TODO
변경된 기능 요구사항
- [ ] 강의 진행 상태(준비중, 진행중, 종료)와 모집 상태(비모집중, 모집중)로 상태 값 가져야 한다
  - 진행 상태와 무관하게 모집 상태로만 수강신청 가능유무 판단
- [ ] 선발 인원을 관리하기 위해 Session과 NsUser를 매핑하는 Selection 매핑 클래스 구현
  - nsUser (NsUser) - 선발된 유저
  - session (Session) - 선발된 세션
  - hasPaid (boolean) - 유저 결제완료 여부
- [ ] Payment에서 nsUser가 해당 Session에 선발되지 않았을 경우 예외처리
- [ ] Session의 coverImageInfo를 List로 리팩터링

