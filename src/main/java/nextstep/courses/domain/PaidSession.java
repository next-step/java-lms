package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    public PaidSession(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, price, startDate, endDate);
    }

    @Override
    public void enroll(final Payment payment) {
        //  강의 상태 - 모집중
        if (isNotRecruiting()) {
            throw new IllegalStateException("session is not recruiting");
        }

        //  유료 강의의 경우, 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.

        //  최대 수강 인원

    }
}
