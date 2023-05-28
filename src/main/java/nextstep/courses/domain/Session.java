package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Session {

    private final Long id;
    private final String title;
    private final int generation;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final SessionType type;
    private final SessionStatus status;
    private final int maxRegisterNum;
    private List<NsUser> students = new ArrayList<>();

    public Session(Long id, String title, int generation, LocalDate startDate, LocalDate endDate, SessionType type, SessionStatus status, int maxRegisterNum) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.maxRegisterNum = maxRegisterNum;
    }

    public void addStudent(NsUser nsUser) {
        if (isRegistrable()) {
            students.add(nsUser);
        }
    }

    private boolean isRegistrable() {
        if (isNotRecruiting()) {
            throw new RuntimeException("강의 모집기간이 아닙니다.");
        }
        if (isCurrRegisterExceedMaxRegister()) {
            throw new RuntimeException("등록 인원이 정원 초과 되었습니다.");
        }
        return true;
    }

    private boolean isNotRecruiting() {
        return RECRUITING != status;
    }

    private boolean isCurrRegisterExceedMaxRegister() {
        return students.size() >= maxRegisterNum;
    }

    Long id() {
        return this.id;
    }

    int currRegisterNum() {
        return this.students.size();
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", generation=" + generation +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", type=" + type +
                ", status=" + status +
                ", maxRegisterNum=" + maxRegisterNum +
                ", students=" + students +
                '}';
    }
}
