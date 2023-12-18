package nextstep.courses.domain.session;

import nextstep.courses.CannotSignUpException;
import nextstep.courses.domain.SystemTimeStamp;
import nextstep.courses.domain.image.SessionImage;
import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Session {
    private SessionInfo sessionInfo;
    private SessionImage sessionImage;
    private List<NsUser> students;
    private SessionPlan sessionPlan;
    private SystemTimeStamp systemTimeStamp;

    public Session(SessionInfo sessionInfo, SessionPlan sessionPlan, SystemTimeStamp systemTimeStamp) {
        this.sessionInfo = sessionInfo;
        this.students = new ArrayList<>(Collections.emptyList());
        this.sessionPlan = sessionPlan;
        this.sessionImage = null;
        this.systemTimeStamp = systemTimeStamp;
    }

    public void signUp(NsUser student) {
        validateEnrollmentStatus();
        students.add(student);
    }

    private void validateEnrollmentStatus() {
        if (!EnrollmentStatus.canSignUp(this.sessionPlan.getEnrollmentStatus())) {
            throw new CannotSignUpException("강의 모집중이 아닙니다.");
        }
    }

    public void saveImage(SessionImage sessionImage) {
        this.sessionImage = sessionImage;
    }

    public int getStudentCount() {
        return students.size();
    }

    public Long getId() {
        return sessionInfo.getId();
    }

    public long getCourseId() {
        return sessionInfo.getCourseId();
    }

    public String getTitle() {
        return sessionInfo.getTitle();
    }

    public SessionType getSessionType() {
        return sessionInfo.getSessionType();
    }

    public SessionPlan getSessionPlan() {
        return sessionPlan;
    }

    public SystemTimeStamp getSystemTimeStamp() {
        return systemTimeStamp;
    }

    public boolean hasImage() {
        return !(sessionImage == null);
    }

}
