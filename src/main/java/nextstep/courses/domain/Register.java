package nextstep.courses.domain;

import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;

import static nextstep.courses.domain.SessionStatus.RECRUITING;

public class Register {

    private final SessionStatus status;
    private final int maxRegisterCount;
    private final NsUsers students;

    public Register(SessionStatus status, int maxRegisterCount, NsUsers students) {
        this.status = status;
        this.maxRegisterCount = maxRegisterCount;
        this.students = students;
    }

    public NsUsers add(NsUser nsUser) {
        if (isAddable()) {
            students.add(nsUser);
        }
        return this.students;
    }

    private boolean isAddable() {
        if (isNotRecruiting()) {
            throw new CannotRegisterException("강의 모집기간이 아닙니다.");
        }
        if (isExceedMaxRegisterCount()) {
            throw new CannotRegisterException("등록 인원이 정원 초과 되었습니다.");
        }
        return true;
    }

    private boolean isNotRecruiting() {
        return RECRUITING != status;
    }

    private boolean isExceedMaxRegisterCount() {
        return students.size() >= maxRegisterCount;
    }

    public NsUsers students() {
        return this.students;
    }

    @Override
    public String toString() {
        return "Register{" +
                "status=" + status +
                ", maxRegisterCount=" + maxRegisterCount +
                ", students=" + students +
                '}';
    }
}
