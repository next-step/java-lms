# [수강신청] README.md

## # Step1 요구사항

* nextstep.qna.service.QnaService의 deleteQuestion()는 앞의 질문 삭제 기능을 구현한 코드이다.
* 이 메소드는 단위 테스트하기 어려운 코드와 단위 테스트 가능한 코드가 섞여 있다.

- [x] QnaService의 deleteQuestion() 메서드에 단위 테스트 가능한 코드(핵심 비지니스 로직)를 도메인 모델 객체에 구현한다.
- [x] QnaService의 비지니스 로직을 도메인 모델로 이동하는 리팩터링을 진행할 때 TDD로 구현한다.
- [x] QnaService의 deleteQuestion() 메서드에 대한 단위 테스트는 src/test/java 폴더 nextstep.qna.service.QnaServiceTest이다.
- [x]도메인 모델로 로직을 이동한 후에도 QnaServiceTest의 모든 테스트는 통과해야 한다.

## # Step1 피드백

- [x] `gradle.properties` 의 "org.gradle.java.home" 문의
- [x] "answers" 를 관리하는 일급컬렉션 추가
- [x] 테스트 이름 구체적으로 명시
