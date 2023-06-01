package nextstep.users.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class NsStudentTest {

    NsStudent nsStudent;

    @BeforeEach
    void setUP() {
        nsStudent = new NsStudent(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        nsStudent.register(1L);
        nsStudent.register(2L);
    }

    @Test
    public void register() {
        assertThat(nsStudent.sessionEnrollment()).hasSize(2);
    }

    @Test
    public void approveToSession() {
        nsStudent.approveToSession(1L);
        Map<Long, StudentStatus> map = nsStudent.sessionEnrollment();
        assertThat(map.get(1L)).isEqualTo(StudentStatus.APPROVE);
    }

    @Test
    public void refusedToSession() {
        nsStudent.refusedToSession(2L);
        Map<Long, StudentStatus> map = nsStudent.sessionEnrollment();
        assertThat(map.get(2L)).isEqualTo(StudentStatus.REFUSE);
    }
}
