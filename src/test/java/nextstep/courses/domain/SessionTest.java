package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    private Students students;

    @BeforeEach
    void setUp() {
        this.students = new Students(Arrays.asList(
                new Student(1L, 1L),
                new Student(2L, 1L),
                new Student(3L, 1L)));
    }

    @Test
    void 수강신청_성공() throws Exception {
        NsUser loginUser = NsUsers.createNsUser(4L);
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, 49, SessionStatus.ENROLLING);
        Student student = dut.enroll(loginUser, students);
        assertThat(student).isEqualTo(new Student(loginUser.getId(), dut.getId()));
    }

    @Test
    void 수강신청_실패_이미_수강신청한_회원() {
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, 50, SessionStatus.ENROLLING);
        assertThatThrownBy(() -> dut.enroll(NsUsers.createNsUser(3L), students))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }

    @Test
    void 수강신청_실패_수강정원_마감() {
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, 50, SessionStatus.ENROLLING);
        assertThatIllegalArgumentException().isThrownBy(() -> dut.enroll(NsUsers.createNsUser(4L), students));
    }

    @Test
    void 수강신청_실패_모집중_아님() {
        Session dut = Sessions.createSessionWithEnrollment(1L, 50, 49, SessionStatus.PREPARING);
        assertThatIllegalArgumentException().isThrownBy(() -> dut.enroll(NsUsers.createNsUser(4L), students));
    }
}
