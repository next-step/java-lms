package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class SessionRegistration {

    private static final String REGISTER_ERROR_MESSAGE = "수강 신청은 모집중 일때만 신청 가능합니다.";
    private static final String MAX_STUDENTS_COUNT_ERROR_MESSAGE = "최대 수강 신청 인원을 초과할 수 없습니다.";

    private final int maxStudentCount;
    private int currentStudentCount;
    private SessionStatusType sessionStatusType;
    private List<NsUser> nsUsers = new ArrayList<>();

    public SessionRegistration(int maxStudentCount, int currentStudentCount, SessionStatusType sessionStatusType, List<NsUser> nsUsers) {
        this.maxStudentCount = maxStudentCount;
        this.currentStudentCount = currentStudentCount;
        this.sessionStatusType = sessionStatusType;
        this.nsUsers = nsUsers;
    }

    public void register(NsUser nsUser) {
        this.validationSessionStatus();
        this.validationSessionMaxStudentCount(1);

        this.nsUsers.add(nsUser);
        this.currentStudentCount += 1;
    }

    public List<NsUser> getNsUsers() {
        return this.nsUsers;
    }

    private void validationSessionStatus() {
        if (this.sessionStatusType != SessionStatusType.RECRUITING) {
            throw new IllegalArgumentException(REGISTER_ERROR_MESSAGE);
        }
    }

    private void validationSessionMaxStudentCount(int studentCount) {
        if (this.currentStudentCount + studentCount > this.maxStudentCount) {
            throw new IllegalArgumentException(MAX_STUDENTS_COUNT_ERROR_MESSAGE);
        }
    }
}
