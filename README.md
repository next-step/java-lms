# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

# 1단계 - 레거시 코드 리팩터링
## 질문 삭제하기 요구사항
- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든답변자 같은경우 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경 한다.
- 질문자와 답변자가 다른경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

## 리팩터링 요구사항
- nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
- QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

## TODO
* [X] 자신이 작성하지 않은 질문은 삭제할 수 없어서 예외가 발생한다.

### 작업을 하면서 생각했던 것들 
일단 제일 작은 단위부터 TDD를 해보자
내가 봤을때 제일 작은 단위는 QnAService에 해당 질문이 자신의
질문이 아니면 예와가 발생하는 로직 인거 같다.
그래서 검증하는 test code를 만들어주고 Question 클래스에 
delete 메서드를 추가 후 if문을 안으로 옮겨줬다.

두번째로는 눈에 들어온것은 question.getAnswers(), question.setDeleted(),
questionId, question.getWriter() 수많은 getter와 setter들이였다.
그래서 든 생각은 question.delete() 메서드에 모든 로직을 넣어주는건 어떨지..
모든 로직이 들어간다면 getAnswers, setDeleted, questionId, getWriter
get set을 안쓸 수 있지 않을까 questionId같은 경우는 이 값으로 찾아온 question이기 때문에 
question에 있는 인스턴스변수 id를 사용하면 될거같다.
로직을 Question으로 옮긴 후 기존 테스트 통과 


