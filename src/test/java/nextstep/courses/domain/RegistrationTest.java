package nextstep.courses.domain;

import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.ApplicantStatus;
import nextstep.courses.domain.model.Registration;
import nextstep.courses.domain.model.Session;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RegistrationTest {

    @Test
    @DisplayName("학생이 이미 등록되어 있으면 예외가 발생한다.")
    void createStudentsWithAlreadyEnrolled() {
        Registration registration = new Registration(2);
        Session session = SessionTest.createPaidSession(10_000L, 2);
        registration.apply(NsUserTest.JAVAJIGI, session.getId(), 0L);
        assertThrows(IllegalArgumentException.class, () -> registration.apply(NsUserTest.JAVAJIGI, session.getId(), 0L));
    }

    @Test
    @DisplayName("학생 수를 초과하면 예외가 발생한다.")
    void createStudentsWithExceedLimit() {
        Session session = SessionTest.createPaidSession(10_000L, 0);
        Registration registration = new Registration(0, Set.of(new Applicant(NsUserTest.JAVAJIGI, session.getId(), null)));
        assertThrows(IllegalArgumentException.class, () -> registration.select(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void createPaidSessionWithCorrectPrice() {
        Registration registration = new Registration(1);
        Session session = SessionTest.createPaidSession(800_000L, 1);
        assertThatCode(() -> registration.apply(NsUserTest.createNsUser(3L, 800_000L), session.getId(), 800_000L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않으면 수강 신청이 불가능하다.")
    void createPaidSessionWithNotEnoughPrice() {
        Registration registration = new Registration(1);
        Session session = SessionTest.createPaidSession(800_000L, 1);
        assertThrows(IllegalArgumentException.class, () -> registration.apply(NsUserTest.createNsUser(3L, 790_000L), session.getId(), 800_000L));
    }

    @Test
    @DisplayName("남은 수강 인원을 확인한다.")
    void createPaidSessionWithEnoughPrice() {
        Registration registration = new Registration(1);
        assertThat(registration.getRemain()).isEqualTo(1);

        Registration registration2 = new Registration(3,
                Set.of(
                        ApplicantTest.createStudent(100L, ApplicantStatus.APPLIED),
                        ApplicantTest.createStudent(101L, ApplicantStatus.SELECTED),
                        ApplicantTest.createStudent(102L, ApplicantStatus.APPROVED)
                ));
        assertThat(registration2.getRemain()).isEqualTo(1);
    }

    @Test
    @DisplayName("학생은 돈이 부족하면 수강료를 결제할 수 없다.")
    void payWithNotEnoughMoney() {
        Registration registration = new Registration(1);
        Session session = SessionTest.createPaidSession(CourseTest.createCourse(), 10_000L, 1);
        NsUser user = NsUserTest.JAVAJIGI;
        assertThrows(IllegalArgumentException.class, () -> registration.apply(user, session.getId(), 10_000L));
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.")
    void cancelStudentsWithSelectionProcess() {
        Applicant student1 = ApplicantTest.createStudent(100L, NsUserTest.JAVAJIGI, ApplicantStatus.APPLIED);
        Applicant student2 = ApplicantTest.createStudent(101L, NsUserTest.SANJIGI, ApplicantStatus.SELECTED);
        Registration registration = new Registration(1, Set.of(student1, student2));

        assertThatThrownBy(() -> registration.cancel(NsUserTest.SANJIGI)).isInstanceOf(IllegalArgumentException.class);
        assertThatCode(() -> registration.cancel(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(registration.getApplicantStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.CANCELLED);
    }

    @Test
    @DisplayName("강의는 선발 절차에 따라 최대 수강 인원을 선발한다.")
    void selectStudentsWithCapacity() {
        Applicant student1 = ApplicantTest.createStudent(100L, NsUserTest.JAVAJIGI, ApplicantStatus.APPLIED);
        Applicant student2 = ApplicantTest.createStudent(101L, NsUserTest.SANJIGI, ApplicantStatus.APPLIED);
        Registration registration = new Registration(2, Set.of(student1, student2));

        assertThat(registration.select(() -> true)).isEqualTo(2);
    }

    @Test
    @DisplayName("강사는 선발된 인원에 대해서만 수강 승인이 가능해야 한다.")
    void selectStudentsWithSelectionProcess() {
        Applicant student1 = ApplicantTest.createStudent(100L, NsUserTest.JAVAJIGI, ApplicantStatus.SELECTED);
        Applicant student2 = ApplicantTest.createStudent(101L, NsUserTest.SANJIGI, ApplicantStatus.APPLIED);
        Registration registration = new Registration(2, Set.of(student1, student2));

        assertThatCode(() -> registration.approve(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
        assertThat(registration.getApplicantStatus(NsUserTest.JAVAJIGI)).isEqualTo(ApplicantStatus.APPROVED);
        assertThatThrownBy(() -> registration.approve(NsUserTest.SANJIGI)).isInstanceOf(IllegalArgumentException.class);
    }

}
