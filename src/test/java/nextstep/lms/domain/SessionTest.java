package nextstep.lms.domain;

import nextstep.lms.UnAuthorizedException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class SessionTest {

    protected static final Session JAVA_SESSION_1 = Session.of("자바 기초1", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));
    protected static final Session JAVA_SESSION_2 = Session.of("자바 기초2", CourseTest.COURSE_B, LmsUserTest.ADMIN_2, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));

    private Session testSession;

    @BeforeEach
    void setUp() {
        testSession = Session.of("테스트용 강의", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));
    }

    @AfterEach
    void tearDown() {
        testSession = null;
    }

    @Test
    void 무료강의추가() {
        Session newSession = Session.of("무료강의1", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));
        assertAll(
                () -> assertThat(newSession.isFree()).isTrue(),
                () -> assertThat(newSession.isMaxApplicantCount(100)).isTrue(),
                () -> assertThat(newSession.isCourse(CourseTest.COURSE_A)).isTrue(),
                () -> assertThat(newSession.isCreator(LmsUserTest.ADMIN_1)).isTrue()
        );
    }

    @Test
    void 일반회원이_강의추가_불가능() {
        assertThatThrownBy(
                () -> Session.of("파이썬 기초1", CourseTest.COURSE_A, LmsUserTest.USER_1, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15))
        ).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 과정생성자와_불일치시_생성불가능() {
        assertThatThrownBy(
                () -> Session.of("파이썬 기초2", CourseTest.COURSE_A, LmsUserTest.ADMIN_2, 0, 100, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15))
        ).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    void 모집중으로상태변경() {
        testSession.open(LmsUserTest.ADMIN_1);
        assertAll(
                () -> assertThat(testSession.isStatus(SessionStatus.OPEN)).isTrue(),
                () -> assertThat(testSession.updatedAtIsNull()).isFalse()
        );
    }

    @Test
    void 생성자가_아닌_유저가_상태변경시() {
        assertAll(
                () -> assertThatThrownBy(() -> testSession.open(LmsUserTest.USER_1)).isInstanceOf(UnAuthorizedException.class),
                () -> assertThatThrownBy(() -> testSession.open(LmsUserTest.ADMIN_2)).isInstanceOf(UnAuthorizedException.class)
        );
    }

    @Test
    void 수강신청_성공() {
        testSession.open(LmsUserTest.ADMIN_1);
        testSession.addApplicant(LmsUserTest.USER_1);
        assertThat(testSession.hasUser(LmsUserTest.USER_1)).isTrue();
    }

    @Test
    void 이미_신청한_회원() {
        testSession.open(LmsUserTest.ADMIN_1);
        testSession.addApplicant(LmsUserTest.USER_1);
        assertThatThrownBy(() -> testSession.addApplicant(LmsUserTest.USER_1)).isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 준비중_상태() {
        assertThatThrownBy(() -> testSession.addApplicant(LmsUserTest.USER_1)).isInstanceOf(IllegalStateException.class).hasMessage(SessionStatus.PREPARING.message());
    }

    @Test
    void 종료_상태() {
        testSession.close(LmsUserTest.ADMIN_1);
        assertThatThrownBy(() -> testSession.addApplicant(LmsUserTest.USER_1)).isInstanceOf(IllegalStateException.class).hasMessage(SessionStatus.CLOSED.message());
    }

    @Test
    void 모집_마감_상태() {
        Session testSession2 = Session.of("테스트용 강의2", CourseTest.COURSE_A, LmsUserTest.ADMIN_1, 0, 1, null, LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 15));
        testSession2.open(LmsUserTest.ADMIN_1);
        testSession2.addApplicant(LmsUserTest.USER_1);
        assertAll(
                () -> assertThat(testSession2.isStatus(SessionStatus.FULL)).isTrue(),
                () -> assertThatThrownBy(() -> testSession2.addApplicant(LmsUserTest.USER_2)).isInstanceOf(IllegalStateException.class).hasMessage(SessionStatus.FULL.message())
        );
    }
}
