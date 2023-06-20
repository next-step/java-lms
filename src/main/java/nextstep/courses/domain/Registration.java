package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Registration {
    private RegistrationStatus registrationStatus;

    private Students students;

    private Long capacity;

    public Registration(RegistrationStatus registrationStatus, Students students, Long capacity) {
        this.registrationStatus = registrationStatus;
        this.students = students;
        this.capacity = capacity;
    }

    public Registration(Registration registration, List<NsUser> students) {
        this.registrationStatus = registration.registrationStatus;
        this.students = new Students(students);
    }

    public Long register(NsUser user) {
        if (this.registrationStatus != RegistrationStatus.OPEN) {
            throw new IllegalStateException("강의 모집 중이 아닙니다.");
        }
        if (this.students.isGreaterEqualThan(this.capacity)) {
            throw new IllegalStateException("강의가 현재 만석입니다.");
        }
        return this.students.add(user);
    }
}
