package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Registration {
    private RegistrationStatus registrationStatus;

    private Students students;

    private Long capacity;

    public Registration(RegistrationStatus registrationStatus, Students students, Long capacity) {
        this.registrationStatus = registrationStatus;
        this.students = students;
        this.capacity = capacity;
    }

    public void register(NsUser user) {
        if (this.registrationStatus != RegistrationStatus.OPEN) {
            throw new IllegalStateException("강의 모집 중이 아닙니다.");
        }
        if (this.students.isGreaterEqualThan(this.capacity)) {
            throw new IllegalStateException("강의가 현재 만석입니다.");
        }
        this.students.add(user);
    }
}
