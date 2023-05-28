package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDate;

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
    private final NsUsers students = new NsUsers();

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

    public void register(NsUser nsUser) {
        if (isRegistrable()) {
            students.register(nsUser);
        }
    }

    private boolean isRegistrable() {
        if (isNotRecruiting()) {
            throw new CannotRegisterException("강의 모집기간이 아닙니다.");
        }
        if (isCurrRegisterExceedMaxRegister()) {
            throw new CannotRegisterException("등록 인원이 정원 초과 되었습니다.");
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
