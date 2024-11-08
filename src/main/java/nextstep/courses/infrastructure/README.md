## 3단계 1차 리뷰 사항

### EnrollmentRepository
- [X] enrollUser -> save 로 변경

### FreeSession
- [ ] getFee 메서드 리턴값 0 고정

### PaidSession
- [ ] validaPaymentAmount public -> private 으로 변경
- [ ] isPaymentMismatched 에서 payment 객체의 getAmount 대신 메시지 보내기 

### Session
- [ ] 접근제한자와 abstract 위치, 컨벤션 확인

### SessionBody
- [ ] courseId 필드 Session 클래스로 이동
- [ ] final 키워드 제거 이유

### JdbcSessionRepository
- [ ] 무료/유료 등 구현체 결정 책임 도메인으로 위임

### enrollment 테이블
- [ ] 중복 신청 데이터가 존재할 수 있는 구조. unique 제약 추가. repository 중복 처리 로직 제거 가능 

### cover_image 테이블 
- [ ] 1개의 강의는 1개의 이미지를 가지는 1:1 관계이므로 테이블 분리 불필요

### SessionEnrollmentTest
- [ ] 매직넘버 2, 3에 따라 메서드의 결과가 다르니 변수로 추출해서 의도를 드러냄

### EnrollmentRepositoryTest
- [ ] 테스트의 의도대로 size 검증이 아닌 NsUser 값들을 검증