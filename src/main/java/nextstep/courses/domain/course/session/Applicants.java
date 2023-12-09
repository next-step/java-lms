package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applicants implements Iterable<NsUser> {
    private final List<NsUser> applicants;

    private final int quota;

    public Applicants(int quota) {
        this(new ArrayList<>(), quota);
    }

    public Applicants(List<NsUser> applicants, int quota) {
        this.applicants = applicants;
        this.quota = quota;
    }

    public int size() {
        return this.applicants.size();
    }

    public void addChargedApplicant(NsUser applicant) {
        checkApplicantAlreadyExisted(applicant);
        checkChargeAndApplySizeIsValid();
        this.applicants.add(applicant);
    }

    public void addFreeApplicant(NsUser applicant) {
        checkApplicantAlreadyExisted(applicant);
        this.applicants.add(applicant);
    }

    private void checkApplicantAlreadyExisted(NsUser applicant) {
        if (this.applicants.contains(applicant)) {
            throw new IllegalArgumentException("이미 강의를 신청하였습니다.");
        }
    }

    private void checkChargeAndApplySizeIsValid() {
        if (this.isFull()) {
            throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
        }
    }

    public boolean isFull() {
        return this. applicants.size() == this.quota;
    }

    @Override
    public Iterator<NsUser> iterator() {
        return this.applicants.iterator();
    }
}
