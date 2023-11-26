# 학습 관리 시스템(Learning Management System)

<!-- TOC -->

* [학습 관리 시스템(Learning Management System)](#학습-관리-시스템learning-management-system)
  * [진행 방법](#진행-방법)
  * [온라인 코드 리뷰 과정](#온라인-코드-리뷰-과정)
  * [Step1 (레거시 코드 리팩터링)](#step1-레거시-코드-리팩터링)
    * [질문 삭제하기 기능 요구사항](#질문-삭제하기-기능-요구사항)
    * [리팩터링 요구사항](#리팩터링-요구사항)

<!-- TOC -->

## 진행 방법

* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정

* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---

## Step1 (레거시 코드 리팩터링)

### 질문 삭제하기 기능 요구사항

- 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
- 로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.
- 답변이 없는 경우 삭제가 가능하다.
- 질문자와 답변글의 모든 답변자가 같은 경우는 삭제가 가능하다.
- 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
- 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
- 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

### 리팩터링 요구사항

> - **`Service`에 비즈니스 로직을 넣는 순간 테스트 하기가 어려워진다.**
    <br/> (`Service`에 있는 `비즈니스 로직`을 `Domain`으로 옮기는게 `제일 먼저 할 일`이다.)

> - `nextstep.qna.service.QnaService`의 `deleteQuestion()`는 앞의 질문 삭제 기능을 구현한 코드이다.
    <br/> (이 메소드는 `단위 테스트하기 어려운 코드`와 `단위 테스트 가능한 코드`가 섞여 있다.)
> - `QnaService`의 `deleteQuestion()` 메서드에 대한 단위 테스트는 `src/test/java` 폴더 `nextstep.qna.service.QnaServiceTest`이다.
    <br/> (`도메인 모델`로 로직을 이동한 후에도 `QnaServiceTest`의 모든 테스트는 `통과`해야 한다.)

- `QnaService`의 `deleteQuestion()` 메서드에 `단위 테스트 가능한 코드(핵심 비지니스 로직)`를 `도메인 모델 객체`에 구현한다.
  - `QnaService`의 `비지니스 로직`을 `도메인 모델`로 이동하는 리팩터링을 진행할 때 `TDD`로 구현한다.
