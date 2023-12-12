package nextstep.courses.domain.dto;

public class SessionEnrollment {
    private final Integer countOfEnrollments;
    private final Integer limits;

    public SessionEnrollment(){
        this(1, 1);
    }

    public SessionEnrollment(Integer countOfEnrollments, Integer limits) {
        validateCounts(countOfEnrollments, limits);
        this.countOfEnrollments = countOfEnrollments;
        this.limits = limits;
    }

    public boolean isNotEmpty(){
        return countOfEnrollments > 0;
    }

    private void validateCounts(Integer countOfEnrollments, Integer limits){
        if(countOfEnrollments > limits){
            throw new IllegalArgumentException();
        }
    }

    public Integer getCountOfEnrollments() {
        return countOfEnrollments;
    }

    public Integer getLimits() {
        return limits;
    }
}
