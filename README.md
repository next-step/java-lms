# 학습 관리 시스템(Learning Management System)

## LMS 기능목록

### Parsor
- [x] 파일명 포맷(파일명.확장자)이 아닐경우 예외 발생
- [x] 문자열을 마침표(.)로 분리해 파일명과 확장자 리턴

### CoverImage
- [x] 파일 확장자가 지원하지 않는 확장자라면 예외 발생
- [x] 파일용량이 최대 크기보다 크다면 예외 발생
- [x] 파일 너비가 최소 너비보다 작다면 예외발생
- [x] 파일 높이가 최소 높이보다 작다면 예외발생
- [x] 파일 비율이 지원하지 않는 비율이라면 예외발생

### PaidSession
- [x] 강의 최대 수강 인원을 초과할 수 없다.
- [ ] 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
---
## Q&A 기능목록
- [x] 본인이 질문한 질문이 아닌데 삭제하려고 할 경우 CannotDeleteException 발생
- [x] 질문에 답변자가 다른사람이 한명이라도 있다면 CannotDeleteException 발생
- [x] 답변의 작성자가 아닌데 삭제하려고 할 경우 CannotDeleteException 발생
- [x] 질문을 삭제하면 List<DeleteHistory>를 리턴한다.
- [x] 답변을 삭제하면 DeleteHistory를 리턴한다.
- [x] Answers 객체에 Answer를 추가한다.
- [x] 답변 작성자가 모두 일치한다면 true, 아니면 false리턴.
- [x] 모든 답변을 삭제하면 List<DeleteHistory>를 리턴한다.
