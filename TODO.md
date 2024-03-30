### 1단계

- [x] 질문 작성자와 로그인 유저를 비교한다
    - 작성자가 아닌 경우 CannotDeleteException 던진다
- [x] 답변 작성자와 로그인 유저를 비교한다
    - 작성자가 아닌 경우 CannotDeleteException 던진다
- [x] 질문 작성자와 일치하고 답변이 없는 경우 삭제 가능하다
- [x] 질문자와 답변 글의 모든 답변자가 같은 경우 삭제가 가능하다
- [x] 질문자와 답변자가 다른 경우 답변을 삭제할 수 없다
    - 작성자가 다른 경우 CannotDeleteException 던진다
- [x] Answers 일급 컬렉션 추가
    - add(..) , get(), toDeleteHistory() 생성
