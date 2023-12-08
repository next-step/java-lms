package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applicants implements Iterable<NsUser> {
    private final List<NsUser> applicants;

    public Applicants() {
        this(new ArrayList<NsUser>());
    }

    public Applicants(List<NsUser> applicants) {
        this.applicants = applicants;
    }

    public int size() {
        return this.applicants.size();
    }

    public void add(NsUser applicant) {
        checkApplicantAlreadyExisted(applicant);
        this.applicants.add(applicant);
    }

    private void checkApplicantAlreadyExisted(NsUser applicant) {
        if (this.applicants.contains(applicant)) {
            throw new IllegalArgumentException("이미 강의를 신청하였습니다.");
        }
    }

    @Override
    public Iterator<NsUser> iterator() {
        return this.applicants.iterator();
    }
}
