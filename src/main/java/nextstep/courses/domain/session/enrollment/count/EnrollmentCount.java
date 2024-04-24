package nextstep.courses.domain.session.enrollment.count;

public interface EnrollmentCount {

    boolean isRegistrationWithinCapacity();

    void addRegistrationCount();
}
