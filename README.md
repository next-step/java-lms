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
* [X] 질문자와 답변자가 다른경우 답변을 삭제할 수 없고 예외가 발생한다.

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

로직을 옮기고나니 answer.isOwner가 눈에 들어왔다
이 로직도 Question 처럼 Answer 자기자신이 처리하면 좋을거 같았다.
메서드명으로는 뭐가 좋을까?... 항상 이런게 고민이다.
id 값이 같은지 판별해서 예외가 발생하는거니까 isDeleted?가 좋을까
보통 is가 앞에 붙으면 리턴 값이 boolean 값인거 같으니까
validateLoginUser 정도가 적당할까 ?
단위테스트 mock 테스트 모두 통과 !

수정을하고보니  answers로 반복문을도는 로직이 보였다. 똑같은 자료 구조를 반복문을 두번 돌리는게 조금 이상하다
반복문을 한번 돌릴 때 예외, 삭제처리를 해주면 좋지 않을까란 생각이 들었다.
반복문을 합쳐주고 mock 테스트 단위 테스트 통과!

answers 자료구조에 너무 많은 로직들과 answer의 getter, setter가 사용되고 있다.
일단 반복문의 로직을 Answers라는 일급 컬렉션을 만들어줄 필요가 있을까 ?
Answers에서 List<DeleteHistory>를 리턴하는 메서드를 만들어서 한번에 처리해주면 좋을거같단 생각이 들었다
deleteHistories.add(new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now()));
deleteHistories.addAll(answers.delete(loginUser));
뭐 이런 식으로 ? 이렇게 접근하는게 맞는건가 ,,,...
Answers에 로직 이동 후 테스트 통과!

아직 해결하지못한 문제는 Answers에있는 getter, setter이다
이건 Question과 똑같이 delete 메서드로 묶어서 DeleteHistory를 리턴하는 메서드로 만들어주면 좋을거같다.


# 2단계 - 수강 신청(도메인 모델)
## 수강 신청 기능 요구사항
- 과정(Course)은 기수 단위로 운영하며, 여러 개의 강의(Session)를 가질 수 있다.
- 강의는 시작일과 종료일을 가진다.
- 강의는 강의 커버 이미지 정보를 가진다.
  - 이미지 크기는 1MB 이하여야 한다.
  - 이미지 타입은 gif, jpg(jpeg 포함),, png, svg만 허용한다.
  - 이미지의 width는 300픽셀, height는 200픽셀 이상이어야 하며, width와 height의 비율은 3:2여야 한다.
- 강의는 무료 강의와 유료 강의로 나뉜다.
  - 무료 강의는 최대 수강 인원 제한이 없다.
  - 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
  - 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
- 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
- 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
  - 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

## TODO
* [X] 이미지의 크기 1MB 초과하면 예외가 던져진다
* [X] 이미지 타입이 gif, jpg, jpeg, png, svg 아니면 예외가 던져진다
* [X] 이미지의 width는 300픽셀, height는 200픽셀이 아니면 예외가 던져진다
* [X] width와 height의 비율이 3:2가 아니면 예외가 던져진다

* [X] 강의 종료일이 시작일보다 빠르면 예외가 던져진다
* [x] 강의는 시작일과 종료일을 가진다

* [X] 강의 최대 수강 인원을 초과하면 예외가 던져진다
* [X] 수강생이 똑같은 강의를 수강신청하면 예외가 던져진다
* [ ] 모집중이 아닌 강의를 수강신청하면 예외가 던져진다