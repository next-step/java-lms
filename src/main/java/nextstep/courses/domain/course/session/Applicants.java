package nextstep.courses.domain.course.session;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Applicants implements Iterable<NsUser> {
    private final List<NsUser> applicants;

    public Applicants() {
        this(new ArrayList<>());
    }

    public Applicants(List<NsUser> applicants) {
        this.applicants = applicants;
    }

    public NsUser find(int index) {
        return this.applicants.get(index);
    }

    public int size() {
        return this.applicants.size();
    }

    public void addApplicant(NsUser applicant, SessionState sessionState) {
        checkApplicantAlreadyExisted(applicant);
        checkChargedAndApplySizeIsValid(sessionState);
        this.applicants.add(applicant);
    }

    private void checkApplicantAlreadyExisted(NsUser applicant) {
        if (this.applicants.contains(applicant)) {
            throw new IllegalArgumentException("이미 강의를 신청하였습니다.");
        }
    }

    private void checkChargedAndApplySizeIsValid(SessionState sessionState) {
        if (sessionState.chargedAndFull(applicants)) {
            throw new IllegalArgumentException("수강 인원은 정원을 초과할 수 없습니다.");
        }
    }

    @Override
    public Iterator<NsUser> iterator() {
        return this.applicants.iterator();
    }
}
