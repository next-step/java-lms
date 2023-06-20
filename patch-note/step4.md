step4 변경 요구사항

1. 강의 상태에 대해서 2가지로 분기한다  
- as-is : SessionStatus(Ready / Recruiting / End)  
- to-be
  - SessionProgressStatus(Ready / Progress / End)
  - RecruitingStatus(Nothing / Recruiting)

2. 수강 승인 기능
- 사용자의 어떠한 선발된 그룹인지 상태를 추가한다 NsUserGroup (Wootecho / Wootecampro)
- SessionUser에 상태를 추가한다 StudentStatus (Waiting / Accepted / Rejected)
- 수강 신청한 사용자가 강사가 등록해 놓은 수강 상태와 같다면 승인 요청을 보낸다
  - 승인하면 StudentStatus 상태가 Accepted로 변경
- 승인 취소하면 StudentStatus가 Rejected로 변경된다

3. 수강 신청 로직 변경  
- capacity에 대해서 count할 때, StudentStatus가 Rejected라면 count하지 않도록 기능을 변경한다

4. 리팩토링
- SessionUser 테이블 이름을 Student 테이블로 변경한다