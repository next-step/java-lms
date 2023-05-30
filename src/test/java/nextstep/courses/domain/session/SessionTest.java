package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.InvalidTimeRangeException;
import nextstep.courses.domain.user.Name;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.UserTest;
import nextstep.courses.domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    public static final Session TEST_SESSION_CHARGED;

    public static final Session TEST_SESSION_SECOND;

    static {
        try {
            TEST_SESSION_CHARGED = buildSession(1L, Status.ENROLLING, 10L);
            TEST_SESSION_SECOND = buildSession(2L, Status.PREPARING, 100L);
        } catch (InvalidTimeRangeException e) {
            throw new RuntimeException(e);
        }
    }

    private Long id = 1L;
    private LocalDateTime start = LocalDateTime.parse("2023-05-01T00:00:00");
    private LocalDateTime end = LocalDateTime.parse("2023-05-31T00:00:00");
    private CoverImage coverImagePath = CoverImage.of(1L, "/resources/image/test");
    private PriceType priceType = PriceType.CHARGED;
    private Status statusEnrolling = Status.ENROLLING;
    private Long maximumCapacity = 100L;
    private Users users = Users.of(new ArrayList<>(List.of(User.of(1L, Name.of("유저1")))));

    @Test
    @DisplayName("최대 수강 인원을 초과한 강의는 수강신청을 할 수 없다.")
    void 수강신청_모집중_최대인원초과() throws InvalidTimeRangeException {
        Session actual = buildSession(id, statusEnrolling, 0L);

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("모집중 상태의 강의는 수강신청이 가능하다.")
    void 수강신청_모집중() throws CannotEnrollException, InvalidTimeRangeException {
        Session actual = buildSession(id, statusEnrolling, maximumCapacity);

        actual.enroll(UserTest.TEST_USER);
        assertThat(actual.isEnrolled(UserTest.TEST_USER)).isTrue();
    }

    @Test
    @DisplayName("종료 상태의 강의는 수강신청을 할 수 없다.")
    void 수강신청_종료() throws InvalidTimeRangeException {
        Session actual = buildSession(id, Status.ENDED, maximumCapacity);

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 종료");
    }

    @Test
    @DisplayName("준비중 상태의 강의는 수강신청을 할 수 없다.")
    void 수강신청_준비중() throws InvalidTimeRangeException {
        Session actual = buildSession(id, Status.PREPARING, maximumCapacity);

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 준비중");
    }

    @Test
    @DisplayName("동일한 상태를 갖는 두 강의는 같은 강의이다.")
    void 강의생성() throws InvalidTimeRangeException {
        assertThat(Session.of(id, Duration.of(start, end), coverImagePath, priceType,
            statusEnrolling, maximumCapacity, users))
            .isEqualTo(buildSession(id, Status.ENROLLING, maximumCapacity));
    }

    private static Session buildSession(Long id, Status status, Long maximumCapacity)
        throws InvalidTimeRangeException {
        LocalDateTime start = LocalDateTime.parse("2023-05-01T00:00:00");
        LocalDateTime end = LocalDateTime.parse("2023-05-31T00:00:00");
        CoverImage coverImagePath = CoverImage.of(1L, "/resources/image/test");
        PriceType priceType = PriceType.CHARGED;
        return new Session.Builder()
            .id(id)
            .duration(Duration.of(start, end))
            .coverImage(coverImagePath)
            .priceType(priceType)
            .status(status)
            .maximumCapacity(maximumCapacity)
            .users(Users.of(new ArrayList<>(List.of(User.of(1L, Name.of("유저1"))))))
            .build();
    }
}