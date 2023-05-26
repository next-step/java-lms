# 학습 관리 시스템(Learning Management System)

## [1단계] - 레거시 코드 리팩터링
1. 질문 삭제하기 요구사항
   1. 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
   2. 삭제 가능한 경우
      - 로그인 사용자와 질문한 사람이 같은 경우
      - 답변이 없는 경우
      - 질문자와 답변 글의 모든 답변자가 같은 경우
   3. 질문을 삭제할 때 답변도 삭제해야 하며, 답변 삭제도 삭제 상태 변경으로 진행
   4. 질문자와 답변자가 다른 경우 답변 삭제 불가
   5. 질문, 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.

2. 리팩터링 요구사항
   1. QnAService의 deleteQuestion 함수에 '단위 테스트 가능 코드'를 도메인 모델 객체에 구현
      - 해당 리팩터링 진행 시 TDD로 구현
      - 도메인 모델로 로직이 이동한 후에도 모든 테스트가 통과하여야 한다.

3. 힌트
   1. 객체 상태 데이터를 꺼내지(get) 말고 메시지를 보낼 것
   2. Question list를 일급 컬렉션으로 구현
   3. 3개 이상의 인스턴스 변수를 가진 클래스를 사용하지 않는다.
   4. 도메인 모델에 setter 메서드를 추가하지 않는다.

4. 리팩터링 진행
   1. questionRepository에서 가져오는 부분을 함수화
   2. '질문 삭제 권한' 관련 함수화
   3. 질문 delete 처리 및 history 추가
   4. 'question'에 해당하는 answer들을 가져와서 delete 처리
      1. deleteHistoryService에 add 요청
      2. Service 계층에서는 domain 객체에게 실질적 로직 실행 요청
   5. deleteHistoryService에 일급 컬렉션 (DeleteHistories) 필드 추가