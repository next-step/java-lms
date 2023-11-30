package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {

    @Test
    @DisplayName("Session이 처음 생성되면, Session의 status는 Recruiting 상태가 아니다.")
    void testInitSessionStatusIsReady() {
        //given
        final Session freeSession = buildDefaultFreeSession();
        final Session paidSession = buildDefaultPaidSession();

        //when
        final boolean freeSessionIsNotRecruiting = freeSession.isNotRecruiting();
        final boolean paidSessionNotRecruiting = paidSession.isNotRecruiting();

        //then
        assertThat(freeSessionIsNotRecruiting).isTrue();
        assertThat(paidSessionNotRecruiting).isTrue();
    }

    private PaidSession buildDefaultPaidSession() {
        return new PaidSession(
                "TDD, 클린 코드 with Java",
                3000L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0)
        );
    }

    private FreeSession buildDefaultFreeSession() {
        return new FreeSession(
                "TDD, 클린 코드 with Java",
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0)
        );
    }

    @Test
    @DisplayName("session을 생성할 때, 강의 커버 이미지를 넣을 수 있다.")
    void testSessionConstructorWithCoverImage() {
        //given
        final CoverImage coverImage = new CoverImage(1000, ImageType.JPG, 300, 200);
        final Session session = new PaidSession(
                "TDD, 클린 코드 with Java",
                3000L,
                LocalDateTime.of(2024, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 0, 0),
                coverImage
        );

        //when
        final CoverImage getCoverImage = session.getCoverImage();

        //then
        assertThat(getCoverImage).isEqualTo(coverImage);
    }

    @Test
    @DisplayName("session을 생성할 때, 강의 커버 이미지를 넣지 않으면 기본 CoverImage가 들어간다.")
    void testSessionConstructorWithNoCoverImage() {
        //given
        final Session session = buildDefaultPaidSession();
        final CoverImage coverImage = CoverImage.defaultCoverImage();

        //when
        final CoverImage getCoverImage = session.getCoverImage();

        //then
        assertThat(getCoverImage).isEqualTo(coverImage);
    }

    @ParameterizedTest
    @MethodSource("ofProvider")
    @DisplayName("of 메서드를 통해, FreeSession 혹은 PaidSession이 생성된다.")
    void testOf(final long price, final Class<FreeSession> type) {
        //given
        final String title = "TDD, 클린 코드 with Java";
        final LocalDateTime startDate = LocalDateTime.of(2024, 1, 1, 0, 0);
        final LocalDateTime endDate = LocalDateTime.of(2024, 12, 31, 0, 0);

        //when
        final Session session = Session.of(title, price, startDate, endDate);

        //then
        assertThat(session).isInstanceOf(type);
    }

    public static Stream<Arguments> ofProvider() {
        return Stream.of(
                Arguments.of(3000L, PaidSession.class),
                Arguments.of(0L, FreeSession.class)
        );
    }
}
