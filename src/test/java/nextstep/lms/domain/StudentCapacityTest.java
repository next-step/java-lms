package nextstep.lms.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class StudentCapacityTest {

    @Test
    @DisplayName("강사 최대 수강인원 설정 테스트")
    void initStudentCapacityTest() {
        StudentCapacity studentCapacity = StudentCapacity.init(5);

        assertThat(studentCapacity.getStudentCapacity())
                .isEqualTo(5);

        assertThat(studentCapacity.getRegisteredStudent())
                .isEqualTo(0);
    }

    @Test
    @DisplayName("수강신청인원 등록 테스트")
    void enrollStudentTest() {
        StudentCapacity studentCapacity = StudentCapacity.init(2);

        assertThat(studentCapacity.enroll())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("수강신청인원이 초과되었을 때 에러 테스트")
    void enrollOverCapacityTest() {
        StudentCapacity studentCapacity = StudentCapacity.init(2);
        studentCapacity.enroll();
        studentCapacity.enroll();

        assertThatThrownBy(studentCapacity::enroll)
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청 취소 테스트")
    void cancelTest() {
        StudentCapacity studentCapacity = StudentCapacity.init(2);
        studentCapacity.enroll();
        studentCapacity.enroll();

        studentCapacity.cancel();

        assertThat(studentCapacity.getRegisteredStudent())
                .isEqualTo(1);
    }

}
