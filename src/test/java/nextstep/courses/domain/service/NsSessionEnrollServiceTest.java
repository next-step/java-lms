package nextstep.courses.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.constant.SessionFee;
import nextstep.courses.domain.entity.NsSession;
import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

class NsSessionEnrollServiceTest {

    Payment payment;
    NsSessionEnrollService service;

    @BeforeEach
    void init() {
        payment = new Payment(String.valueOf(1L), 1L, 1L, SessionFee.A.getFee());
        service = new NsSessionEnrollService();
    }

    @Test
    void 수강신청_테스트() {
        NsUser nsUser = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsSession paidOpen = NsSession.paidOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               SessionType.PAID.getType(),
                                               SessionStatus.OPEN.getType(),
                                               LocalDate.now(),
                                               LocalDate.now().plusDays(30),
                                               SessionFee.A.getFee());

        assertThat(service.enroll(paidOpen, nsUser, payment)).isNotNull();
    }

    @Test
    void 강의가_대기_상태_일_때_수강신청_실패_테스트() {
        NsUser nsUser = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsSession paidWait = NsSession.paidOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               SessionType.PAID.getType(),
                                               SessionStatus.WAIT.getType(),
                                               LocalDate.now(),
                                               LocalDate.now().plusDays(30),
                                               SessionFee.A.getFee());
        try {
            service.enroll(paidWait, nsUser, payment);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "신청인원을 초과하거나, 수강료와 결제내역이 일치하지 않습니다");
        }
    }

    @Test
    void 강의가_종료_상태_일_때_수강신청_실패_테스트() {
        NsUser nsUser = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsSession paidClosed = NsSession.paidOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               SessionType.PAID.getType(),
                                               SessionStatus.CLOSED.getType(),
                                               LocalDate.now(),
                                               LocalDate.now().plusDays(30),
                                               SessionFee.A.getFee());

        try {
            service.enroll(paidClosed, nsUser, payment);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "신청인원을 초과하거나, 수강료와 결제내역이 일치하지 않습니다");
        }
    }

    @Test
    void 유료강의_신청가능_인원을_초과하는_경우_수강신청_실패_테스트() {
        NsUser nsUser = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsSession paid = NsSession.paidOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               SessionType.PAID.getType(),
                                               SessionStatus.OPEN.getType(),
                                               LocalDate.now(),
                                               LocalDate.now().plusDays(30),
                                               SessionFee.A.getFee());



        try {
            for (int i = 0; i < NsSession.MAX_PAID_QUOTA; i++) {
                System.out.println(paid); // print quota
                service.enroll(paid, nsUser, payment);
            }
        } catch (Exception e) {
            assertEquals(e.getMessage(), "신청인원을 초과하거나, 수강료와 결제내역이 일치하지 않습니다");
        }
    }

    @Test
    void 유료강의_결제금액이_일치하지_않는_경우_수강신청_실패_테스트() {
        NsUser nsUser = new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net");
        NsSession paid = NsSession.paidOf(1L,
                                               CoverImage.DEFAULT_IMAGE,
                                               SessionType.PAID.getType(),
                                               SessionStatus.OPEN.getType(),
                                               LocalDate.now(),
                                               LocalDate.now().plusDays(30),
                                               SessionFee.C.getFee());

        try {
            System.out.println("payment - " + payment);
            System.out.println("paid - " + paid);
            service.enroll(paid, nsUser, payment);
        } catch (Exception e) {
            assertEquals(e.getMessage(), "신청인원을 초과하거나, 수강료와 결제내역이 일치하지 않습니다");
        }
    }
}
