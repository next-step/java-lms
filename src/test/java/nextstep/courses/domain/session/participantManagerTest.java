package nextstep.courses.domain.session;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class participantManagerTest {

    @DisplayName("최대 참가자 수 생성하고 참여자는 0명이다.")
    @Test
    void 최대참가자수_생성하고_참여자는_0명이다() {
        // given
        int maxCount = 10;
        // when
        ParticipantManager participantManager = new ParticipantManager(maxCount);
        // then
        assertThat(participantManager.max()).isEqualTo(10);
    }

    @DisplayName("최대 참가자 수 생성하고 기본참여자는 1명이다.")
    @Test
    void 최대참가자수_생성하고_기본참여자는_1명이다() {
        // given
        int maxCount = 10;
        List<NsUser> user1 = List.of(NsUserTest.JAVAJIGI);
        SessionParticipants participants = new SessionParticipants(user1);
        // when
        ParticipantManager participantManager = new ParticipantManager(maxCount, participants);
        // then
        assertThat(participantManager.nowCount()).isEqualTo(1);
    }

    @DisplayName("참가자를 추가한다.")
    @Test
    void 참가자를_추가한다() {
        // given
        int maxCount = 10;
        List<NsUser> users = new ArrayList<>();
        ParticipantManager participantManager = new ParticipantManager(maxCount, new SessionParticipants(users));
        // when
        participantManager.add(NsUserTest.JAVAJIGI);
        // then
        assertThat(participantManager.nowCount()).isEqualTo(1);
    }

    @DisplayName("최대 참가자 수를 초과하면 예외가 발생한다.")
    @Test
    void 최대참가자수를_초과하면_예외가_발생한다() {
        // given
        int maxCount = 1;
        List<NsUser> users = new ArrayList<>();
        users.add(NsUserTest.JAVAJIGI);
        // when
        ParticipantManager participantManager = new ParticipantManager(maxCount, new SessionParticipants(users));
        // then
        assertThatThrownBy(() -> participantManager.validateParticipant())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
