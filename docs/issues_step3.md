# 3단계 - 수강신청(DB 적용)
## DB 매핑
- 테이블과 객체가 1:1 관계가 아님
  - 테이블과 같은 정보를 가진 DTO 객체를 생성 후 각 객체에 매핑
### Session 조회
- session 테이블
- session_enrollment 테이블
- image 테이블
### Session 리팩토링
- 기존 Session 생성자가 과도하게 많은 파라미터를 사용
  - 원시값을 파라미터로 받을 경우 자식 객체의 구현을 알아야 함
  - Session 객체는 자식 객체를 생성자로 받고, DTO를 통하여 자식 객체를 생성
### SessionPrice - Enrollment 역할 중복
- 유료 강의/무료 강의를 구분하는 Enrollment와 유료/무료 여부를 갖고 있는 SessionPrice의 역할 중복
### Enrollment 리팩토링
- 인터페이스로 구현했더니 getter를 구현하기 애매해짐
- 인터페이스는 상태값을 갖고 있지 않기 때문에 getter를 구현할 수 없음
- EnrollmentImpl을 공통 구현체로 만들고 컴포지션을 사용