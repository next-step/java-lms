package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.courses.CannotEnrollException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void 수강신청_모집중_최대인원초과() {
        Long id = 1L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        String coverImagePath = "";
        PriceType priceType = PriceType.CHARGED;
        Status status = Status.ENROLLING;
        Long maximumCapacity = 0L;
        Session actual = Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>());

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(NsUser.GUEST_USER))
            .withMessageMatching("최대 수강 인원을 초과했습니다.");
    }

    @Test
    void 수강신청_모집중() throws CannotEnrollException {
        Long id = 1L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        String coverImagePath = "";
        PriceType priceType = PriceType.CHARGED;
        Status status = Status.ENROLLING;
        Long maximumCapacity = 100L;
        Session actual = Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>());

        actual.enroll(NsUser.GUEST_USER);
        assertThat(actual.isEnrolled(NsUser.GUEST_USER)).isTrue();
    }

    @Test
    void 수강신청_종료() {
        Long id = 1L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        String coverImagePath = "";
        PriceType priceType = PriceType.CHARGED;
        Status status = Status.ENDED;
        Long maximumCapacity = 100L;
        Session actual = Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>());

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(NsUser.GUEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 종료");
    }

    @Test
    void 수강신청_준비중() {
        Long id = 1L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        String coverImagePath = "";
        PriceType priceType = PriceType.CHARGED;
        Status status = Status.PREPARING;
        Long maximumCapacity = 100L;
        Session actual = Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>());

        assertThatExceptionOfType(CannotEnrollException.class)
            .isThrownBy(() -> actual.enroll(NsUser.GUEST_USER))
            .withMessageMatching("모집중인 강의가 아닙니다 : 준비중");
    }

    @Test
    void 강의생성() {
        Long id = 1L;
        LocalDateTime start = LocalDateTime.now();
        LocalDateTime end = LocalDateTime.now();
        String coverImagePath = "";
        PriceType priceType = PriceType.CHARGED;
        Status status = Status.PREPARING;
        Long maximumCapacity = 100L;
        assertThat(Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>()))
            .isEqualTo(Session.of(id, start, end, coverImagePath, priceType, status, maximumCapacity, new ArrayList<>()));
    }

}