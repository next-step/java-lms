package nextstep.courses.domain.session.enrollment.count.engine;

public interface EnrollmentCount {

    boolean isRegistrationWithinCapacity();

    void addRegistrationCount();
}
