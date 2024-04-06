# Step1

## Todo
- [ ] 코드 리뷰 반영 - 생성자들 중에서 해당 클래스 내부에서만 사용되는 생성자들 접근 지정자를 private으로 지정
- [ ] 코드 리뷰 반영 - 이름 변경 saveDeletedHistory -> writeDeletedHistory
- [ ] 코드 리뷰 반영 - CannotDeleteException RuntimeException으로 변경

## Done
- [X] 질문한 사람이 로그인한 본인이 아닐 경우에 삭제가 불가능
- [X] 답변이 있으며 질문자와 답변글이 다른 경우 삭제가 불가능
- [X] 질문자가 본인이며 답변이 없으면 삭제가 가능
- [X] 질문 삭제 시에 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
- [X] 질문자가 로그인한 본인일 경우에 삭제가 가능
- [X] 답변이 있으며 질문자와 답변글이 모두 로그인한 사용자이면 삭제가 가능
- [X] 답변 삭제 시에 데이터의 상태를 삭제 상태(deleted - boolean type)로 변경
- [X] 질문과 답변 삭제 이력에 대한 정보를 DeleteHistory를 활용해 남긴다.
