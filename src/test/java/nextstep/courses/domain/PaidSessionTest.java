package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class PaidSessionTest {

    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
    @Test
    void test01() {
        Session session = new PaidSession(1, 10000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        session.changeStatus(SessionStatus.RECRUITING);
        assertThatThrownBy(() -> session.registers(List.of(NsUserTest.JAVAJIGI)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 수강인원을 초과하였습니다.");
    }

    @DisplayName("수강 신청은 강의 상태가 모집 중일 때만 가능하다.")
    @Test
    void test02() {
        Session session = new PaidSession(1, 10000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        session.changeStatus(SessionStatus.RECRUITING);
        assertThatCode(() -> session.register(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @DisplayName("수강생이 결제한 금액과 수강료가 일치하지 않는 경우 예외가 발생한다.")
    @Test
    void test03() {
        Payment payment = new Payment("1", 2L, NsUserTest.JAVAJIGI.getId(), 10000L);
        PaidSession session = new PaidSession(1, 9000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        session.changeStatus(SessionStatus.RECRUITING);
        assertThatThrownBy(() -> session.register(NsUserTest.JAVAJIGI, payment))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
    }

    @DisplayName("전체 수강생을 확인한다.")
    @Test
    void test04() {
        PaidSession session = new PaidSession(2, 10_000, LocalDateTime.now(), LocalDateTime.now().plusDays(1));
        session.changeStatus(SessionStatus.RECRUITING);
        session.registers(List.of(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI));
        List<NsUser> students = session.totalStudents();
        assertThat(students).containsExactlyInAnyOrder(NsUserTest.JAVAJIGI, NsUserTest.SANJIGI);
    }
}
