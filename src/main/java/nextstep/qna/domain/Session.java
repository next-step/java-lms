package nextstep.qna.domain;

public interface Session {
    void recruit(); // SessionStatus를 recruit으로 변경

    void end(); // SessionStatus를 end로 변경

    boolean checkForRegister(long paidAmount); // 수강 신청 가능 여부
}
