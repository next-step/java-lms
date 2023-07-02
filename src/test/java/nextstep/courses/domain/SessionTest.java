package nextstep.courses.domain;

import nextstep.courses.domain.enums.ProgressState;
import nextstep.courses.domain.enums.RecruitmentState;
import nextstep.courses.exception.SessionExpiredException;
import nextstep.courses.exception.SessionNotOpenException;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");
    private static final NsUser joy = new NsUser(2L, "joy", "password", "조이", "joy@nextstep.com");
    private static final NsUser david = new NsUser(3L, "david","password","데이빗","david@gamil.com");

    private static final Session session = new Session("20230801", "20230831", 10 ,0L);

    @Test
    void 생성자검증() {
        Assertions.assertThat(session)
                .isInstanceOf(Session.class);
    }

    @Test
    void 강의진행상태변경_불가() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);
        Session session =  new Session(0L, sessionDate, "https://nextstep.com", true , ProgressState.PREPARING,
                RecruitmentState.NOT_RECRUITING,30, 0L, LocalDateTime.now(), LocalDateTime.now());

        assertThatThrownBy(() -> {
            session.changeProgressState(ProgressState.END);
        }).isInstanceOf(SessionExpiredException.class).hasMessageContaining("강의종료일이 경과하여 상태 변경이 불가합니다.");
    }

    @Test
    void 강의모집상태변경_불가() {
        SessionDate sessionDate = new SessionDate("20230401", "20230431", false);
        Session session =  new Session(0L, sessionDate, "https://nextstep.com", true , ProgressState.PREPARING,
                RecruitmentState.NOT_RECRUITING,30, 0L, LocalDateTime.now(), LocalDateTime.now());

        assertThatThrownBy(() -> {
            session.changeRecruitmentState(RecruitmentState.RECRUITING);
        }).isInstanceOf(SessionExpiredException.class).hasMessageContaining("강의종료일이 경과하여 상태 변경이 불가합니다.");
    }

    @Test
    void 강의신청테스트() {
        session.changeProgressState(ProgressState.PROCEEDING);

        session.enroll(jerry);
        session.enroll(joy);
        session.enroll(david);

        Assertions.assertThat(session.getSignedUpStatus()).isEqualTo(3);
    }
}
