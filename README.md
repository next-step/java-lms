# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## Step 1 : 레거시 코드 리팩토링
### 해야할 일
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
### 요구사항
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.
### 기능
- [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- [X] 로그인 사용자와 질문한 사람이 같은 경우 질문 삭제가 가능하다.
- [X] 답변이 없는 경우 질문 삭제가 가능하다.
- [X] 질문자와 답변글의 모든 답변자 같은 경우 질문 삭제가 가능하다.
- [X] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- [X] 질문자와 답변자가 다른경우 답변을 삭제 할 수 없다.
- [X] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
## 테스트
- [X] QnaServiceTest 이상 없음
- [X] QuestionTest
- [X] AnswerTest
- [X] AnswersTest
### 힌트
객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
규칙 8: 일급 콜렉션을 쓴다.
Question의 List를 일급 콜렉션으로 구현해 본다.
규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
인스턴스 변수의 수를 줄이기 위해 도전한다.
도메인 모델에 setter 메서드 추가하지 않는다.
### 리뷰 내용
```
Answer
메서드의 이름은 delete인데 아래 두가지의 책임을 가지고 있네요.
delete로 상태 변경
삭제 이력 인스턴스 생성 및 반환
그리 중요한 부분은 아니고 delete라는 이름에서 유추가 가능하기에 변경할 필요는 없으나 단일 책임 원칙과 메서드 네이밍 측면에서 한번 고민해보시면 좋겠습니다.

QnAService
도메인 클래스를 통해 책임을 잘 추출해주셨습니다.
다만 지금 QnAService는 말 그대로 Question과 Answer을 다루는 클래스인데 DeleteHistory를 다루고 있습니다. 물론 Question과 Answer의 삭제에 대한 부분이라 연관이 없는 것은 아니나 생성이 아닌 삭제 라이프사이클만 연관이 있다는 부분을 고려하면 응집도가 떨어지고 불필요하게 결합이 발생하고 있다고 볼 수 있습니다.
여기서 이벤트를 통해 결합을 줄이고 응집도를 높여볼 수 있을 것 같아요. 이번 단계에 꼭 반영할 필요는 없지만 관련해서 한번 고민해보시면 좋겠습니다. 물론 EventPublisher와의 결합이 생기겠지만 이후 다양한 처리 로직이 생기더라도 추가적인 결합은 생기지 않을 것이므로 장점이 있을 것 같아요.

QeustionTest
삭제할 수 없는 여러 예외 상황에 대해서도 테스트를 작성해보면 어떨까요?

Ansers
Answers나 Answer 도메인 클래스에 대한 테스트도 작성해볼 수 있지 않을까요?
```
### TIL
        verify(applicationEventPublisher, times(1))
                .publishEvent(new DeleteHistoryEvent(qnAService, DeleteHistories.of(question)));
        이 경우 Mockito 사용하면 DeleteHistoryEvent에 hashCode, equals Override 꼭 하기

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
### 리뷰 내용
```
SessionFee.java
  중요한 부분은 아니고, SessionFee의 경우 일반적으로 long의 범위를 넘지는 않겠지만 
  금액을 다루는 경우는 long이나 double, int가 아닌 BigDecimal과 같은 타입을 사용하는 경우가 많습니다. 참고 부탁드려요.

ImageType.java
  중요한 부분은 아니나 이펙티브 자바에서는 파라미터가 하나인 경우 from, 
  파라미터가 여러개인 경우 of로 네이밍하도록 가이드하고 있습니다. 대부분의 개발자들이 일반적으로 이를 따를 것이므로 해당 규칙에 맞게 정의해도 좋을 것 같아요.

PaidSession.java
  그리 중요한 부분은 아니나 아래의 객체지향 생활 체조 원칙을 위반하고 있습니다. if를 여러개 쓰는 것과 else를 쓰는 것은 크게 다르지 않습니다.
  규칙 2: else 예약어를 쓰지 않는다.
  각각의 경우를 메서드로 추출하는 경우 의도도 명확히 드러나고 위의 규칙을 지킬 수 있을 것 같아요.
 
CoverImage.java
  일반적으로 데이터베이스에 파일을 바로 저장하지 않고 링크 정도만 저장하기 때문에 
  실제 데이터베이스와 연동되는 경우 도메인 클래스의 validation이나 클래스 형태가 조금 달라질 순 있을 것 같네요.
 
PaidSession.java
  모집중이 아닌 경우에 대해서는 무료 강의, 유료 강의 모두 검증이 필요합니다. 
  현재 이 부분이 각각 구현되어 있는데요. 파편화되어 도메인 규칙이 관리되고 있어 규칙이 변경된 경우 한쪽에 반영되지 않아 버그가 생길 수 있을 것 같습니다.

FreeSession.java
  이미 등록한 유저 목록을 가져와 넣어주어야 하지 않을까요? 데이터베이스쪽을 구현하면서 추가해도 상관없겠지만 현재 기준으로도 유저에 대한 정보를 생성자에서 받도록 해야할 것 같아요.
  
FreeSession.java
  maxCapacity 정보는 FreeSession에서 알 필요가 있을까요?

PaidSession.java
  요구사항엔 없지만 이미 추가된 수강생이 또 추가되는 경우도 생길 수 있을까요? 이에 대한 검증이 추가되면 어떨까요?  
```
### TIL
```
이펙티브 자바에서는 파라미터가 하나인 경우 from, 파라미터가 여러개인 경우 of로 네이밍하도록 가이드하고 있습니다. 


java.lang.UnsupportedOperationException은 불변 리스트에 요소를 추가하려고 할 때 발생하는 일반적인 예외입니다. 
예외가 발생하는 이유는 List.of() 메서드로 생성된 리스트는 수정할 수 없는 불변 리스트이기 때문입니다.
생성자 생성시 new ArrayList<>(students)로 해결...
```
