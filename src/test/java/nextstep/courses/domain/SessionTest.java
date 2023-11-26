package nextstep.courses.domain;

import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.exception.NotRecruitingSessionException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionTest {

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다")
    public void session_image() {
        assertThat(new Session(0L, duration(), image())).extracting(Session::image).isEqualTo(image());
    }

    @Test
    @DisplayName("강의 상태가 모집중이 아닐 때 수강신청 시 에러 발생한다")
    public void not_recruiting_status_apply() {
        Session session = new Session(0L, duration(), image(), SessionStatus.READY);

        assertThatExceptionOfType(NotRecruitingSessionException.class)
            .isThrownBy(() -> session.apply(null, NsUserTest.JAVAJIGI))
            .withMessageMatching("모집중인 강의가 아닙니다.");
    }

    private Duration duration() {
        return new Duration(LocalDate.now(), LocalDate.now());
    }

    private Image image() {
        return new Image(1, "JPG", 300, 200);
    }

}
