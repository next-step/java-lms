package nextstep.session.domain;

import nextstep.session.NotRecruitException;
import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SignUpInformation {

    private SessionStatus status;
    private Long maxNumberOfStudent;
    private final List<NsUser> students = new ArrayList<>();

    public SignUpInformation(SessionStatus status, Long maxNumberOfStudent) {
        this.status = status;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public Long getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public void signUp(NsUser user) throws StudentNumberExceededException, NotRecruitException {
        if (!status.equals(SessionStatus.RECRUITING)) {
            throw new NotRecruitException("모집 기간에만 신청 가능합니다.");
        }

        if (students.size() == maxNumberOfStudent) {
            throw new StudentNumberExceededException("최대 수강 인원 수를 초과했습니다.");
        }

        this.students.add(user);
    }
}
