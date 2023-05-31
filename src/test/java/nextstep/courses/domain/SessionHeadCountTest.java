package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionHeadCountTest {

    @Test
    void createTest_수강신청_정원초과() {
        SessionHeadCount sessionHeadCount = new SessionHeadCount(1);
        sessionHeadCount.addPerson(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> sessionHeadCount.addPerson(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강신청 정원을 넘었습니다.");
    }

    @Test
    void createTest_수강신청_중복요청() {
        SessionHeadCount sessionHeadCount = new SessionHeadCount(3);
        sessionHeadCount.addPerson(NsUserTest.JAVAJIGI);
        sessionHeadCount.addPerson(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> sessionHeadCount.addPerson(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("중복 신청입니다.");
    }
}