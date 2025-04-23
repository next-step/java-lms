package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class EnrollmentTest {

    private Session session = Session.createFreeSession(LocalDate.now(), LocalDate.now().plusDays(1));
    private Student student = new Student(1L);

    @Test
    public void 이미_승인된_수강신청_건은_반려시도_시_예외가_발생한다() {
        Enrollment enrollment = Enrollment.request(session, student);
        enrollment.approve();

        assertThatIllegalStateException().isThrownBy(() -> enrollment.reject());
    }

    @Test
    public void 이미_반려된_수강신청_건은_승인시도_시_예외가_발생한다() {
        Enrollment enrollment = Enrollment.request(session, student);
        enrollment.reject();

        assertThatIllegalStateException().isThrownBy(() -> enrollment.approve());
    }

}
