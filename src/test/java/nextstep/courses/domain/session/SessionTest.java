package nextstep.courses.domain.session;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @DisplayName("강의가 모집중이 아니면 수강신청에 실패 한다.")
    @Test
    void 강의가_준비중이_아닐떄() {
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));

        final Session tddSession = new FreeSession(1L, "tdd", sessionPeriod, SessionStatus.FINISHED, null);

        assertThatThrownBy(() -> {
            tddSession.enroll(new Payment("1", 1L, JAVAJIGI.getId(), 1000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의의 남은자리가 없으면 수강신청에 실패한다.")
    @Test
    void 강의의_남은자리가_없을떄() {
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final CoverImage coverImage = new CoverImage(3000L, new ImagePixel(300, 200), ImageType.SVG);

        final Session tddSession = new PaidSession(1L, "tdd", sessionPeriod, SessionStatus.FINISHED, coverImage
                , new Amount(1000L), 0);

        assertThatThrownBy(() -> {
            tddSession.enroll(new Payment("1", 1L, JAVAJIGI.getId(), 1000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("강의금액과 결제한 금액이 다르면 수강신청에 실패한다.")
    @Test
    void 강의금액과_결제금액이_다를떄() {
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        final CoverImage coverImage = new CoverImage(3000L, new ImagePixel(300, 200), ImageType.SVG);

        final Session tddSession = new PaidSession(1L, "tdd", sessionPeriod, SessionStatus.FINISHED, coverImage
                , new Amount(1500L), 10);

        assertThatThrownBy(() -> {
            tddSession.enroll(new Payment("1", 1L, JAVAJIGI.getId(), 1000L));
        }).isInstanceOf(IllegalArgumentException.class);
    }

}
