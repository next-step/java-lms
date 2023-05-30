package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class NextStepUsersTest {
    @Test
    @DisplayName("강의 수강 신청 시 수강인원이 1 증가한다")
    void enroll() {
        NextStepUsers nextStepUsers = new NextStepUsers(1);
        nextStepUsers.enroll(NsUserTest.JAVAJIGI);
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
}
