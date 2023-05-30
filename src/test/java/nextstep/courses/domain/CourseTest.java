package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

@DisplayName("과정 객체 테스트")
class CourseTest {

    LocalDateTime createDate;
    NsUser JAVAJIGI;
    NsUser SANJIGI;
    NsUser WOOK;
    SessionPeriod sessionPeriod;
    String name;
    Image coverImage;
    Enrollment enrollment;

    @BeforeEach
    void init() {
        createDate = LocalDateTime.of(2023, 5, 27, 19, 30, 30);
        JAVAJIGI = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null);
        SANJIGI = new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null);
        WOOK = new NsUser(3L, "wook", "password", "name", "wook@gmail.com");
        sessionPeriod = new SessionPeriod(LocalDate.now(), LocalDate.now().plusDays(60));
        name = "수강신청(도메인모델)";
        coverImage = new Image("test.jpeg", "http://nextstep.com");
        enrollment = new Enrollment(50, new Students());
    }

    @DisplayName("과정에 세션을 추가 할 수 있다")
    @Test
    void addSessionInCourse() {
        Session session1 = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage, new FreePaymentStrategy(), enrollment);
        Session session2 = new Session(name, sessionPeriod, SessionStatus.PREPARING, coverImage, new FreePaymentStrategy(), enrollment);
        Course course = new Course("test course", 11, 1L);
        course.addSession(session1);
        course.addSession(session2);

        assertThat(course.sessionCount()).isEqualTo(2);
        assertThat(course.fetchSessions())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(session1, session2);
    }

    @DisplayName("과정을 등록 할 수 있다")
    @Test
    void registerCourse() {
        Course course = new Course("TDD Clean Code", 16, 1L);
        Session session = new Session(name, sessionPeriod, SessionStatus.ENROLLING, coverImage,  new FreePaymentStrategy(), enrollment);
        course.addSession(session);
        String registerMessage = course.registerCourse(SampleUser.WOOK);

        assertThat(registerMessage).isEqualTo("TDD Clean Code 16기 과정 등록에 성공하였습니다");
        assertThat(session.hasEnrolledStudent()).isTrue();
        assertThat(session.currentEnrolmentCount()).isEqualTo(1);
    }

    @ParameterizedTest(name = "과정을 생성할때 기수는 양수가 아니면 예외가 발생한다")
    @ValueSource(ints = {0, -9})
    void termException(int term) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Course("TDD Clean Code", term, 1L))
                .withMessage("the term is must be greater than zero");
    }
}
