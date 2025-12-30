# Step1 리팩토링

## QnaService
- question이 삭제 비즈니스 로직을 위임
- question의 delete로 반환된 deleteHistories를 deleteHistroyService에 전달

## Question
- delete 메소드 구현
  - question의 소유자 여부 확인
  - List<Answer> -> Answers로 변경
- deleted 상태 변경

## Answer
- Answers 구현
- delete 메소드 구현
  - 소유자 여부 확인
- deleted 상태 변경

## Content
- Question과 Answer의 공통 부분 추출


## 힌트
- 객체의 상태 데이터를 꺼내지(get)말고 메시지를 보낸다.
- 규칙 8: 일급 콜렉션을 쓴다.
  - Question의 List를 일급 콜렉션으로 구현해 본다.
- 규칙 7: 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
  - 인스턴스 변수의 수를 줄이기 위해 도전한다.
- 도메인 모델에 setter 메서드 추가하지 않는다.