package nextstep.session.domain;

import nextstep.session.NotRecruitException;
import nextstep.session.StudentNumberExceededException;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SignUpInformation {

    private ProgressStatus progressStatus;
    private RecruitmentStatus recruitmentStatus;
    private Long maxNumberOfStudent;
    private final List<NsUser> students = new ArrayList<>();

    public SignUpInformation(ProgressStatus progressStatus, Long maxNumberOfStudent) {
        this.progressStatus = progressStatus;
        this.maxNumberOfStudent = maxNumberOfStudent;
    }

    public ProgressStatus getProgressStatus() {
        return progressStatus;
    }

    public Long getMaxNumberOfStudent() {
        return maxNumberOfStudent;
    }

    public List<NsUser> getStudents() {
        return students;
    }

    public void signUp(NsUser user) throws StudentNumberExceededException, NotRecruitException {
        if (!progressStatus.equals(ProgressStatus.PROCEEDING)) {
            throw new NotRecruitException("모집 기간에만 신청 가능합니다.");
        }

        if (students.size() == maxNumberOfStudent) {
            throw new StudentNumberExceededException("최대 수강 인원 수를 초과했습니다.");
        }

        this.students.add(user);
    }
}
