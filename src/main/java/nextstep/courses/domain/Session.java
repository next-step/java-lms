package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import java.time.LocalDateTime;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Session {

    private final Long id;
    private final String title;
    private final int generation;
    private final DateTime dateTime;
    private final SessionType type;
    private final SessionStatus status;
    private final int maxRegisterCount;
    private final NsUsers students = new NsUsers();

    public Session(Long id, String title, int generation, DateTime dateTime, SessionType type, SessionStatus status, int maxRegisterCount) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.dateTime = dateTime;
        this.type = type;
        this.status = status;
        this.maxRegisterCount = maxRegisterCount;
    }

    public Session(Long id, String title, int generation, LocalDateTime startAt, LocalDateTime endAt, SessionType type, SessionStatus status, int maxRegisterCount) {
        this(id, title, generation, new DateTime(startAt, endAt), type, status, maxRegisterCount);
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
        if (isOutOfRecruitingDate()) {
            throw new CannotRegisterException("모집일이 아닙니다.");
        }
        if (isExceedMaxRegisterCount()) {
            throw new CannotRegisterException("등록 인원이 정원 초과 되었습니다.");
        }
        return true;
    }

    private boolean isNotRecruiting() {
        return RECRUITING != status;
    }

    private boolean isOutOfRecruitingDate() {
        LocalDateTime now = LocalDateTime.now();
        return !(now.isAfter(this.dateTime.startAt()) && now.isBefore(this.dateTime.endAt()));
    }

    private boolean isExceedMaxRegisterCount() {
        return students.size() >= maxRegisterCount;
    }

    int currRegisterCount() {
        return this.students.size();
    }

}
