# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---
### 🚀 1단계 - 레거시 코드 리팩터링
#### <질문 삭제하기> 요구사항
* [x] 질문 데이터의 삭제 상태를 변경한다. (deleted = true)
* [x] 로그인 사용자 == 질문글 작성자인 경우 삭제 가능
* [x] 질문글에 답변글이 없는 경우 삭제 가능
* [x] 질문을 삭제하면 답변 또한 삭제한다.
  * [x] 답변의 삭제 상태를 변경한다. (deleted = true)
  * [x] 답변글 작성자 != 로그인 사용자인 경우 답변글을 삭제할 수 없다.
  * [x] 질문글 작성자 != 답변글 작성자인 경우 답변글을 삭제할 수 없다.

#### 리팩터링 요구사항
* [x] QnaService의 deleteQuestion() 메서드의 핵심 비지니스 로직을 도메인 모델 객체에 구현
  * [x] TDD로 구현
* [x] 리팩토링 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

#### Todo
* [x] 답변을 삭제한다. (deleted = true)
* [x] 질문을 삭제한다. (deleted = true)
* [x] 답변과 질문의 삭제 이력을 가져온다.

#### Feedback 23.11.26
* [x] Wrapper 클래스에서 변수명은 values, value, 값을 가져오는 메서드명은 value(), get()으로 사용해 보기
* [x] delete 작업 후에 deleteHistories를 즉시 반환하도록 변경
* [x] Answer의 delete()가 답변만 삭제하고 싶을 경우에도 대응할 수 있도록 변경

---
### 🚀 2단계 - 수강신청(도메인 모델)
#### <수강신청> 기능 요구사항
* [x] 과정(Course)은 기수 단위로 운영되며, 여러개의 강의(Session)을 가질 수 있다.
* [x] 강의(Session)
  * [x] 시작일과 종료일을 가진다.
  * [x] 강의 커버 이미지를 가진다.
    * [x] 이미지 크기는 1MB 이하이다.
    * [x] 이미지 타입은 gif, jpg(jpeg 포함), png, svg만 허용한다.
    * [x] 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
  * [x] 무료강의와 유료강의로 나뉜다.
    * [x] 무료 강의는 최대 수강 인원의 제한이 없다.
    * [x] 유료 강의는 최대 수강 인원을 초과할 수 없다.
    * [x] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
    * [x] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
      * [x] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.
  * [x] 준비중, 모집중, 종료 3가지 상태를 가진다.
* [x] 수강신청
  * [x] 로그인 유저는 강의를 대상으로 수강신청을 할 수 있다.
  * [x] 수강신청은 모집중 상태인 경우에만 가능하다.

#### 프로그래밍 요구사항
* [x] DB 테이블 설계 없이 도메인 모델부터 구현한다.
* [x] 도메인 모델은 TDD로 구현한다. 단, Service 클래스는 단위 테스트가 없어도 된다.

#### Feedback 23.12.15
* [x] 이미지 타입을 관리할 수 있는 enum을 사용해보기
* [x] 항상 인자가 필요한 경우 기본 생성자를 삭제하기
* [x] 이미지 가로, 세로 비율을 구할 때 부동소수점인 Double 외에 곱셈 혹은 BigDecimal을 사용해보기
  * [x] 코드 가독성과 속도를 고려하여 BigDecimal이 아닌 곱셈으로 수정
* [x] 강의의 기간을 관리하는 클래스를 만들어서 유효성 검사를 진행해보기
* [x] 강의에 필수 값이 있다면 생성 시 검증을 진행해보기
* [x] 클래스 네임에 Info, Data는 사용하지 않고, 의미를 보다 명확히 하기
* [x] 수강 신청이 완료되면 수강 인원이 늘어야 할 것
* [x] 파일 마지막의 newLine에 대해 공부해 보고, 누락된 부분을 추가하기

#### Feedback 23.12.07
* [x] userNumber를 가져오는 방식에 대한 고민
* [x] 값이 필요한 객체의 경우 기본생성자를 제공하지 않고, 추가적인 validate를 진행

---
### 🚀 3단계 - 수강신청(DB 적용)
#### 요구사항
* [x] 2단계에서 구현한 도메인 모델을 DB 테이블과 매핑하고, 데이터를 저장한다.
  * [x] schema.sql 에 DB 테이블 추가
    * [x] Session
    * [x] CoverImage
    * [x] NsUserSession
  * [x] CRUD 코드 추가
    * [x] sessionRepository
    * [x] coverImageRepository
  * [x] CRUD 코드에 대한 테스트 코드 추가
    * [x] sessionRepository

#### Feedback 23.12.12
* [ ] CoverImage와 Sesssion의 관계 고민
  * [ ] Session은 CoverImage를 가진다.
  * [ ] 수강신청 시 CoverImage의 정보는 필요하지 않다.
  * [ ] CoverImage는 sessionId를 반드시 안다.
* [ ] Repository 와 DAO의 차이를 알아보고 설계를 수정
* [x] CoverImage의 중복된 validation 제거
* [x] Wrapper 타입과 Primitive 타입의 쓰임새 확인 후 수정
* [x] SessionPaymentCondition 내 메서드의 적합한 명명 고민
* [x] 수강신청 로직 수정(검증)
* [ ] 객체 저장 후 id값을 반환하도록 하기
* [x] Class 레벨의 @Transactional 활용
