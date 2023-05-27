package nextstep.courses.domain;

import nextstep.courses.fixtures.SessionFixtureBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("강의는 시작일과 종료일을 가진다.")
    void date() {
        // given
        Session session = new SessionFixtureBuilder().build();

        // when
        LocalDate startDateTime = session.getStartDateTime();
        LocalDate endDateTime = session.getEndDateTime();

        // then
        assertThat(startDateTime).isEqualTo(LocalDate.of(2021, 1, 1));
        assertThat(endDateTime).isEqualTo(LocalDate.of(2021, 2, 1));
    }

    @Test
    @DisplayName("종료일이 시작일보다 빠를 때 예외")
    void dateException() {
        // when
        assertThatThrownBy(() -> new SessionFixtureBuilder()
                .withStartDate(LocalDate.of(2021, 2, 1))
                .withEndDate(LocalDate.of(2021, 1, 1))
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
    }

    @Test
    @DisplayName("강의는 강의 커버 이미지 정보를 가진다.")
    void coverImage() {
        // given
        Session session = new SessionFixtureBuilder().build();

        // when
        String coverImageUrl = session.getCoverImageUrl();

        // then
        assertThat(coverImageUrl).isEqualTo(new SessionFixtureBuilder().coverImageUrl);
    }

    @Test
    @DisplayName("이미지 정보가 URL 형식이 아닐 때 예외")
    void coverImageException() {
        // given
        String coverImageUrl = "testCover.png";


        // when
        assertThatThrownBy(() -> new SessionFixtureBuilder()
                .withCoverImageUrl(coverImageUrl)
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효하지 않은 URL 입니다.");
    }

    @Test
    @DisplayName("무료 강의일 때 가격이 0원인지 확인")
    void payTypeFree() {
        // given
        Session session = new SessionFixtureBuilder()
                .withPrice(0L)
                .build();

        // when
        SessionPayType payType = session.getPayType();

        // then
        assertThat(payType).isEqualTo(SessionPayType.FREE);
        assertThat(session.getPrice()).isEqualTo(0L);
    }

    @Test
    @DisplayName("유료 강의일 때 가격이 0원이 아닌지 확인")
    void payTypePaid() {
        // given
        Session session = new SessionFixtureBuilder()
                .withPrice(10000L)
                .build();

        // when
        SessionPayType payType = session.getPayType();

        // then
        assertThat(payType).isEqualTo(SessionPayType.PAID);
        assertThat(session.getPrice()).isEqualTo(10000L);
    }

    @Test
    @DisplayName("가격이 0원 이하일 때 예외")
    void payTypeException() {
        // given
        Long price = -1L;

        // when
        assertThatThrownBy(() -> new SessionFixtureBuilder()
                .withPrice(price)
                .build())
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 0원 이상이어야 합니다.");
    }

    @ParameterizedTest(name = "{displayName} - {argumentsWithNames}")
    @EnumSource(SessionStatus.class)
    @DisplayName("강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.")
    void status(SessionStatus status) {
        // given
        Session session = new SessionFixtureBuilder()
                .withStatus(status)
                .build();

        // when
        SessionStatus sessionStatus = session.getStatus();

        // then
        assertThat(sessionStatus).isEqualTo(status);
    }

    @Test
    @DisplayName("모집중일 때 수강신청")
    void register() {
        // given
        Session session = new SessionFixtureBuilder()
                .withStatus(SessionStatus.RECRUITING)
                .build();

        // when
        int userId = 0;
        session.enroll(userId);

        // then
        assertThat(session.countUsers()).isEqualTo(1);
    }

    @ParameterizedTest(name = "{displayName} - {argumentsWithNames}")
    @EnumSource(value = SessionStatus.class, names = {"OPENED", "CLOSED"})
    @DisplayName("모집 중이 아닐 때 수강신청 예외")
    void registerException(SessionStatus status) {
        // given
        Session session = new SessionFixtureBuilder()
                .withStatus(status)
                .build();

        // then
        assertThatThrownBy(() -> session.enroll(0))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("모집 중인 강의만 수강 신청이 가능합니다.");
    }

    @Test
    @DisplayName("강의 상태는 변경할 수 있다.")
    void changeStatus() {
        // given
        Session session = new SessionFixtureBuilder()
                .withStatus(SessionStatus.OPENED)
                .build();

        // when
        session.open();
        session.recruit();
        session.close();

        // then
        assertThat(session.getStatus()).isEqualTo(SessionStatus.CLOSED);
    }
}