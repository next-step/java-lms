# 수강 신청 기능

## 2. 테이블 설계
- [ ] 객체 구조(도메인 구조)를 가능한 유지하면서 DB 테이블과 매핑한다.
- [ ] 성능보다 도메인 로직 구현에 집중한다.

### 2.1 session_image 테이블
- 컬럼: id, file_size, image_type, width, height

### 2.2 session 테이블
- 컬럼: id, course_id, cohort, start_date, end_date, image_id, status, session_type, max_capacity, fee, created_at, updated_at
- Course와 1:N
- SessionImage와 N:1

### 2.3 session_enrollment 테이블
- 컬럼: id, session_id, ns_user_id, enrolled_at
- Session과 1:N
- NsUser와 N:1
