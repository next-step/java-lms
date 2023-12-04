package nextstep.sessions.domain;

public class SessionCharge {

    // 무료/유료 판단
    private boolean charge;

    // 가격
    private double price;

    // 인원 수
    private int limitStudents;

    public SessionCharge(boolean charge, double price, int limitStudents) {
        this.charge = charge;
        if (!charge) {
            this.price = 0;
            this.limitStudents = 0;
            return;
        }
        if (price <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강료가 0원 이하일 수 없습니다.");
        }
        if (limitStudents <= 0) {
            throw new IllegalArgumentException("유료 강의는 수강 인원에 제한 있습니다.");
        }
        this.price = price;
        this.limitStudents = limitStudents;
    }
}
