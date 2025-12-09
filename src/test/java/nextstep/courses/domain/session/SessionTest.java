package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static nextstep.courses.domain.session.EnrollmentConditionTest.SESSION_ID;
import static nextstep.courses.domain.session.EnrollmentConditionTest.JAVAJIGI_ENROLLMENT;
import static nextstep.courses.domain.session.EnrollmentConditionTest.SANJIGI_ENROLLMENT;

class SessionTest {
    private static final Image IMAGE = new Image(1_024, 300, 200, "png");

    @Test
    void 강의는_모집중_상태가_아니면_신청할_수_없다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        assertThatThrownBy(() -> freeSession.enroll(JAVAJIGI_ENROLLMENT))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 무료_강의는_인원수_제한없이_들을_수_있다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        freeSession.openRecruiting();
        freeSession.enroll(JAVAJIGI_ENROLLMENT);
        freeSession.enroll(SANJIGI_ENROLLMENT);
    }

    @Test
    void 유료_강의는_인원수_제한이_있다() {
        Session paidSession = Session.createPaidSession(SESSION_ID, "paid", IMAGE, 1, 30_000L);
        paidSession.openRecruiting();
        paidSession.enroll(JAVAJIGI_ENROLLMENT);
        assertThatThrownBy(() -> paidSession.enroll(SANJIGI_ENROLLMENT))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 유료_강의는_지불한_금액이_맞아야_한다() {
        Session paidSession = Session.createPaidSession(SESSION_ID, "paid", IMAGE, 1, 20_000L);
        paidSession.openRecruiting();
        assertThatThrownBy(() -> paidSession.enroll(JAVAJIGI_ENROLLMENT))
                .isInstanceOf(SessionUnregistrableException.class);
    }
}