package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

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
}
