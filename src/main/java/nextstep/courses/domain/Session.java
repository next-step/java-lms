package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private Long courseId;
    private SessionPeriod sessionPeriod;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CoverImage coverImage;
    private PaymentType paymentType;
    private SessionStatus sessionStatus;
    private List<NsUser> nsUsers = new ArrayList<>();
    private int maxUserSize;

    public Session(Long id, Long courseId, SessionPeriod sessionPeriod, LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, PaymentType paymentType, SessionStatus sessionStatus, int maxUserSize) {
        this.id = id;
        this.courseId = courseId;
        this.sessionPeriod = sessionPeriod;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
        this.maxUserSize = maxUserSize;
    }

    public void register(NsUser nsUser) {
        validate();
        nsUsers.add(nsUser);
    }

    private void validate() {
        if (sessionStatus != SessionStatus.RECRUITING) {
            throw new IllegalStateException("모집중이 아닙니다.");
        }
        if (maxUserSize <= nsUsers.size()) {
            throw new IllegalStateException("모집인원이 가득 찼습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return maxUserSize == session.maxUserSize && Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt) && Objects.equals(coverImage, session.coverImage) && paymentType == session.paymentType && sessionStatus == session.sessionStatus && Objects.equals(nsUsers, session.nsUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, sessionPeriod, createdAt, updatedAt, coverImage, paymentType, sessionStatus, nsUsers, maxUserSize);
    }
}
