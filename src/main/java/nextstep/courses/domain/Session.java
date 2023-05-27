package nextstep.courses.domain;

import nextstep.courses.exception.ExceedingMaximumStudentException;
import nextstep.courses.exception.NotEligibleRegistrationStatusException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private final SessionDuration duration;
    private final SessionCoverImage coverImage;
    private final SessionPaymentType paymentType;
    private final SessionStatus status;
    private final List<NsUser> students;
    private final int maximumStudent;

    public Session(SessionDuration duration,
                   SessionCoverImage coverImage,
                   SessionPaymentType paymentType,
                   SessionStatus status,
                   List<NsUser> students,
                   int maximumStudent) {
        this.duration = duration;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.status = status;
        this.students = students;
        this.maximumStudent = maximumStudent;
    }

    public Session(SessionDuration duration,
                   SessionCoverImage coverImage,
                   SessionPaymentType paymentType,
                   SessionStatus status,
                   int maximumStudent) {
        this(duration, coverImage, paymentType, status, new ArrayList<>(), maximumStudent);
    }

    public void register(NsUser student) {
        if (students.size() >= maximumStudent) {
            throw new ExceedingMaximumStudentException();
        }
        if (!status.isRecruiting()) {
            throw new NotEligibleRegistrationStatusException();
        }
        addStudent(student);
    }

    private void addStudent(NsUser student) {
        students.add(student);
    }

    public SessionDuration getDuration() {
        return duration;
    }

    public SessionCoverImage getCoverImage() {
        return coverImage;
    }

    public SessionPaymentType getPaymentType() {
        return paymentType;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public int getMaximumStudent() {
        return maximumStudent;
    }
}
