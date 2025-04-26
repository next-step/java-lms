package nextstep.courses.domain;

import nextstep.courses.domain.model.Applicant;
import nextstep.courses.domain.model.ApplicantStatus;
import nextstep.courses.domain.model.RegistrationStatus;
import nextstep.courses.domain.model.Session;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

class ApplicantTest {

    public static Applicant createStudent(Long balance) {
        Session session = SessionTest.createFreeSession(RegistrationStatus.OPEN);
        return new Applicant(NsUserTest.createNsUser(3L, balance), session.getId(), null);
    }

    public static Applicant createStudent(Long id, ApplicantStatus status) {
        return new Applicant(id, SessionTest.createFreeSession(RegistrationStatus.OPEN).getId(), NsUserTest.createNsUser(0L), null, status, null, null);
    }

    public static Applicant createStudent(Long id, NsUser user, ApplicantStatus status) {
        return new Applicant(id, SessionTest.createFreeSession(RegistrationStatus.OPEN).getId(), user, null, status, null, null);
    }


    @Test
    @DisplayName("학생은 수강료를 결제할 수 있다.")
    void pay() {
        Session session = SessionTest.SESSION1;
        NsUser user = NsUserTest.createNsUser(100L, session.getPrice());
        Payment payment = new Payment(session.getId(), user.getId(), session.getPrice());
        assertThatCode(() -> new Applicant(user, session.getId(), payment)).doesNotThrowAnyException();
    }

}
