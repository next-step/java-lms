package nextstep.courses.domain.vo;

import nextstep.courses.code.EnrollStatus;
import nextstep.courses.exception.AlreadyEnrolledException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {

    public static Enrollment ENROLL_1 = new Enrollment(EnrollStatus.OPEN, StudentsTest.STUDENT_JAVAJIGI1, 1);
    public static Enrollment ENROLL_10 = new Enrollment(EnrollStatus.OPEN, StudentsTest.STUDENT_JAVAJIGI1, 10);
    public static Enrollment ENROLL_0 = new Enrollment(EnrollStatus.OPEN, StudentsTest.STUDENT_JAVAJIGI2, 0);

    @Test
    @DisplayName("모집 테스트")
    void enrollmentTest() {
        final Enrollment enrollment = new Enrollment(100);

        assertThat(enrollment.capacity()).isEqualTo(100);
        assertThat(enrollment.status()).isEqualTo(EnrollStatus.READY);
    }

    @Test
    @DisplayName("수강 신청 테스트")
    void enrollTest() throws Exception{
        final Enrollment enrollment = new Enrollment(EnrollStatus.OPEN, new Students(), 2);

        enrollment.enroll(NsUserTest.JAVAJIGI);
        enrollment.enroll(NsUserTest.SANJIGI);

        assertThat(enrollment.students().count()).isEqualTo(2);
        assertThat(enrollment.status()).isEqualTo(EnrollStatus.CLOSED);
    }

    @Test
    @DisplayName("수강 신청 실패 테스트 - 이미 수강 신청한 학생")
    void enrollFailForAlreadyEnrolledTest() {
        assertThatThrownBy(() -> {
            ENROLL_10.enroll(NsUserTest.JAVAJIGI);
        }).isInstanceOf(AlreadyEnrolledException.class)
                .hasMessage("이미 수강신청한 학생입니다.");
    }

    @Test
    @DisplayName("수강 신청 실패 테스트 - 모집중이지 않은 상태")
    void enrollFailForNotOpenTest() {
        final Enrollment enrollment = new Enrollment(10);

        assertThatThrownBy(() -> {
            enrollment.enroll(NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의가 모집중인 상태가 아닙니다.");
    }

    @Test
    @DisplayName("수강 신청 실패 테스트 - 제한인원 초과")
    void enrollFailForFullTest() {
        assertThatThrownBy(() -> {
            ENROLL_1.enroll(NsUserTest.SANJIGI);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 제한인원이 초과 되었습니다.");
    }

    @Test
    @DisplayName("모집 시작 테스트")
    void openTest() {
        final Enrollment enrollment = new Enrollment(10);

        enrollment.open();

        assertThat(enrollment.status()).isEqualTo(EnrollStatus.OPEN);
    }

    @Test
    @DisplayName("모집 종료 테스트")
    void closeForFullTest() {
        final Enrollment enrollment = new Enrollment(2);

        enrollment.close();

        assertThat(enrollment.status()).isEqualTo(EnrollStatus.CLOSED);
    }
}
