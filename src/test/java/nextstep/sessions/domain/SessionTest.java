package nextstep.sessions.domain;

import nextstep.users.domain.NsUser;
import nextstep.utils.TestImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {
    @DisplayName("무료 강의 생성 테스트")
    @Test
    void createSession() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024*1024, "jpeg"));
        PriceType priceType = PriceType.FREE;

        Session session = new Session(title, startAt, endAt, coverImage, priceType);

        assertThat(session.getTitle()).isEqualTo(title);
    }

    @DisplayName("유료 강의 생성 테스트")
    @Test
    void createPaidSession() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024*1024, "jpeg"));
        PriceType priceType = PriceType.PAID;
        Price price = new Price(10000);
        Integer maxAttendees = 10;

        Session session = new Session(title, startAt, endAt, coverImage, priceType, price, maxAttendees);

        assertThat(session.getTitle()).isEqualTo(title);
    }

    @DisplayName("강의 상태가 모집중이 아닌데 수강신청한 경우")
    @Test
    void applyClosedSession() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024*1024, "jpeg"));
        PriceType priceType = PriceType.PAID;
        Price price = new Price(10000);
        Integer maxAttendees = 10;

        Session session = new Session(title, startAt, endAt, coverImage, priceType, price, maxAttendees);
        session.close();

        NsUser testUser = new NsUser(1L, "testUser", "password", "name", "email");
        assertThatIllegalStateException()
                .isThrownBy(() -> session.apply(testUser))
                .withMessage("강의가 모집중이 아닙니다.");
    }

    @DisplayName("강의 상태가 모집중일 때 수강신청한 경우")
    @Test
    void applyOpenSession() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024*1024, "jpeg"));
        PriceType priceType = PriceType.PAID;
        Price price = new Price(10000);
        Integer maxAttendees = 10;

        Session session = new Session(title, startAt, endAt, coverImage, priceType, price, maxAttendees);

        NsUser testUser = new NsUser(1L, "testUser", "password", "name", "email");
        session.apply(testUser);

        assertThat(session.getAttendees()).contains(testUser);
    }

    @DisplayName("최대 수강인원 초과 시 수강신청 불가")
    @Test
    void applyExceedMaxAttendees() {
        String title = "테스트 타이틀";
        LocalDateTime startAt = LocalDateTime.now();
        LocalDateTime endAt = startAt.plusHours(1);
        CoverImage coverImage = new CoverImage(new TestImage(300, 200, 1024*1024, "jpeg"));
        PriceType priceType = PriceType.PAID;
        Price price = new Price(10000);
        Integer maxAttendees = 1;

        Session session = new Session(title, startAt, endAt, coverImage, priceType, price, maxAttendees);

        NsUser testUser1 = new NsUser(1L, "testUser1", "password", "name", "email");
        NsUser testUser2 = new NsUser(2L, "testUser2", "password", "name", "email");

        session.apply(testUser1);

        assertThatIllegalStateException()
                .isThrownBy(() -> session.apply(testUser2))
                .withMessage("강의가 모집중이 아닙니다.");
    }


}
