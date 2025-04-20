package nextstep.courses.domain;

import nextstep.courses.domain.model.Session;
import nextstep.courses.domain.model.SessionStatus;
import nextstep.courses.domain.model.Student;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionTest {

    public static Session createFreeSession(SessionStatus status) {
        return Session.createFreeSession(CourseTest.createCourse(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, status, NsUserTest.JAVAJIGI);
    }

    public static Session createPaidSession(Long price, int capacity) {
        return Session.createPaidSession(CourseTest.createCourse(), LocalDateTime.now(), LocalDateTime.now().plusMonths(1), null, SessionStatus.OPEN, price, capacity, NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void createFreeSession() {
        assertThatCode(() -> createPaidSession(0L, Integer.MAX_VALUE).enroll(new Student(NsUserTest.JAVAJIGI))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때 가능하다.")
    void registerOpenSession() {
        assertThatCode(() -> createFreeSession(SessionStatus.OPEN).enroll(new Student(NsUserTest.JAVAJIGI))).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중이 아니면 불가능하다.")
    void registerNonOpenSession() {

        for (SessionStatus status : SessionStatus.values()) {

            if (status == SessionStatus.OPEN) continue;

            assertThatThrownBy(() -> createFreeSession(status).enroll(new Student(NsUserTest.JAVAJIGI)))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("session is not open");
        }
    }

    @Test
    @DisplayName("결제 정보는 Payment 객체에 담겨 반한된다.")
    void enrollAndGetPayment() {
        Session session = createPaidSession(800_000L, 1);
        Student student = StudentTest.createStudent(800_000L);
        assertThat(session.enroll(student).getAmount()).isEqualTo(800_000L);
    }

}
