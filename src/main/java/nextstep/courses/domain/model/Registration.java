package nextstep.courses.domain.model;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Registration {
    private final RegistrationStatus status;
    private final Set<Applicant> applicants;
    private final int capacity;

    public Registration(int capacity) {
        this(RegistrationStatus.OPEN, new HashSet<>(), capacity);
    }

    public Registration(int capacity, Set<Applicant> applicants) {
        this(RegistrationStatus.OPEN, applicants, capacity);
    }

    public Registration(RegistrationStatus status, Set<Applicant> applicants, int capacity) {
        validateCapacity(capacity);
        this.status = status;
        this.applicants = applicants;
        this.capacity = capacity;
    }

    private void validateCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity must be greater than 0");
        }
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRemain() {
        return capacity - findApplicantsByStatus(ApplicantStatus.APPROVED).size() - findApplicantsByStatus(ApplicantStatus.SELECTED).size();
    }

    public void apply(NsUser user, Long sessionId, Long price) {
        if (status.isNotSupport()) {
            throw new IllegalArgumentException("session is not open");
        }

        Optional<Applicant> applicant = findApplicantsByUser(user);
        if (applicant.isPresent()) {
            throw new IllegalArgumentException("applicant already exists");
        }
        user.pay(price);
        Payment payment = new Payment(sessionId, user.getId(), price);
        applicants.add(new Applicant(user, sessionId, payment));
    }

    public void select(NsUser user) {
        Optional<Applicant> applicant = findApplicantsByUser(user);
        if (applicant.isEmpty() || !applicant.get().isSameStatus(ApplicantStatus.APPLIED)) {
            throw new IllegalArgumentException("not applicant");
        }

        if (getRemain() <= 0) {
            throw new IllegalArgumentException("remain must be greater than 0");
        }

        applicant.get().makeStatus(ApplicantStatus.SELECTED);
    }

    public int select(SelectStrategy strategy) {
        List<Applicant> st = applicants.stream()
                .filter(student -> student.isSameStatus(ApplicantStatus.APPLIED))
                .filter(student -> strategy.isSelected())
                .limit(getRemain())
                .collect(Collectors.toList());

        st.forEach(student -> student.makeStatus(ApplicantStatus.SELECTED));
        return st.size();
    }

    public void approve(NsUser user) {
        Optional<Applicant> st = findApplicantsByUser(user);
        if (st.isEmpty() || !st.get().isSameStatus(ApplicantStatus.SELECTED)) {
            throw new IllegalArgumentException("not selected");
        }

        st.get().makeStatus(ApplicantStatus.APPROVED);
    }

    public void cancel(NsUser user) {
        Optional<Applicant> st = findApplicantsByUser(user);
        if (st.isEmpty()) {
            throw new IllegalArgumentException("user not applicant");
        }

        Applicant applicant = st.get();
        if (!applicant.isSameStatus(ApplicantStatus.APPLIED)) {
            throw new IllegalArgumentException("not an appropriate applicant");
        }

        applicant.makeStatus(ApplicantStatus.CANCELLED);
    }

    public ApplicantStatus getApplicantStatus(NsUser user) {
        return findApplicantsByUser(user)
                .map(Applicant::getStatus)
                .orElse(ApplicantStatus.NOT_APPLIED);
    }

    private Optional<Applicant> findApplicantsByUser(NsUser user) {
        return applicants.stream()
                .filter(student -> student.isEqualTo(user))
                .findAny();
    }

    private List<Applicant> findApplicantsByStatus(ApplicantStatus status) {
        return applicants.stream()
                .filter(student -> student.isSameStatus(status))
                .collect(Collectors.toList());
    }

    public RegistrationStatus getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Applicants{" +
                "capacity=" + capacity +
                ", applicants=" + applicants +
                '}';
    }

}
