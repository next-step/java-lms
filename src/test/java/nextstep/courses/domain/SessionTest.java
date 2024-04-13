package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @DisplayName("수강 상태를 변경한다.")
    @Test
    void test01() {
        Session session = new FreeSession(0L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.FREE);
        session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
        assertThat(session.isRecruitmentStatus(RecruitmentStatus.RECRUITING)).isTrue();
    }

    @DisplayName("전체 수강생을 확인한다.")
    @Test
    void test02() {
        Session session = new FreeSession(0L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.FREE);
        session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
        Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 0L);
        session.enroll(NsUserTest.JAVAJIGI, payment);
        assertThat(session.totalStudents()).containsExactlyInAnyOrder(NsUserTest.JAVAJIGI);
    }

    @DisplayName("현재 모집 중인 강의가 아닌 강의에 수강신청할 경우 예외가 발생한다.")
    @Test
    void test03() {
        Session session = new FreeSession(0L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.FREE);
        Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 0L);
        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, payment))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("현재 모집 중인 강의가 아닙니다.");
    }

    @Nested
    class FreeSessionTest {

        @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
        @Test
        void test03() {
            FreeSession session = new FreeSession(0L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.FREE);
            session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
            assertThatCode(() -> {
                for (int i = 0; i < 10_000; i++) {
                    NsUser user = new NsUser((long) i, String.valueOf(i), "password", "name", "test@email.com");
                    Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 0L);
                    session.enroll(user, payment);
                }
            }).doesNotThrowAnyException();
        }
    }

    @Nested
    class PaidSessionTest {

        @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
        @Test
        void test04() {
            Session session = new PaidSession(0L, 1, 10_000L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.PAID);
            session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
            assertThatThrownBy(() -> {
                Payment payment1 = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 10_000L);
                session.enroll(NsUserTest.JAVAJIGI, payment1);
                Payment payment2 = new Payment(0L, NsUserTest.SANJIGI.getId(), 10_000L);
                session.enroll(NsUserTest.SANJIGI, payment2);
            })
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최대 수강인원을 초과하였습니다.");
        }

        @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않는 경우 예외가 발생한다.")
        @Test
        void test05() {
            Session session = new PaidSession(0L, 1, 9_000L, LocalDateTime.now(), LocalDateTime.now().plusDays(1), SessionCoverImageTest.CI, SessionType.PAID);
            session.changeRecruitmentStatus(RecruitmentStatus.RECRUITING);
            Payment payment = new Payment(0L, NsUserTest.JAVAJIGI.getId(), 10_000L);
            assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, payment))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("결제 금액과 수강료가 일치하지 않습니다.");
        }
    }
}
