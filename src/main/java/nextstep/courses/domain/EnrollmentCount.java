package nextstep.courses.domain;

public class EnrollmentCount {

    private final int count;

    public EnrollmentCount(int count) {
        if(count < 0){
            throw new IllegalArgumentException("수강인원은 0이상이어야 합니다.");
        }
        this.count = count;
    }

    public boolean hasRemainingCount() {
        return !(count == 0);
    }
}
