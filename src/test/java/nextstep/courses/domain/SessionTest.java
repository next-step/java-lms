package nextstep.courses.domain;

import nextstep.courses.SessionCostTypeException;
import nextstep.courses.SessionStateNotOnException;
import nextstep.courses.StudentMaxException;
import nextstep.users.domain.StudentTest;
import nextstep.users.domain.StudentsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    public static Session s1 = Session.ofFreeSession("lotto1", "image1", 1, Cost.FREE, State.READY, 30);
    public static Session s2 = Session.ofFreeSession("lotto2", "image2", 2, Cost.FREE, State.ON, 1);
    public static Session s3 = Session.ofFreeSession("lotto3", "image3", 3, Cost.FREE, State.OFF, 30);
    public static Session s4 = Session.ofPaySession("lotto4", "image4", 4, Cost.PAY, State.OFF, 30);

    @Test
    @DisplayName("무료 강의")
    void freeSession() {
        assertThat(s1).isInstanceOf(FreeSession.class);
    }

    @Test
    @DisplayName("유료 강의")
    void paySession() {
        assertThat(s4).isInstanceOf(PaySession.class);
    }

    @Test
    @DisplayName("무료 강의인데 유료로 잘못 등록하는 생성한 경우")
    void freeSession_exception() {
        assertThatThrownBy(() -> {
            Session.ofFreeSession("lms1_free", "image", 1, Cost.PAY, State.OFF, 30);
        }).isInstanceOf(SessionCostTypeException.class).hasMessageContaining("무료 강의가 아닙니다.");
    }

    @Test
    @DisplayName("유료 강의인데 무료로 잘못 등록하는 생성한 경우")
    void paySession_exception() {
        assertThatThrownBy(() -> {
            Session.ofPaySession("lms1_pay", "image", 1, Cost.FREE, State.OFF, 30);
        }).isInstanceOf(SessionCostTypeException.class).hasMessageContaining("유료 강의가 아닙니다.");
    }

    @Test
    @DisplayName("학생 등록")
    void addStudent() {
        Session session = Session.ofFreeSession("lotto", "image", 1, Cost.FREE, State.ON, 30);
        assertThat(session.enroll(StudentTest.student1)).isEqualTo(StudentsTest.students);
    }

    @Test
    @DisplayName("학생 정원 초과")
    void addStudent_maxUserException() {
        s2.enroll(StudentTest.student2);
        assertThatThrownBy(() -> {
            s2.enroll(StudentTest.student1);
        }).isInstanceOf(StudentMaxException.class).hasMessageContaining("정원 초과하여 신청할 수 없습니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 준비중")
    void addStudent_stateOnException() {
        assertThatThrownBy(() -> {
            s1.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotOnException.class).hasMessageContaining("준비 중인 강의입니다.");
    }

    @Test
    @DisplayName("모집중이 아닌 경우 - 종료")
    void addStudent_stateOffException() {
        assertThatThrownBy(() -> {
            s3.enroll(StudentTest.student1);
        }).isInstanceOf(SessionStateNotOnException.class).hasMessageContaining("모집 종료된 강의입니다.");
    }
}
