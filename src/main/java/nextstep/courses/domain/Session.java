package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Session {
    private final SessionImage sessionImage;
    private SessionStatus sessionStatus;
    private final Set<NsUser> students = new HashSet<>();

    public Session(SessionImage sessionImage, SessionStatus sessionStatus) {
        this.sessionImage = sessionImage;
        this.sessionStatus = sessionStatus;
    }

    public void enrollmentUser(NsUser user) {

        if (!sessionStatus.isRecruit()) {
            throw new NotRecruitException();
        }

        students.add(user);
    }

    public Set<NsUser> getStudents() {
        return new HashSet<>(students);
    }
}
