package nextstep.courses.domain;

import nextstep.courses.code.SessionStatus;
import nextstep.courses.code.SessionType;

import java.util.ArrayList;
import java.util.List;

public class Session {

    private static final String SIGN_UP_HISTORIES_SIZE_ERROR_MESSAGE = "최대 수강 인원을 초과할 수 없습니다.";
    private static final String STATUS_ERROR_MESSAGE = "강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.";

    private final Long id;

    private final SessionEssentialInfo essentialInfo;

    private final SessionType type;

    private final SessionStatus status;

    private final List<SignUpHistory> signUpHistories = new ArrayList<>();

    private final SessionPeriod period;

    private Course course;


    public Session(Course course, SessionEssentialInfo essentialInfo, SessionType type, SessionStatus status, SessionPeriod period) {
        this(0L, course, essentialInfo, type, status, period);
    }

    public Session(Long id, Long courseId, SessionEssentialInfo essentialInfo, SessionType type, SessionStatus status, SessionPeriod period) {
        this(id, new Course(courseId), essentialInfo, type, status, period);
    }

    public Session(Long id, Course course, SessionEssentialInfo essentialInfo, SessionType type, SessionStatus status, SessionPeriod period) {
        this.id = id;
        this.course = course;
        this.essentialInfo = essentialInfo;
        this.type = type;
        this.status = status;
        this.period = period;
    }

    public void addSignUpHistory(SignUpHistory signUpHistory) {
        validStatus();
        signUpHistory.toSession(this);
        signUpHistories.add(signUpHistory);
        validSignUpHistoriesSize();
    }

    private void validStatus() {
        if (SessionStatus.RECRUIT != this.status) {
            throw new IllegalArgumentException(STATUS_ERROR_MESSAGE);
        }
    }

    private void validSignUpHistoriesSize() {
        if (this.signUpHistories.size() > essentialInfo.getHeadCount()) {
            throw new IllegalArgumentException(SIGN_UP_HISTORIES_SIZE_ERROR_MESSAGE);
        }
    }

    public Long getId() {
        return id;
    }

    public Course getCourse() {
        return course;
    }

    public SessionEssentialInfo getEssentialInfo() {
        return essentialInfo;
    }

    public SessionType getType() {
        return type;
    }

    public SessionStatus getStatus() {
        return status;
    }

    public List<SignUpHistory> getSignUpHistories() {
        return signUpHistories;
    }

    public SessionPeriod getPeriod() {
        return period;
    }

    public void saveCourse(Course course) {
        this.course = course;
    }



}
