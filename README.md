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
* [ ] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
  * [X] question 삭제 요청 시 loginUser와 다른 경우 exception throw
  * [X] question 삭제 요청 시 loginUser와 같으면 삭제한다.
  * [ ] delete함수에서 권한체크를 하다보니, 불필요한 answer loop가 발생함.
* [ ] 답변이 없는 경우 삭제가 가능하다.
  * [ ] 질문에 답변이 있는 경우 삭제불가 exception throw.
* [ ] 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
  * [ ] 답변자가 하나라도 다른 경우 삭제할 수 없다.
  * [ ] answer의 validate는 answers에서 판단한다.
* [ ] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
  * [X] answer -> 삭제하는 경우 상태가 변경한다. (매개변수 삭제)
  * [X] answer 삭제 요청 시 loginUser와 다른 경우 exception throw
  * [X] answer 삭제 요청 시 loginUser와 같으면 삭제한다.
* [ ] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
* [ ] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## step1 리펙토링 요구사항
* [ ] deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
  * [ ] 여기서는 삭제하는 하는 역할을 갖는다.
  * [ ] 매개변수에 question 객체를 전달한다.
  * [ ] deleteHistories -> 삭제내역 liset 저장하는 일급콜렉션 생성
* [ ] QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현

## **힌트**
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
    - Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
    - 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 도메인 모델에 setter 메서드 추가하지 않는다.

## step1 리팩토링 todo
* [ ] question -> 
  * [ ] 여러 Answer를 저장하는 일급콜렉션 클래스를 생성한다.
  * [X] setTitle 삭제
  * [X] setContents 삭제
  * [X] setDeleted -> delete로 변경
  * [X] 인스턴스변수에 final
  * [ ] 
* [ ] answers -> 
  * [ ] answer 유저와 질문자가 같은지 find.
* [ ] NsUser ->
  * [ ] 
* [ ] questionRespository -> 
  * [ ] save method 생성
  * [ ] Question 객체 id가 자동 증가하며 저장 (find max id)
* [ ] deleteHistories -> 삭제이력을 쌓는 경우 createDate 매개변수 줄이기
* [ ] answer -> 로그인 유저와 답변 유저가 동일한지 체크한다.
* [ ] AOP class 활용(생성시간, 업데이트 시간)
