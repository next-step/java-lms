# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

## 기능 목록

### Question
- [x] 로그인 사용자와 질문한 사람이 같지 않을 경우 `CannotDeleteException`을 발생한다.
- [x] questionId에 해당하는 question이 없을 경우 `NotFoundException`이 발생한다.
- [x] 삭제가능 할 경우, 삭제시 deleted 필드 값을 true로 변경한다.
- [x] Question 삭제시 Answer 삭제 되는 기능 추가
- [x] Question에서 answers list를 일급 컬렉션으로 분리하기. -> answers
  - 컬렉션에 추가 기능 테스트
  - 컬렉션에 null 값 입력시 NPE 발생  테스트
  - answer 삭제하는 메서드 추가
    - 리스트에 아무 값 없어도 호출 시 예외 발생 x
### Answer
- [x] 질문자와 답변글의 모든 답변자 같은 경우 답변을 삭제 가능하다. 
- [x] 질문자와 답변자가 같지 않을 경우 `CannotDeleteException`을 발생하며 답변을 삭제할 수 없다.
- [x] 삭제가능 할 경우, 삭제시 deleted 필드 값을 true로 변경한다.

### DeleteHistory
- [x] 질문을 삭제 할 때, 질문에 대한 답변까지 삭제를 해야한다.
- [x] 삭제시 `Question`과 `Answer`의 deleted 상태를 true로 삭제한다.
- [x] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
- [x] DeleteHistory를 List로 가지는 일급 컬렉션 추가
  - [x] question을 add 하면 question에 대한 DeleteHistory를 만들어 리스트에 추가한다. 
  - [x] answer을 add 하면 question에 대한 DeleteHistory를 만들어 리스트에 추가한다.

## Step 2 기능목록
- [x] Student는 수강 신청을 할 수 있다.
  - [x] 수강 신청 시 강의 (Session)의 현재 수강 인원이 1 증가한다.
  - [x] 최대 수강 인원과 현재 인원이 동일하다면 예외를 발생한다.
  - [x] 수강 신청은 강의의 상태가 `모집중`일 때만 가능하다.
    - [x] `준비중`, `종료`의 경우 수강신청 시 예외가 발생한다.
  - [x] student는 학생을 식별 할 수 있는 id를 갖는다.

- 강의 (Session)은 
  - [x]시작일과 종료일을 가진다.
  - [x] 강의 커버 이미지 정보를 가진다.
  - [x] 무료 / 유료 강의로 나뉜다.

- [x] 과정 (Course)는 기수 단위로 강의(Session)를 가질 수 있다.
  - [ ] 강의를 과정에 추가하고, 기수에 따라 강의를 조회 할 수 있다.
  - [ ] 기수 유효하지 않다면 (음수 / 초과 기수 조회) 예외를 발생한다.
  
### 수강 신청 기능 요구사항
- 과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다.
- 강의는 시작일과 종료일을 가진다.
- 강의는 강의 커버 이미지 정보를 가진다.
- 강의는 무료 강의와 유료 강의로 나뉜다.
- 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
- 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
- 강의는 강의 최대 수강 인원을 초과할 수 없다.
