package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

public class Recruitment {
    private final RecruitmentStatus status;
    private final Applicants applicants;

    public Recruitment(RecruitmentStatus status, Applicants applicants) {
        this.status = status;
        this.applicants = applicants;
    }

    public void apply(NsUser user, Session session, Long price) {
        if (status.isNotSupport()) {
            throw new IllegalArgumentException("session is not open");
        }

        applicants.apply(user, session, price);
    }

    public void select(NsUser user) {
        applicants.select(user);
    }

    public int select(SelectStrategy strategy) {
        return applicants.select(strategy);
    }

    public void approve(NsUser user) {
        applicants.approve(user);
    }

    public void cancel(NsUser user) {
        applicants.cancel(user);
    }

    public RecruitmentStatus getStatus() {
        return status;
    }

    public Applicants getStudents() {
        return applicants;
    }

    public ApplicantStatus getStudentStatus(NsUser user) {
        return applicants.getApplicantStatus(user);
    }
}
