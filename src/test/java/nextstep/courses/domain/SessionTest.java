package nextstep.courses.domain;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.enums.SessionProcessStatus;
import nextstep.courses.domain.session.enums.SessionRecruitStatus;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
class SessionTest {


    @Test
    void 강의는_이미지_정보가_등록되어야한다() {
        assertThatThrownBy(() -> {
            new Session(new SessionImage());
        }).isInstanceOf(IllegalArgumentException.class);

    }
    @Test
    void 강의는_모집중일때_신청_가능하다() throws CannotRegisterException, PeriodException {
        Session session = new Session(SessionStatus.OPEN);
        assertThat(session.register(NsUser.GUEST_USER)).isEqualTo(new Students(NsUser.GUEST_USER));
    }

    @Test
    void 강의는_모집중에도_신청_가능하다() throws CannotRegisterException, PeriodException {
        Session session = new Session(SessionProcessStatus.WAITING, SessionRecruitStatus.OPEN);
        assertThat(session.register(NsUser.GUEST_USER)).isEqualTo(new Students(NsUser.GUEST_USER));
    }

    @Test
    void 강의는_모집중이_아니면_신청_불가능하다() throws PeriodException {
        Session session = new Session(SessionProcessStatus.WAITING, SessionRecruitStatus.END);
        assertThatThrownBy(() -> {
            session.register(NsUser.GUEST_USER);
        }).isInstanceOf(CannotRegisterException.class);
    }

    @Test
    void 강의의_시작일은_종료일보다_커야한다() {
        LocalDate startDate = LocalDate.of(2023, 12, 03);
        LocalDate endDate = LocalDate.of(2023, 12, 01);
        assertThatThrownBy(() -> {
            new Session(SessionStatus.WAITING, startDate, endDate);
        }).isInstanceOf(PeriodException.class);
    }

    @Test
    void 유료강의는_제한인원을_초과할_수_없다() throws PeriodException {
        SessionType type = new SessionType(PayType.PAID, 1000L, 1);
        Session session = new Session(SessionStatus.OPEN, new HashSet<>(List.of(NsUser.GUEST_USER)), type);
        assertThatThrownBy(() -> {
            session.register(new NsUser(), new Payment("id", 1L, 1L, 1000L));
        }).isInstanceOf(MaxStudentsExceedException.class);
    }

    @Test
    void 무료강의의_경우_제한_인원이_없다() throws CannotRegisterException, PeriodException {
        SessionType type = new SessionType(PayType.FREE, 0L , 1);
        Session session = new Session(SessionStatus.OPEN, new HashSet<>(List.of(NsUser.GUEST_USER)), type);
        NsUser nsUser = new NsUser();
        assertThat(session.register(nsUser)).isEqualTo(new Students(Set.of(NsUser.GUEST_USER, nsUser)));
    }

    @Test
    void 유료강의는_가격이_맞지_않으면_살수_없다() throws PeriodException {
        SessionType type = new SessionType(PayType.PAID, 1000L, 1);
        Session session = new Session(SessionStatus.OPEN, new HashSet<>(List.of(NsUser.GUEST_USER)), type);
        assertThatThrownBy(() -> session.register(new NsUser(), new Payment("id", 1L, 1L, 100L)))
                .isInstanceOf(CannotRegisterException.class);
    }

    @Test
    void 유료강의는_가격과_지불이_동일하면_결제된다() throws CannotRegisterException, PeriodException {
        SessionType type = new SessionType(PayType.PAID, 1000L, 3);
        Session session = new Session(SessionStatus.OPEN, new HashSet<>(List.of(NsUser.GUEST_USER)), type);
        NsUser nsUser = new NsUser();
        Students students = new Students(Set.of(NsUser.GUEST_USER, nsUser));
        assertThat(session.register(nsUser, new Payment("id", 1L, 1L, 1000L))).isEqualTo(students);
    }


}