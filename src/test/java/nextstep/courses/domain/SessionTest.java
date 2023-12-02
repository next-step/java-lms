package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class SessionTest {

    private final Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2023, 12, 25));
    private final Thumbnail thumbnail = new Thumbnail(1, "thumbnail.png", 1024L * 1024L, 300, 200);
    private final List<NsUser> students = List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    private final SessionType paidSession = SessionType.determineSessionType(true, 100, 100);

    @Test
    public void isRecruiting_모집중일때_TRUE_반환테스트() {
        SessionStatus recruitingStatus = SessionStatus.RECRUITING;

        Session recruitingSession = new Session(1, "session", period, thumbnail,
                paidSession, recruitingStatus, students);

        assertThat(recruitingSession.isRecruiting()).isTrue();
    }

    @Test
    public void isRecruiting_강연종료시_FALSE_반환테스트() {
        SessionStatus finishedStatus = SessionStatus.FINISHED;

        Session finishedSession = new Session(1, "session", period, thumbnail,
                paidSession, finishedStatus, students);

        assertThat(finishedSession.isRecruiting()).isFalse();
    }

    @Test
    public void isRecruiting_강연준비중일시_False_반환테스트() {
        SessionStatus preparingStatus = SessionStatus.PREPARING;

        Session preparingSession = new Session(1, "session", period, thumbnail,
                paidSession, preparingStatus, students);

        assertThat(preparingSession.isRecruiting()).isFalse();
    }


}
