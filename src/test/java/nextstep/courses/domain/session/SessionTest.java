package nextstep.courses.domain.session;

import nextstep.courses.domain.image.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {
    public static final Long SESSION_ID = 123L;
    private static final Image IMAGE = new Image(1_024, 300, 200, "png");

    @Test
    void 모집중_상태로_변경하면_정상적으로_상태가_변경된다() {
        Session freeSession = Session.createFreeSession("free", IMAGE);
        freeSession.openRecruiting();
        assertThat(freeSession.isRecruiting()).isTrue();
    }

    @Test
    void 강의_수강신청_가능_조건을_입력해_수강신청_가능_여부를_확인한다() {
        Session paidSession = Session.createPaidSession(SESSION_ID, "paid", IMAGE, 20_000L);
        paidSession.openRecruiting();

        EnrollmentCondition trueCondition = new EnrollmentCondition(NsUserTest.JAVAJIGI, new Payment(SESSION_ID, NsUserTest.JAVAJIGI.getId(), 20_000L));
        assertThat(paidSession.canEnroll(trueCondition)).isTrue();

        EnrollmentCondition falseCondition = new EnrollmentCondition(NsUserTest.SANJIGI, new Payment(SESSION_ID, NsUserTest.SANJIGI.getId(), 30_000L));
        assertThat(paidSession.canEnroll(falseCondition)).isFalse();
    }
}