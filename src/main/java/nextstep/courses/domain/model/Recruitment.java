package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Recruitment {
    private final RecruitmentStatus status;
    private final Students students;
    private final Set<NsUser> applicants;
    private final Set<NsUser> selected;
    private final Set<NsUser> canceled;


    public Recruitment(RecruitmentStatus status, Students students) {
        this(status, students, new HashSet<>(), new HashSet<>());
    }

    public Recruitment(RecruitmentStatus status, Students students, Set<NsUser> applicants, Set<NsUser> selected) {
        this.status = status;
        this.students = students;
        this.applicants = applicants;
        this.selected = selected;
        this.canceled = new HashSet<>();
    }

    public RecruitmentStatus getStatus() {
        return status;
    }

    public void apply(NsUser user) {
        if (status.isNotSupport()) {
            throw new IllegalArgumentException("session is not open");
        }

        if (isAlreadyApplied(user)) {
            throw new IllegalArgumentException("already applied");
        }

        applicants.add(user);
    }

    private boolean isAlreadyApplied(NsUser user) {
        return getStudentStatus(user) != StudentStatus.NOT_APPLIED;
    }


    public int select(List<NsUser> users) {
        if (!applicants.containsAll(users)) {
            throw new IllegalArgumentException("not an applicant");
        }
        applicants.removeAll(users);
        selected.addAll(users);
        return users.size();
    }

    public int select(SelectStrategy strategy) {
        List<NsUser> users = applicants.stream()
                .filter(user -> strategy.isSelected())
                .limit(students.getRemain() - selected.size())
                .collect(Collectors.toList());

        applicants.removeAll(users);
        selected.addAll(users);
        return users.size();
    }

    public Student enroll(NsUser user, Session session, Long price) {
        selected.remove(user);

        return students.register(user, session, price);
    }

    public Student approve(NsUser user, Session session, Long price) {
        if (!selected.contains(user)) {
            throw new IllegalArgumentException("not selected");
        }

        return enroll(user, session, price);
    }

    public void cancel(NsUser user) {
        if (selected.contains(user)) {
            throw new IllegalArgumentException("not an applicant");
        }

        if (!applicants.contains(user)) {
            throw new IllegalArgumentException("not an applicant");
        }

        applicants.remove(user);
        canceled.add(user);
    }

    public Payment getPayment(NsUser user, Session session, Long price) {
        if (!students.include(user)) {
            throw new IllegalArgumentException("student not found");
        }
        return new Payment("0L", session.getId(), user.getId(), price);
    }

    public Students getStudents() {
        return students;
    }

    public StudentStatus getStudentStatus(NsUser user) {
        if (applicants.contains(user)) {
            return StudentStatus.APPLIED;
        }
        if (selected.contains(user)) {
            return StudentStatus.SELECTED;
        }
        if (students.include(user)) {
            return StudentStatus.APPROVED;
        }
        if (canceled.contains(user)) {
            return StudentStatus.CANCELLED;
        }

        return StudentStatus.NOT_APPLIED;
    }
}
