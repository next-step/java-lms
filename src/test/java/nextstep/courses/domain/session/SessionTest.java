package nextstep.courses.domain.session;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.user.UserTest;
import nextstep.courses.domain.user.Users;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SessionTest {

    public static final Session TEST_SESSION_CHARGED = new Session.Builder()
        .id(1L)
        .sessionDuration(
            SessionDuration.of(1L, LocalDateTime.parse("2023-05-01T00:00:00"), LocalDateTime.parse("2023-05-31T00:00:00")))
        .coverImagePath(CoverImage.of(1L, ""))
        .priceType(PriceType.CHARGED)
        .status(Status.ENROLLING)
        .maximumCapacity(10L)
        .users(Users.of(new ArrayList<>()))
        .build();

    private Long id = 1L;
    private LocalDateTime start = LocalDateTime.parse("2023-05-01T00:00:00");
    private LocalDateTime end = LocalDateTime.parse("2023-05-31T00:00:00");
    private CoverImage coverImagePath = CoverImage.of(1L, "");
    private PriceType priceType = PriceType.CHARGED;
    private Status status = Status.ENROLLING;
    private Long maximumCapacity = 100L;

    @Test
    @DisplayName("최대 수강 인원을 초과한 강의는 수강신청을 할 수 없다.")
    void 수강신청_모집중_최대인원초과() {
        Session actual = new Session.Builder()
            .id(id)
            .sessionDuration(SessionDuration.of(1L, start, end))
            .coverImagePath(coverImagePath)
            .priceType(priceType)
            .status(status)
            .maximumCapacity(0L)
            .users(Users.of(new ArrayList<>()))
            .build();

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }

    @Test
    @DisplayName("모집중 상태의 강의는 수강신청이 가능하다.")
    void 수강신청_모집중() throws CannotEnrollException {
        Session actual = new Session.Builder()
            .id(id)
            .sessionDuration(SessionDuration.of(1L, start, end))
            .coverImagePath(coverImagePath)
            .priceType(priceType)
            .status(status)
            .maximumCapacity(maximumCapacity)
            .users(Users.of(new ArrayList<>()))
            .build();

        actual.enroll(UserTest.TEST_USER);
        assertThat(actual.isEnrolled(UserTest.TEST_USER)).isTrue();
    }

    @Test
    @DisplayName("종료 상태의 강의는 수강신청을 할 수 없다.")
    void 수강신청_종료() {
        Session actual = new Session.Builder()
            .id(id)
            .sessionDuration(SessionDuration.of(1L, start, end))
            .coverImagePath(coverImagePath)
            .priceType(priceType)
            .status(Status.ENDED)
            .maximumCapacity(maximumCapacity)
            .users(Users.of(new ArrayList<>()))
            .build();

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 종료");
    }

    @Test
    @DisplayName("준비중 상태의 강의는 수강신청을 할 수 없다.")
    void 수강신청_준비중() {
        Session actual = new Session.Builder()
            .id(id)
            .sessionDuration(SessionDuration.of(1L, start, end))
            .coverImagePath(coverImagePath)
            .priceType(priceType)
            .status(Status.PREPARING)
            .maximumCapacity(maximumCapacity)
            .users(Users.of(new ArrayList<>()))
            .build();

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(UserTest.TEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 준비중");
    }

    @Test
    @DisplayName("동일한 상태를 갖는 두 강의는 같은 강의이다.")
    void 강의생성() {
        assertThat(Session.of(id, SessionDuration.of(1L, start, end), coverImagePath, priceType, status, maximumCapacity, Users.of(new ArrayList<>())))
            .isEqualTo(new Session.Builder()
                .id(id)
                .sessionDuration(SessionDuration.of(1L, start, end))
                .coverImagePath(coverImagePath)
                .priceType(priceType)
                .status(status)
                .maximumCapacity(maximumCapacity)
                .users(Users.of(new ArrayList<>()))
                .build());
    }

}