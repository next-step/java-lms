package nextstep.courses.domain;

import nextstep.courses.domain.builder.EnrollmentBuilder;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Students;
import nextstep.courses.domain.fixture.SessionFixtures;
import nextstep.courses.exception.CannotRegisterException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {
    private static final SessionPeriod PERIOD = new SessionPeriod(
            LocalDate.of(2023, 5, 23),
            LocalDate.of(2023, 5, 30)
    );

    @Test
    public void 모집중이면_수강신청에_성공한다() throws Exception {
        Enrollment enrollment = new EnrollmentBuilder()
                .withStatus(SessionStatus.RECRUITING)
                .withStudents(new Students(10))
                .build();

        assertThatCode(() -> enrollment.enroll(SessionFixtures.STUDENT_1))
                .doesNotThrowAnyException();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, names = {"RECRUITING"}, mode = EnumSource.Mode.EXCLUDE)
    public void 현재_모집중인_강의가_아니면_예외가_난다(SessionStatus status) throws Exception {
        Enrollment enrollment = new EnrollmentBuilder()
                .withStatus(status)
                .withStudents(new Students(10))
                .build();

        assertThatThrownBy(() -> enrollment.enroll(SessionFixtures.STUDENT_1))
                .isInstanceOf(CannotRegisterException.class);
    }
}