## 해야할 일
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
## 요구사항
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.
## 기능 
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
## 힌트
객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
규칙 8: 일급 콜렉션을 쓴다.
Question의 List를 일급 콜렉션으로 구현해 본다.
규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
인스턴스 변수의 수를 줄이기 위해 도전한다.
도메인 모델에 setter 메서드 추가하지 않는다.
## 리뷰 내용
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