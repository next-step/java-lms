package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.courses.domain.image.Image;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {
    private static final Image IMAGE = new Image(1_024, 300, 200, "png");
    private static final EnrollmentCondition JAVAJIGI = EnrollmentConditionTest.JAVAJIGI_ENROLLMENT;
    private static final EnrollmentCondition SANJIGI = EnrollmentConditionTest.SANJIGI_ENROLLMENT;

    @Test
    void 강의는_모집중_상태가_아니면_신청할_수_없다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        assertThatThrownBy(() -> freeSession.enroll(JAVAJIGI))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 무료_강의는_인원수_제한없이_들을_수_있다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        freeSession.openRecruiting();
        freeSession.enroll(JAVAJIGI);
        freeSession.enroll(SANJIGI);
    }

    @Test
    void 유료_강의는_인원수_제한이_있다() {
        Session paidSession = Session.createPaidSession(123L, "paid", IMAGE, 1, 30_000L);
        paidSession.openRecruiting();
        paidSession.enroll(JAVAJIGI);
        assertThatThrownBy(() -> paidSession.enroll(SANJIGI))
                .isInstanceOf(SessionUnregistrableException.class);
    }

    @Test
    void 유료_강의는_지불한_금액이_맞아야_한다() {
        Session paidSession = Session.createPaidSession(123L, "paid", IMAGE, 1, 20_000L);
        paidSession.openRecruiting();
        assertThatThrownBy(() -> paidSession.enroll(JAVAJIGI))
                .isInstanceOf(SessionUnregistrableException.class);
    }
}