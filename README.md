# 학습 관리 시스템(Learning Management System)

## 기능목록
- [x] 본인이 질문한 질문이 아닌데 삭제하려고 할 경우 CannotDeleteException 발생
- [x] 질문에 답변자가 다른사람이 한명이라도 있다면 CannotDeleteException 발생
- [x] 답변의 작성자가 아닌데 삭제하려고 할 경우 CannotDeleteException 발생
- [x] 질문을 삭제하면 List<DeleteHistory>를 리턴한다.
- [x] 답변을 삭제하면 DeleteHistory를 리턴한다.
- [x] Answers 객체에 Answer를 추가한다.
- [x] 답변 작성자가 모두 일치한다면 true, 아니면 false리턴.
- [x] DeleteHistories에 질문 삭제 이력을 추가한다.
- [ ] DeleteHistories에 답변 삭제 이력을 추가한다.
