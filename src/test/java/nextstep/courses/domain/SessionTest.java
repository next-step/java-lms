package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("강의는 시작일을 가진다.")
    void startAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.FREE, SessionStatus.PREPARING);

        assertThat(session.getStartAt()).isEqualTo(startAt);
    }

    @Test
    @DisplayName("강의는 종료일을 가진다.")
    void endAt() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.FREE, SessionStatus.PREPARING);

        assertThat(session.getEndAt()).isEqualTo(endAt);
    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void coverImage() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.FREE, SessionStatus.PREPARING);

        assertThat(session.getCoverImage()).isEqualTo(coverImage);
    }

    @Test
    @DisplayName("무료 강의")
    void freeSession() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.FREE, SessionStatus.PREPARING);

        assertThat(session.isFree()).isTrue();
    }

    @Test
    @DisplayName("유료 강의")
    void paidSession() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.PAID, SessionStatus.PREPARING);

        assertThat(session.isPaid()).isTrue();
    }

    @Test
    @DisplayName("강의 상태 준비중")
    void preparingSession() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.PAID, SessionStatus.PREPARING);

        assertThat(session.isPreparing()).isTrue();
    }
    @Test
    @DisplayName("강의 상태 모집중")
    void recruitingSession() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.PAID, SessionStatus.RECRUITING);

        assertThat(session.isRecruiting()).isTrue();
    }
    @Test
    @DisplayName("강의 상태 종료")
    void closedSession() {
        LocalDateTime startAt = LocalDateTime.MIN;
        LocalDateTime endAt = LocalDateTime.MAX;
        String coverImage = "커버이미정보";

        Session session = new Session(startAt, endAt, coverImage, PaymentType.PAID, SessionStatus.CLOSED);

        assertThat(session.isClosed()).isTrue();
    }
}
