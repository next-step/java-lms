package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NextStepUsersTest {
    @Test
    @DisplayName("승인된 사용자 강의 수강 신청 시 수강인원이 1 증가한다")
    void enroll() {
        NextStepUsers nextStepUsers = new NextStepUsers(1);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI);
        sessionUser.approve();
        nextStepUsers.enroll(sessionUser);
        assertThat(nextStepUsers.enrollmentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강의 최대 수강 인원을 초과할 수 없다")
    void enrollFail() {
        NextStepUsers nextStepUsers = new NextStepUsers(0);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> nextStepUsers.enroll(NsUserTest.JAVAJIGI))
                .withMessageMatching(NextStepUsers.MAXIMUM_ENROLLMENT_MESSAGE);
    }

    @Test
    @DisplayName("승인되지 않는 사용자의 수강신청을 취소할 수 있다")
    void cancel() {
        NextStepUsers nextStepUsers = new NextStepUsers(1);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI);
        nextStepUsers.enroll(sessionUser);
        nextStepUsers.cancel(sessionUser);
        assertThat(nextStepUsers.enrollmentCount()).isZero();
    }

    @Test
    @DisplayName("승인된 사용자의 수강신청을 취소 할 경우 예외 발생")
    void cancel_Fail() {
        NextStepUsers nextStepUsers = new NextStepUsers(1);
        SessionUser sessionUser = new SessionUser(NsUserTest.JAVAJIGI);
        sessionUser.approve();
        nextStepUsers.enroll(sessionUser);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> nextStepUsers.cancel(sessionUser))
                .withMessageMatching("수강 신청이 승인된 사용자입니다.");
    }
}
