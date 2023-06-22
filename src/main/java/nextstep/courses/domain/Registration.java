package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class Registration {
    private RegistrationStatus registrationStatus;

    private Students approvedStudents;

    private Students waitStudents;

    private Long capacity;

    public Registration(RegistrationStatus registrationStatus, Long capacity) {
        this(registrationStatus, new Students(), new Students(), capacity);
    }

    public Registration(Registration registration, List<NsUser> approvedStudents, List<NsUser> waitStudents, Long capacity) {
        this.registrationStatus = registration.registrationStatus;
        this.approvedStudents = new Students(approvedStudents);
        this.waitStudents = new Students(waitStudents);
        this.capacity = capacity;
    }

    public Registration(RegistrationStatus registrationStatus, Students approvedStudents, Students waitStudents, Long capacity) {
        this.registrationStatus = registrationStatus;
        this.approvedStudents = approvedStudents;
        this.waitStudents = waitStudents;
        this.capacity = capacity;
    }

    public Long register(NsUser user) {
        if (this.registrationStatus != RegistrationStatus.OPEN) {
            throw new IllegalStateException("강의 모집 중이 아닙니다.");
        }
        if (this.approvedStudents.isGreaterEqualThan(this.capacity)) {
            throw new IllegalStateException("강의가 현재 만석입니다.");
        }
        if (this.approvedStudents.contains(user)) {
            throw new IllegalStateException("이미 승인되었습니다.");
        }
        return this.waitStudents.add(user);
    }

    public Long approve(NsUser user) {
        if (this.approvedStudents.isGreaterEqualThan(this.capacity)) {
            throw new IllegalStateException("강의가 현재 만석입니다.");
        }
        this.waitStudents.remove(user);
        return this.approvedStudents.add(user);
    }

    public Long disapprove(NsUser user) {
        return this.waitStudents.remove(user);
    }

    public Long getCapacity() {
        return capacity;
    }
}
