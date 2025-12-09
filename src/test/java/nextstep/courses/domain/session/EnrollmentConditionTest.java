package nextstep.courses.domain.session;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;

public class EnrollmentConditionTest {
    public static final Long SESSION_ID = 123L;
    public static final EnrollmentCondition JAVAJIGI_ENROLLMENT = new EnrollmentCondition(NsUserTest.JAVAJIGI, new Payment(SESSION_ID, NsUserTest.JAVAJIGI.getId(), 30_000L));
    public static final EnrollmentCondition SANJIGI_ENROLLMENT = new EnrollmentCondition(NsUserTest.SANJIGI, new Payment(SESSION_ID, NsUserTest.SANJIGI.getId(), 30_000L));
}