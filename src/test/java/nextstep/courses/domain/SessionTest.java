package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    @DisplayName("강의는 시작일과 종료일을 가진다.")
    @Test
    void shouldHaveStartDateAndEndDate() {
        LocalDate startDate = LocalDate.of(2023, 3, 2);
        LocalDate endDate = LocalDate.of(2023, 5, 1);
        CardinalNumber cardinalNumber = new CardinalNumber(1);
        Session session = new Session(cardinalNumber, startDate, endDate);

        assertThat(session.isStartDateSame(startDate)).isTrue();
        assertThat(session.isEndDateSame(endDate)).isTrue();
    }

    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    @Test
    void shouldHaveCoverImageInfo() {
        String imgUrl = "img/url.jpg";
        Session session = new Session();
        session.updateImageWithUrl(imgUrl);

        assertThat(session.isSameCoverImage(imgUrl)).isTrue();
    }

    @DisplayName("강의는 무료 강의와 유료 강의로 나뉜다.")
    @Test
    void shouldDistinguishByFreeOrPaid() {
        Session freeSession = new Session(SessionType.FREE);
        Session paidSession = new Session(SessionType.PAID);

        assertThat(freeSession.isFree()).isTrue();
        assertThat(paidSession.isFree()).isFalse();
    }
}
