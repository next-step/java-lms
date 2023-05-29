package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class StudentsTest {
    @Test
    void 생성() {
        new Students(2);

        assertThatIllegalArgumentException().isThrownBy(()
                -> new Students(1, Arrays.asList(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI)));
    }

    @Test
    void 수강신청_성공() throws Exception {
        Students dut = new Students(2);
        dut.enroll(NsUserTest.JAVAJIGI);
        assertThat(dut.sizeOfStudents()).isEqualTo(1);
    }

    @Test
    void 수강신청_인원_초과() throws Exception {
        Students dut = new Students(1);
        dut.enroll(NsUserTest.JAVAJIGI);
        assertThatIllegalArgumentException().isThrownBy(
                () -> dut.enroll(NsUserTest.SANJIGI));
    }

    @Test
    void 수강신청_이미_수강신청한_학생() throws Exception {
        Students dut = new Students(2);
        dut.enroll(NsUserTest.JAVAJIGI);
                assertThatThrownBy(() -> dut.enroll(NsUserTest.JAVAJIGI))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }
}
