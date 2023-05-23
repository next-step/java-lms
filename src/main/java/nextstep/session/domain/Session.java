package nextstep.session.domain;

import nextstep.session.NotRecruitException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import nextstep.session.StudentNumberExceededException;

public class Session {

    private final Long id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String image;
    private final SessionStatus status;
    private final Long maxNumberOfStudent;
    private final List<NsUser> students = new ArrayList<>();

    public Session(Long id, Long maxNumberOfStudent, SessionStatus status) {
        this.id = id;
        this.maxNumberOfStudent = maxNumberOfStudent;
        this.status = status;
    }

    void signUp(NsUser user) throws StudentNumberExceededException, NotRecruitException {
        if (!status.equals(SessionStatus.RECRUITING)) {
            throw new NotRecruitException("모집 기간에만 신청 가능합니다.");
        }

        if (students.size() == maxNumberOfStudent) {
            throw new StudentNumberExceededException("최대 수강 인원 수를 초과했습니다.");
        }

        this.students.add(user);
    }

}
