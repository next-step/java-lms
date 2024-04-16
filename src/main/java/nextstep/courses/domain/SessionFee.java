package nextstep.courses.domain;

public class SessionFee {

    private long fee;

    public SessionFee(long fee) {
        this.fee = fee;
    }

    public boolean hasPaid(Long fee) {
        return this.fee == fee;
    }


}
