package nextstep.qna.domain;

import nextstep.payments.domain.Payment;

public interface Session {
    void recruit(); // SessionStatus를 recruit으로 변경

    void end(); // SessionStatus를 end로 변경

    boolean isPossibleToRegister(Payment payment); // 수강 신청 가능 여부
}
