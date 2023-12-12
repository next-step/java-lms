## step1 질문 삭제하기 요구사항
* [X] 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
    * [X] question -> 삭제를 하는 경우 상태가 변경한다. (매개변수 삭제)
* [X] 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
    * [X] question 삭제 요청 시 loginUser와 다른 경우 exception throw
    * [X] question 삭제 요청 시 loginUser와 같으면 삭제한다.
* [X] 답변이 없는 경우 삭제가 가능하다.
    * [X] 질문에 답변이 있는지 체크하고 없으면 삭제한다.
* [X] 질문자와 답변 글의 모든 답변자 같은 경우 삭제가 가능하다.
    * [X] question에 해당하는 모든 답변을 조회할 수 있다.
    * [X] 답변자와 질문자가 다른지 체크할 수 있다.
    * [X] 답변 중에 questionUser와 하나라도 다른 경우 exception throw
    * [X] 답변이 loginUser와 모두 같으면 삭제한다.
* [X] 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
    * [X] answer -> 삭제하는 경우 상태가 변경한다. (매개변수 삭제)
* [X] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
    * [X] 질문자와 답변자가 다른 경우 exception Throw
* [X] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
* [X] 질문이 추가되는 경우 답변이 추가된다.

## step1 리팩토링 todo
* [X] question ->
    * [X] 여러 Answer를 저장하는 일급콜렉션 클래스를 생성한다.
    * [X] setTitle 삭제
    * [X] setContents 삭제
    * [X] setDeleted -> delete로 변경
    * [X] 인스턴스변수에 final
* [X] Answers indent줄이기
* [ ] questionRespository ->
    * [ ] save method 생성
    * [ ] Question 객체 id가 자동 증가하며 저장 (find max id)
* [X] deleteHistories -> 삭제이력을 쌓는 경우 createDate 매개변수 줄이기
* [ ] AOP class 활용(생성시간, 업데이트 시간)
* [ ] 자주 사용되고 있는 인스턴스변수 포장해보기

## 궁금한점
* [X] answers를 처음에 빈 collection으로 만들고, 계속 add를 해서 새로운 객체로 반환하면 계속해서 인스턴스 생성되어 메모리 이슈는 없을지
