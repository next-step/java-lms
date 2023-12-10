package nextstep.courses.domain;

import nextstep.courses.exception.ParticipantsException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class ParticipantsTest {
    @Test
    @DisplayName("같은 참여자가 신청하면 에러를 던진다")
    void 참여자_중복에러() {
        Participants participants = new Participants(new HashSet<>(Set.of(NsUserTest.JAVAJIGI)));

        Assertions.assertThatThrownBy(() -> participants.add(NsUserTest.JAVAJIGI))
                .isInstanceOf(ParticipantsException.class);
        
    }
}
