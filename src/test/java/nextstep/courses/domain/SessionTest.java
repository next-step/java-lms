package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTest {

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void 강의는_시작일과_종료일을_가진다() {
        LocalDateTime startDate = LocalDateTime.of(2023, 11, 29, 9, 30);
        LocalDateTime endDate = LocalDateTime.of(2023, 12, 29, 18, 30);
        Session session = new Session(startDate, endDate);

        assertThat(session.getStartDate()).isBefore(endDate);
    }

    @Test
    @DisplayName("강의는 이미지를 가질 수 있다.")
    void 강의는_이미지를_가질_수_있다() {
        Session session = new Session(new Image("png", 300, 200));
        assertThat(session.getImage()).isNotNull();
    }

    @Test
    @DisplayName("강의는 준비중 상태를 가진다.")
    void 강의는_준비중_상태를_가질_수_있다() {
        Session session = new Session(SessionStatus.PREPARING);
        SessionStatus result = session.isSessionStatus();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("강의는 모집중 상태를 가진다.")
    void 강의는_모집중_상태를_가질_수_있다() {
        Session session = new Session(SessionStatus.RECRUITING);
        SessionStatus result = session.isSessionStatus();
        assertThat(result).isNotNull();
    }

    @Test
    @DisplayName("강의는 종료 상태를 가진다.")
    void 강의는_종료_상태를_가질_수_있다() {
        Session session = new Session(SessionStatus.CLOSE);
        SessionStatus result = session.isSessionStatus();
        assertThat(result).isNotNull();
    }
}
