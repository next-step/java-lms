# 1단계 - 레거시 코드 리팩토링
## 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제가 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든 답변자가 같은 경우에 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 상태 또한 삭제 상태(deleted)를 변경한다.
- 질문자와 답변자가 다른경우 답변을 삭제 할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory 를 활용해 남긴다.

## 리팩터링 요구사항
- nextstep.qna.service.QnaService 의 deleteQuestion() 은 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService 의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비즈니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService 의 비즈니스 로직을 도메인 모델로 이동하는 리팩토링을 진행 할 때 TDD 로 구현한다.
- QnaService 의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest 이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest 의 모든 테스트는 통괴해야 한다.

## Service -> 도메인 모델 리팩터링
AS-IS  
![AS-IS 이미지](https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/2020-04-08T11%3A45%3A28.056legacy_refactoring_1.png)
TO-BE
![TO-BE 이미지](https://nextstep-storage.s3.ap-northeast-2.amazonaws.com/2020-04-08T11%3A45%3A40.213legacy_refactoring_3.png)

## 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
- Question 의 List 를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 도메인 모델에 setter 메서드 추가하지 않는다.