package nextstep.courses.domain;

import static nextstep.courses.domain.SessionCoverImageTest.SAMPLE_COVER_IMAGE;
import static nextstep.qna.domain.TestFixtures.FIXED_DATE_TIME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.courses.CanNotJoinSessionException;
import nextstep.courses.InvalidSessionException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionTest {

    public static Session RECRUITING_FREE_SESSION = new Session(
        0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
        SAMPLE_COVER_IMAGE, 0, 0, SessionStatus.RECRUIT,
        FIXED_DATE_TIME
    );

    @Test
    @DisplayName("강의 데이터가 유효하면 성공적으로 생성된다")
    void new_success() {
        assertThatNoException()
            .isThrownBy(
                () ->  new Session(
                    FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
                    SAMPLE_COVER_IMAGE,
                    FIXED_DATE_TIME
                )
            );
    }

    @Test
    @DisplayName("강의 종료일이 시작일보다 앞서면 예외가 발생한다")
    void new_fail_for_invalid_date() {
        assertThatThrownBy(
            () ->  new Session(
                FIXED_DATE_TIME.plusDays(1), FIXED_DATE_TIME,
                SAMPLE_COVER_IMAGE,
                FIXED_DATE_TIME
            )
        ).isInstanceOf(InvalidSessionException.class);
    }

    @Test
    @DisplayName("모집 정원을 초과하지 않았고 모집중 상태라면 강의 참여에 성공한다")
    void join() {
        // given
        Session session = SessionTest.RECRUITING_FREE_SESSION;
        NsUser loginUser = NsUserTest.JAVAJIGI;

        // when
        session.join(loginUser);

        // then
        List<NsUser> learners = session.getLearners();
        assertThat(learners).contains(loginUser);
    }

    @ParameterizedTest
    @ValueSource(strings = {"PREPARE", "END"})
    @DisplayName("모집중이 아니라면 강의 참여 시 예외가 발생한다")
    void join_fail_for_not_recruiting(String sessionStatus) {
        // given
        Session session = new Session(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, 0, 0, SessionStatus.valueOf(sessionStatus),
            FIXED_DATE_TIME
        );
        NsUser loginUser = NsUserTest.JAVAJIGI;

        // when, then
        assertThatThrownBy(() -> session.join(loginUser))
            .isInstanceOf(CanNotJoinSessionException.class);
    }

    @Test
    @DisplayName("모집정원을 초과하면 강의 참여 시 예외가 발생한다")
    void join_fail_for_exceed_capacity() {
        // given
        Session session = new Session(
            0L, FIXED_DATE_TIME, FIXED_DATE_TIME.plusDays(1),
            SAMPLE_COVER_IMAGE, 800_000, 1, SessionStatus.RECRUIT,
            FIXED_DATE_TIME
        );
        NsUser joinedUser = NsUserTest.JAVAJIGI;
        session.join(joinedUser);

        // when, then
        assertThatThrownBy(() -> session.join(NsUserTest.SANJIGI))
            .isInstanceOf(CanNotJoinSessionException.class);
    }
}
