package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.JdbcCandidateRepository;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcStudentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@JdbcTest
class CourseServiceTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseService courseService;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(
                new JdbcCourseRepository(jdbcTemplate),
                new JdbcSessionRepository(jdbcTemplate),
                new JdbcStudentRepository(jdbcTemplate),
                new JdbcCandidateRepository(jdbcTemplate)
        );
    }

    @DisplayName("Course 초기화 테스트 : Course에 포함된 Session들을 DB로부터 조회하여 객체를 초기화한다.")
    @Test
    void findTest() {
        Course course = courseService.findCourseById(0L);
        assertThat(course.getTitle()).isEqualTo("넥스트스텝");
        assertThat(course.findSession(0L).getTitle()).isEqualTo("TDD 클린코드");
    }

    @DisplayName("사용자 수강신청 테스트 - 이미 수강신청한 사용자는 중복 수강신청할 수 없다.")
    @Test
    void register_중복수강신청() {
        assertThatThrownBy(() -> courseService.registerSession(0L, NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("수강신청 승인 테스트 - 수강신청한 사용자가 아닌 경우")
    @Test
    void approve_미수강_사용자() {
        assertThatThrownBy(() -> courseService.approveSessionRegister(0L, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("수강신청 승인 테스트 - 선발된 사용자가 아닌 경우")
    @Test
    void approve_미선발_사용자() {
        courseService.registerSession(0L, NsUserTest.SANJIGI);
        assertThatThrownBy(() -> courseService.approveSessionRegister(0L, NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }
}