package nextstep.courses.domain;

import nextstep.users.domain.NsUserFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


class AttendeesTest {

    @DisplayName("이미 수강신청한 유저는 수강신청 못한다")
    @Test
    public void duplicatedUserEnrollTest() {
        Attendees attendees = new Attendees();
        attendees.add(NsUserFixtures.JAVAJIGI);

        Assertions.assertThatThrownBy(() -> attendees.add(NsUserFixtures.JAVAJIGI))
                .isInstanceOf(IllegalStateException.class);
    }

}