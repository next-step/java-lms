package nextstep.Session;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParticipantsTest {

    @Test
    @DisplayName("같은 강의를 두번 신청하는 경우 예외를 반환합니다.")
    void enrollDuplicationTest() {
        Participants participants = new Participants();
        participants.addStudent(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> participants.addStudent(NsUserTest.JAVAJIGI))
            .isInstanceOf(IllegalArgumentException.class);
    }

}
