package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StudentsTest {
    @Test
    void 수강신청_유무() throws Exception {
        Students dut = new Students();
        dut.enroll(new Student(1L, 1L));
        assertThat(dut.sizeOfStudents()).isEqualTo(1);
        assertThatThrownBy(() -> dut.enroll(new Student(1L, 1L)))
                .isInstanceOf(AlreadyEnrollmentException.class);
    }

    @Test
    void 수강신청마감_유무() throws Exception {
        Students dut = new Students();
        dut.enroll(new Student(1L, 1L));
        assertThat(dut.isFullCapacity(2)).isFalse();
        dut.enroll(new Student(2L, 1L));
        assertThat(dut.isFullCapacity(2)).isTrue();
    }
}
