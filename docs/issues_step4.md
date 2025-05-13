# 4단계 - 수강신청(요구사항 변경)
## 3단계 피드백
### DTO 사용
- DTO를 사용하지 않는다고 하더라도 도메인 내부의 값을 알기 위한 getter는 필수불가결
- 그러나 현재 요구사항에서 Session 전체 값을 update할 필요는 없음
- SessionDto를 Session으로 변환하는 과정에서 다른 Repository에 의존하는 것에는 문제가 있음
  - UserService에서 List<NsUser> 반환
  - imageRepository 의존성 제거 → imageRepository에서 SessionThumbnail 반환
  - sessionEnrollmentRepository 의존성 제거 -> 단순 CRUD 기능의 계층을 늘릴 필요는 없음
- Service 간 의존성을 반드시 제거할 필요는 없음 (단, 순환 참조는 발생하면 안됨)
### Repository 
- 도메인 객체에 ID는 필요 없는 값
- 하지만 이것을 분리하여 얻는 장점은 크지 않음
  - 참고: [https://mincanit.tistory.com/74](https://mincanit.tistory.com/74)
## 수강 신청 변경
### 수강 신청 상태 추가
- 기존 EnrollmentManager에서는 List<NsUser>를 사용하여 수강신청을 관리
  - 수강 신청 상태 - 사용자를 묶는 객체 추가
    - EnrollmentManager는 수강 신청 상태와 관계 없이 List<NsUser> 도 필요
    - List<NsUser> 대신 Map<NsUser, EnrollmentStatus>를 갖도록 변경하자
    - 이러면 별도의 Enrollment 객체는 필요하지 않음
## 피드백
### Enrollments - Session 관계
- Enrollments는 Session에 의존
- 그러나 Enrollments가 Session의 필드일 필요성이 있을까?
- 