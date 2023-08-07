# 학습 관리 시스템(Learning Management System)
## 진행 방법
* 학습 관리 시스템의 수강신청 요구사항을 파악한다.
* 요구사항에 대한 구현을 완료한 후 자신의 github 아이디에 해당하는 브랜치에 Pull Request(이하 PR)를 통해 코드 리뷰 요청을 한다.
* 코드 리뷰 피드백에 대한 개선 작업을 하고 다시 PUSH한다.
* 모든 피드백을 완료하면 다음 단계를 도전하고 앞의 과정을 반복한다.

## 온라인 코드 리뷰 과정
* [텍스트와 이미지로 살펴보는 온라인 코드 리뷰 과정](https://github.com/next-step/nextstep-docs/tree/master/codereview)

---
## 1단계 - 레거시 코드 리팩터링
### todo 리스트
1. delete 로직 분리 [x]
    - Question 및 Answer 객체에서 직접 delete/유효성 테스트 진행 [x]
    - Question은 Answer에게 삭제하라고 메시지 전달 [x]
2. Answer 일급 콜렉션 화 [x]
    - Answer 리스트를 가지고 있는 Answers 객체 추가 [x]
    - Answers에도 delete 기능을 추가하여 여기서 일괄 삭제 [x]
    - Answer를 추가할 수 있는 addAnswer 추가 [x]
3. 삭제 완료시 DeleteHistory 반환 [x]
    - 위에서 구현한 모든 delete 기능은 DeleteHistory를 반환 [x]
    - Answers와 Question은 DeleteHistory 리스트를 반환 [x]

### 요구사항
#### 질문 삭제하기 요구사항
1. 질문 데이터를 완전히 삭제하는 것이 아니라 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경한다.
2. 로그인 사용자와 질문한 사람이 같은 경우 삭제할 수 있다.
3. 답변이 없는 경우 삭제가 가능하다.
4. 질문자와 답 변들의 모든 답변자 같은 경우 삭제가 가능하다.
5. 질문을 삭제할 때 답변 또한 삭제해야 하며, 답변의 삭제 또한 삭제 상태(deleted)를 변경한다.
6. 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다.
7. 질문과 답변 삭제 이력에 대한 정보를 Delete History를 활용해 남긴다.

#### 리팩터링 요구사항
1. nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다. 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.
2. QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
3. QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
4. QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다. 도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

---

## 2단계 - 수강신청(도메인 모델)
### todo list

1. Course 객체는 Session 리스트 객체를 가지고 있음. [x]
   - Session 일급 콜렉션으로 구현 -> Sessions [x]
   
2. Sessions 객체 [x]
   - 강의 시작, 종료, 모집 등의 기능 진행 시 강의 아이디 필요. [x]

3. Session 객체 [x]
   - 강의 아이디(id), 강의 명(title) 필요 [x]
   - 강의 시작일(startDt)과 강의 종료일(endDt)를 가지고 있음. [x]
     - default는 null 이며 시작 또는 종료시 LocalDateTime.now()로 할당 [x]
   - 강의 커버 이미지를 가지고 있음. 이미지 객체 필요해 보임 (CoverImage) [x]
   - 유무료 여부(isFree)를 가지고 있음. [x]
   - 상태를 가지고 있음. SessionStatus 객체로 분리 [x]
     - 생성 시 준비 중 [x]
   - 수강 최대 인원 필요 (maxNumberOfStudent) [x]
   - 수강 중 인원 필요 (Students) -> User의 집합 [x]
   - 수강 신청 기능 (enrolment) [x]
     - User 객체 필요 [x]
     - 강의 상태가 모집중 인지 확인 [x]
     - 수강 신청 시 수강 최대 인원 체크 (Students와 협력) [x]
     - Students에 추가 [x]
   - 강의 시작, 종료, 모집 기능 필요 [x]
     - 모집은 모집중으로 상태 변경 [x]
     - 시작은 진행중으로 상태 변경 [x]
     - 종료는 종료로 상태 변경 [x]
     - 상태 변경 시 이전 상태에 대한 유효성 체크 추가 [x]
     
3. SessionStatus Enum 객체 [x]
   - 강의 상태를 가지고 있음
   - 준비중(PREPARING), 모집중(RECRUITING), 진행중(PROCEEDING), 종료(END)
   - 진행중은 요구사항에는 없으나 필요해 보임.

4. CoverImage 객체 -> Session 생성 시 필요 [x]
   - Image size, path 등의 정보 필요

5. Students 객체 [x]
   - Session에서 강의를 듣고 있는 User 객체들 [x]
   - 최대 수강인원을 받아 최대 수강인원을 넘었거나 같은지 확인하는 기능 필요 [x]

### 요구 사항
#### 수강 신청 기능 요구사항
1. 과정(Course)은 기수 단위로 여러 개의 강의(Session)를 가질 수 있다.
2. 강의는 시작일과 종료일을 가진다.
3. 강의는 강의 커버 이미지 정보를 가진다.
4. 강의는 무료 강의와 유료 강의로 나뉜다.
5. 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.
6. 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.
7. 강의는 강의 최대 수강 인원을 초과할 수 없다.

#### 프로그래밍 요구사항
1. DB 테이블 설계 없이 도메인 모델부터 구현한다.
2. 도메인 모델은 TDD로 구현한다.
   - 단, Service 클래스는 단위 테스트가 없어도 된다.
3. 다음 동영상을 참고해 DB 테이블보다 도메인 모델을 먼저 설계하고 구현한다.