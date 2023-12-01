package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ParticipantCountTest {

    @DisplayName("최대 참가자 수 생성하고 참여자는 0명이다.")
    @Test
    void 최대참가자수_생성하고_참여자는_0명이다() {
        // given
        int maxCount = 10;
        // when
        ParticipantCount participantCount = new ParticipantCount(maxCount);
        // then
        assertThat(participantCount.max()).isEqualTo(10);
    }

    @DisplayName("최대 참가자 수 생성하고 기본참여자는 2명이다.")
    @Test
    void 최대참가자수_생성하고_기본참여자는_2명이다() {
        // given
        int maxCount = 10;
        int defaultCount = 2;
        // when
        ParticipantCount participantCount = new ParticipantCount(maxCount, defaultCount);
        // then
        assertThat(participantCount.nowCount()).isEqualTo(2);
    }

    @DisplayName("참가자를 추가한다.")
    @Test
    void 참가자를_추가한다() {
        // given
        int maxCount = 10;
        ParticipantCount participantCount = new ParticipantCount(maxCount);
        // when
        participantCount.add();
        // then
        assertThat(participantCount.nowCount()).isEqualTo(1);
    }

    @DisplayName("최대 참가자 수를 초과하면 예외가 발생한다.")
    @Test
    void 최대참가자수를_초과하면_예외가_발생한다() {
        // given
        int maxCount = 10;
        int defaultCount = 10;
        // when
        ParticipantCount participantCount = new ParticipantCount(maxCount, defaultCount);
        // then
        assertThatThrownBy(() -> participantCount.add())
                .isInstanceOf(IllegalArgumentException.class);
    }
}
