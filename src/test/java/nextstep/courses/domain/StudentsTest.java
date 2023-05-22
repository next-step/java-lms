package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class StudentsTest {
    @Test
    void 생성() {
        new Students(2);

        assertThatIllegalArgumentException().isThrownBy(()
                -> new Students(1, Arrays.asList(
                        new Student(1L, 1L),
                        new Student(2L, 1L))));
    }

    @Test
    void 수강신청_성공() throws Exception {
        Students dut = new Students(2);
        dut.enroll(new Student(1L, 1L));
        assertThat(dut.sizeOfStudents()).isEqualTo(1);
    }

    @Test
    void 수강신청_인원_초과() throws Exception {
        Students dut = new Students(1);
        dut.enroll(new Student(1L, 1L));
        assertThatIllegalArgumentException().isThrownBy(
                () -> dut.enroll(new Student(2L, 1L)));
    }

    @Test
    void 수강신청_이미_수강신청한_학생() throws Exception {
        Students dut = new Students(2);
        dut.enroll(new Student(1L, 1L));
                assertThatThrownBy(() -> dut.enroll(new Student(1L, 1L)))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }
}
