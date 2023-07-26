step4 변경 요구사항

1. 강의 상태에 대해서 2가지로 분기한다  
- as-is : SessionStatus(Ready / Recruiting / End)  
- to-be
  - SessionProgressStatus(Ready / Progress / End)
  - SessionRecruitingStatus(Nothing / Recruiting)

2. 수강 승인 기능 (x)
- 사용자의 어떠한 선발된 그룹인지 상태를 추가한다 NsUserGroup (WTC / WCP)
- SessionUser에 상태를 추가한다 StudentStatus (Waiting / Accepted / Rejected)
- 수강 신청한 사용자의 그룹중 강사가 등록해 놓은 수강 상태와 같은 것이 존재한다면 승인 요청을 보낸다
  - 승인하면 StudentStatus 상태가 Accepted로 변경
- 승인 취소하면 StudentStatus가 Rejected로 변경된다

2. 수강 승인 기능
- Student에 ApprovalStatus를 추가한다 (Waiting / Accepted / Rejected)
- 강사가 수강 승인을 하면 ApprovalStatus가 Accepted로 변경된다
- 강사가 수강 승인을 취소하면 ApprovalStatus가 Rejected로 변경된다
- 우선 해야할 일
  - [ ] NsUserGroup 관련 로직 삭제
  - [ ] StudentStatus 관련 로직 삭제

3. 수강 신청 로직 변경
- capacity에 대해서 count할 때, StudentStatus가 Rejected라면 count하지 않도록 기능을 변경한다

4. 리팩토링
- SessionUser 테이블 이름을 Student 테이블로 변경한다