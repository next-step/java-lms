package nextstep.courses.service;

import nextstep.courses.domain.session.Image;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidPaymentAmountException;
import nextstep.courses.exception.session.InvalidProgressStateException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @Test
    @DisplayName("유료, 무료 강의를 생성한다.")
    @Transactional
    public void save() {
        Session paidSession = Session.ofPaid(Period.from(), Image.from(), 1_000L, 100);
        Session freeSession = Session.ofFree(Period.from(), Image.from());


        assertThat(sessionService.save(paidSession)).isNotEqualTo(0);
        assertThat(sessionService.save(freeSession)).isNotEqualTo(0);
    }

    @Test
    @DisplayName("강의를 조회한다, 강의정보, 이미지, 수강생 목록이 포함되어 있다.")
    public void findById() {
        Session session = sessionService.findById(1000L);

        assertThat(session.id()).isEqualTo(1000L);
        assertThat(session.images().size()).isEqualTo(1);
        assertThat(session.students().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("모집중이 아니면 강의 신청이 불가능하다")
    public void no_recruiting() {
        Session session = 유료_강의_생성();
        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, new Payment(1000L))).isInstanceOf(InvalidProgressStateException.class);
    }

    private Session 유료_강의_생성() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1);
        long savedId = sessionService.save(session);
        return sessionService.findById(savedId);
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다.")
    public void enrollment_max_exceeded() {
        Session session = 유료_강의_생성_및_모집중();
        sessionService.enroll(session, NsUserTest.SANJIGI, new Payment(1000L));
        assertThatThrownBy(() -> sessionService.enroll(session, NsUserTest.JAVAJIGI, new Payment(1000L))).isInstanceOf(EnrollmentMaxExceededException.class);
    }

    private Session 유료_강의_생성_및_모집중() {
        Session session = 유료_강의_생성();
        session.ongoing();
        session.recruiting();
        return session;
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    public void invalid_payment_amount() {
        Session session = 유료_강의_생성_및_모집중();
        assertThatThrownBy(() -> sessionService.enroll(session, NsUserTest.JAVAJIGI, new Payment(900L))).isInstanceOf(InvalidPaymentAmountException.class);
    }

    @Test
    @DisplayName("무료 강의는 수강 인원 제한이 없다.")
    public void no_limit_enroll() {
        Session session = 무료_강의_생성_및_모집중();
        sessionService.enroll(session, NsUserTest.JAVAJIGI, null);
        sessionService.enroll(session, NsUserTest.SANJIGI, null);
    }

    private Session 무료_강의_생성_및_모집중() {
        Session session = Session.ofFree(Period.from(), Image.from());
        long savedId = sessionService.save(session);
        Session findSession = sessionService.findById(savedId);
        findSession.ongoing();
        findSession.recruiting();
        return findSession;
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발된 인원에 대해서만 수강 승인이 가능하다. 수강생이 존재하지 않는 경우 예외가 발생한다.")
    public void no_exist_student() {
        assertThatThrownBy(() -> sessionService.approval(1000L, new NsUser())).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sessionService.cancel(1000L, new NsUser())).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("준비 상태에서만 수강 승인,취소가 가능하다")
    public void validate_approval() {
        sessionService.approval(1000L, NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> sessionService.approval(1000L, NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> sessionService.cancel(1000L, NsUserTest.JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}
