package nextstep.payments.service;

import nextstep.payments.domain.Payment;
import nextstep.qna.domain.CoverImage;
import nextstep.qna.domain.PaidSession;
import nextstep.qna.domain.Pixel;
import nextstep.qna.domain.Session;
import nextstep.qna.domain.session.SessionStatus;

public class PaymentService {
    public static final Session TEMPORARY_SESSION = PaidSession.of("2023-12-03T00:00:00", "2024-01-03T00:00:00", CoverImage.of("dd", 1024 * 50, "gif", Pixel.of(300), Pixel.of(200)), SessionStatus.RECRUITING, 50, 0, 100000);


    public Payment payment(String id) {
        // PG사 API를 통해 id에 해당하는 결제 정보를 반환
        return new Payment();
    }

    public void signUpSession(Payment payment) {
        // 결제 정보를 통해 수강 신청
//        Session session = SessionRepository.findById(payment.getSessionId());
        Session session = TEMPORARY_SESSION;
        if (session.isPossibleToRegister(payment)) {
            // 수강 신청 성공 후 로직 구현
        }
    }
}
