package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.EnrollmentConditionTest.JAVAJIGI_CONDITION;
import static nextstep.courses.domain.session.EnrollmentConditionTest.SANJIGI_CONDITION;
import static nextstep.courses.domain.session.SessionTest.SESSION_ID;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionEnrollmentTest {
    private static final Image IMAGE = new Image(1_024, 300, 200, "png");

    @Test
    void 강의는_모집중_상태가_아니면_신청할_수_없다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        SessionEnrollment enrollment = new SessionEnrollment(freeSession);
        assertThatThrownBy(() -> enrollment.enroll(JAVAJIGI_CONDITION))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 무료_강의는_인원수_제한없이_들을_수_있다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        freeSession.openRecruiting();
        SessionEnrollment enrollment = new SessionEnrollment(freeSession);
        enrollment.enroll(JAVAJIGI_CONDITION);
        enrollment.enroll(SANJIGI_CONDITION);
    }

    @Test
    void 유료_강의는_인원수_제한이_있다() {
        Session paidSession = Session.createPaidSession(SESSION_ID, "paid", IMAGE, 30_000L);
        paidSession.openRecruiting();
        SessionEnrollment enrollment = new SessionEnrollment(1, paidSession);
        enrollment.enroll(JAVAJIGI_CONDITION);
        assertThatThrownBy(() -> enrollment.enroll(SANJIGI_CONDITION))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 유료_강의는_지불한_금액이_맞아야_한다() {
        Session paidSession = Session.createPaidSession(SESSION_ID, "paid", IMAGE, 20_000L);
        paidSession.openRecruiting();
        SessionEnrollment enrollment = new SessionEnrollment(1, paidSession);
        assertThatThrownBy(() -> enrollment.enroll(JAVAJIGI_CONDITION))
                .isInstanceOf(SessionUnregistrableException.class);
    }
}