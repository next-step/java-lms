package nextstep.courses.domain;

import nextstep.courses.enums.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class SessionTest {

    @Test
    void 수강신청은_모집중일때만_가능() {
        Session S1 = create(SessionStatus.RECRUITING, 1);
        Session S2 = create(SessionStatus.PREPARING, 1);
        Session S3 = create(SessionStatus.END, 1);
        assertThat(S1.enrollNsUser(NsUserTest.JAVAJIGI).countNsUsers()).isEqualTo(1);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> S2.enrollNsUser(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의가 모집중 일때만 등록 가능합니다.");


        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> S3.enrollNsUser(NsUserTest.JAVAJIGI))
                .withMessageMatching("강의가 모집중 일때만 등록 가능합니다.");
    }

    @Test
    void 강의는_강의_최대_수강_인원을_초과할_수_없다() {
        Session S1 = create(SessionStatus.RECRUITING, 1);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    S1.enrollNsUser(NsUserTest.JAVAJIGI);
                    S1.enrollNsUser(NsUserTest.SANJIGI);
                })
                .withMessageMatching("강의 최대 수강 인원이 초과되었습니다.");
    }

    public static Session create(SessionStatus sessionStatus, int maxNsUserCount) {
        return new Session(1L, 1L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), "https://test.png", true, sessionStatus, new NsUsers(maxNsUserCount));
    }
}
