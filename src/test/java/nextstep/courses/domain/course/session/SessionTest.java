package nextstep.courses.domain.course.session;

import nextstep.courses.fixture.ImageFixtures;
import nextstep.courses.fixture.SessionFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private Session session;

    @Test
    @DisplayName("강의는 이미지가 없으면 이미지를 추가하라는 예외를 반환 한다.")
    void newObject_imageNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        null,
                        SessionFixtures.duration(),
                        SessionFixtures.freeSessionStateZero(),
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 기간이 없으면 기간을 추가하라는 예외를 반환 한다.")
    void newObject_durationNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        ImageFixtures.images(),
                        null,
                        SessionFixtures.freeSessionStateZero(),
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 강의 상태가 없으면 상태를 추가하라는 예외를 반환 한다.")
    void newObject_sessionStateNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        ImageFixtures.images(),
                        SessionFixtures.duration(),
                        null,
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnReady 는 변경할 날짜가 강의 종료일과 같거나 늦으면 예외를 던진다.")
    void changeOnReady_changeDateIsSameOrAfterWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnReady(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnReady(SessionFixtures.DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnReady 는 강의를 준비중으로 변경한다.")
    void changeOnReady_success() {
        session = SessionFixtures.createdFreeSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.ONGOING);

        Session updatedSession = session.changeOnReady(SessionFixtures.DATE_2023_12_5);

        assertThat(updatedSession.sessionProgressStatus()).isEqualTo(SessionProgressStatus.READY);
    }

    @Test
    @DisplayName("changeOnGoing 는 변경할 날짜가 강의 종료일과 같거나 늦으면 예외를 던진다.")
    void changeOnGoing_changeDateIsSameOrAfterWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnGoing(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnGoing(SessionFixtures.DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnGoing 는 강의를 진행중으로 변경한다.")
    void changeOnGoing_success() {
        session = SessionFixtures.createdFreeSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.READY);

        Session updatedSession = session.changeOnGoing(SessionFixtures.DATE_2023_12_5);

        assertThat(updatedSession.sessionProgressStatus()).isEqualTo(SessionProgressStatus.ONGOING);
    }

    @Test
    @DisplayName("changeOnEnd 는 변경할 날짜가 강의 종료일보다 빠르거나 같다면 예외를 던진다.")
    void changeOnEnd_changeDateIsBeforeOrSameWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnEnd(SessionFixtures.DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnEnd(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnEnd 는 강의를 종료로 변경한다.")
    void changeOnEnd_success() {
        session = SessionFixtures.createdFreeSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.READY);

        Session updatedSession = session.changeOnEnd(SessionFixtures.DATE_2023_12_12);

        assertThat(updatedSession.sessionProgressStatus()).isEqualTo(SessionProgressStatus.END);
    }
}
