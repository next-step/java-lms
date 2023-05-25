## 학습 관리 시스템(Learning Management System)

---

### Step1. 레거시 코드 리팩터링
### 질문 삭제하기 요구사항
- [X] 질문 데이터 상태를 삭제 상태로 변경(deleted - boolean type)
- [X] 잘문 삭제 가능 조건 
  - [X] 로그인 사용자와 질문한 사람이 같은 경우
  - [X] 답변이 없는 경우
  - [X] 질문자와 답변글의 모든 답변자 같은 경우
- [X] 삭제 내용
  - [X] 질문을 삭제할 때 답변도 같이 삭제 처리
  - [X] 답변의 삭제 또한 삭제 상태(deleted)를 변경
- [X] 답변 삭제
  - [X] 질문자와 답변자가 다른 경우 답변 삭제 불가능
  - [X] 질문과 답변 삭제 이력 저장 : DeleteHistory
- [X] 예외 처리
  - [X] CannotDeleteException : RuntimeException 상속하도록 변경
  - [X] 비지니스 예외 상황 각 도메인에서 예외 처리 

### 리팩터링 요구사항
- QnaService deleteQuestion()는 질문 삭제 기능을 구현한 코드
- QnaService deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현
- QnaService 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD 구현
- QnaService deleteQuestion() 메서드에 대한 단위 테스트는 QnaServiceTest
- 도메인 모델로 로직을 이동한 후에도 QnaServiceTest 모든 테스트는 통과해야 함

---

### Step2. 수강신청(도메인 모델)
`도메인 모델 설계`
- [ ] 과정 - Course  `List<Lecture>`
  - 강의(Session) 와 1:N 관계 (기수별)
- [ ] 강의 - Lecture
  - [ ] 강의 수강 신청 기능
  - [ ] 학생은 강의를 신청할 수 있다
  - [ ] 강의는 최대 수강 인원을 초과 할 수 없다
  - [ ] 강의 상태가 모집중일때만 수강 신청 가능하다
- [X] 강의시간 - LectureDate
  - startDate - 시작일
  - endDate - 종료일
  - [ ] 종료일은 시작일보다 나중이여야 한다
- [ ] 커버 이미지 - LectureImage 
  - coverImage
- [X] 강의 구분 - PaymentStrategy (전략 패턴 적용)  
  - FreePaymentStrategy - 무료강의
  - PaidPaymentStrategy - 유료강의
- [ ] 강의 상태 - LectureStatus  
  - PREPARING - 준비중
  - ENROLLING - 모집중
  - FINISHED - 종료
- [ ] 수강자 - Student 
- [ ] 수강자들 일급 컬렉션 - Students `List<Student>`