# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## step1 질문 삭제하기 요구사항
* [ ] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
  * [X] question -> 삭제를 하는 경우 상태가 변경한다. (매개변수 삭제)
  * [ ] QnAService에 question 객체를 전달
* [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
  * [X] question 삭제 요청 시 loginUser와 다른 경우 exception throw
  * [X] question 삭제 요청 시 loginUser와 같으면 삭제한다.
* [X] 답변이 없는 경우 삭제가 가능하다.
  * [X] 질문에 답변이 있는지 체크하고 없으면 삭제한다.
* [ ] 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
  * [ ] question에 해당하는 모든 답변을 조회할 수 있다.
  * [X] 답변자와 질문자가 다른지 체크할 수 있다.
  * [ ] 답변자가 하나라도 다른 경우 삭제할 수 없다.
  * [ ] 답변중에 loginUser와 하나라도 다른 경우 exception throw
  * [ ] answer의 삭제validate는 answers에게 물어본다.
  * [ ] 답변이 loginUser와 모두 같으면 삭제한다.
* [ ] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
  * [X] answer -> 삭제하는 경우 상태가 변경한다. (매개변수 삭제)
* [ ] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
  * [ ] 질문자와 답변자가 다른 경우 exception Throw
* [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
* [X] 질문이 추가되는 경우 답변이 추가된다.

## **힌트**
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
    - Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 도메인 모델에 setter 메서드 추가하지 않는다.

## step1 리펙토링 요구사항
- nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

## step1 리팩토링 todo
* [ ] question -> 
  * [ ] 여러 Answer를 저장하는 일급콜렉션 클래스를 생성한다.
  * [X] setTitle 삭제
  * [X] setContents 삭제
  * [X] setDeleted -> delete로 변경
  * [X] 인스턴스변수에 final
* [ ] Answers indent줄이기
* [ ] questionRespository -> 
  * [ ] save method 생성
  * [ ] Question 객체 id가 자동 증가하며 저장 (find max id)
* [ ] deleteHistories -> 삭제이력을 쌓는 경우 createDate 매개변수 줄이기
* [ ] AOP class 활용(생성시간, 업데이트 시간)

## 궁금한점
* [ ] answers를 처음에 빈collection으로 만들고, 계속 add를 해서 새로운 객체로 반환하면 계속해서 인스턴스 생성되는게 아닌지?