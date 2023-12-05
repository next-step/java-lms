package nextstep.courses.domain.entity;

import java.time.LocalDateTime;

import nextstep.users.domain.NsUser;

public class Enrollment {

    private NsCourse nsCourse;
    private NsUser nsUser;
    private NsSession nsSession;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Enrollment(NsCourse nsCourse,
                      NsUser nsUser,
                      NsSession nsSession) {
        this.nsCourse = nsCourse;
        this.nsUser = nsUser;
        this.nsSession = nsSession;
    }

    public NsCourse getNsCourse() {
        return nsCourse;
    }

    public void setNsCourse(NsCourse nsCourse) {
        this.nsCourse = nsCourse;
    }

    public NsUser getNsUser() {
        return nsUser;
    }

    public void setNsUser(NsUser nsUser) {
        this.nsUser = nsUser;
    }

    public NsSession getNsSession() {
        return nsSession;
    }

    public void setNsSession(NsSession nsSession) {
        this.nsSession = nsSession;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Enrollment() {}
}
