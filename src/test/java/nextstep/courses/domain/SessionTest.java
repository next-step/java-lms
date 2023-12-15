package nextstep.courses.domain;

import nextstep.courses.exception.BusinessInvalidValueException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class SessionTest {

    @Test
    void 무료강의생성() {
        Session freeSession = Session.ofFree(1L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(3L, 300, 200, 1024, new byte[100]), new Course(), new ArrayList<>());
        assertThat(freeSession.id()).isEqualTo(1L);
    }

    @Test
    void 유료강의생성() {
        long sessionPrice = 1_000_000L;
        int sessionCapacity = 100;

        Session paidSession = Session.ofPaid(2L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                new SessionCover(4L, 300, 200, 1024, new byte[100]), new Course(), sessionPrice, sessionCapacity, new ArrayList<>());
        assertThat(paidSession.id()).isEqualTo(2L);
    }

    @Test
    void 수강신청상태_exception() {
        Session session = Session.ofFree(5L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1), new SessionCover(5L, 300, 200, 1024, new byte[100]), new Course(), new ArrayList<>());
        assertThatExceptionOfType(BusinessInvalidValueException.class)
                .isThrownBy(() -> session.enroll(new NsUser(), 0L))
                .withMessage("수강신청 가능한 상태가 아닙니다.");
    }

    @Test
    void 최대수강인원_exception() {
        Session paidSession = Session.ofPaid(2L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                new SessionCover(6L, 300, 200, 1024, new byte[100]), new Course(), 10000L, 0, new ArrayList<>());
        paidSession.startEnrollment();

        assertThatExceptionOfType(BusinessInvalidValueException.class)
                .isThrownBy(() -> paidSession.enroll(new NsUser(), 1_000_000L))
                .withMessage("최대수강인원을 초과했습니다.");
    }

    @Test
    void 가격비교_exception() {
        Session paidSession = Session.ofPaid(2L, LocalDateTime.now(), LocalDateTime.now().plusMonths(1),
                new SessionCover(7L, 300, 200, 1024, new byte[100]), new Course(), 10000L, 1, new ArrayList<>());
        paidSession.startEnrollment();

        assertThatExceptionOfType(BusinessInvalidValueException.class)
                .isThrownBy(() -> paidSession.enroll(new NsUser(), 2_000_000L))
                .withMessage("강의 가격이 변동되었습니다.");
    }
}