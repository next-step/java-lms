step4 변경 요구사항

1. 강의 상태에 대해서 2가지로 분기한다  
- as-is : SessionStatus(Ready / Recruiting / End)  
- to-be
  - SessionProgressStatus(Ready / Progress / End)
  - SessionRecruitingStatus(Nothing / Recruiting)

2. 수강 승인 기능
- Student에 StudentStatus를 추가한다 (Waiting / Accepted / Rejected)
- 강사가 수강 승인을 하면 ApprovalStatus가 Accepted로 변경된다
- 강사가 수강 승인을 취소하면 ApprovalStatus가 Rejected로 변경된다

3. 수강 신청 로직 변경
- capacity에 대해서 count할 때, StudentStatus가 Rejected라면 count하지 않도록 기능을 변경한다

4. 리팩토링
- SessionUser 테이블 이름을 Student 테이블로 변경한다