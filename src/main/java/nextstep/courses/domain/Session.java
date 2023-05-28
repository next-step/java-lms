package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Session {
    private Long id;
    private Long courseId;
    private LocalDateTime startAt;
    private LocalDateTime endAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private CoverImage coverImage;
    private PaymentType paymentType;
    private SessionStatus sessionStatus;
    private List<NsUser> nsUsers = new ArrayList<>();
    private int maxUserSize;

    public Session(Long id, long courseId, LocalDateTime startAt, LocalDateTime endAt, LocalDateTime createdAt, LocalDateTime updatedAt, CoverImage coverImage, PaymentType paymentType, SessionStatus sessionStatus, int maxUserSize) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("시작일은 종료일을 넘을 수 없습니다.");
        }
        this.id = id;
        this.courseId = courseId;
        this.startAt = startAt;
        this.endAt = endAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.coverImage = coverImage;
        this.paymentType = paymentType;
        this.sessionStatus = sessionStatus;
        this.maxUserSize = maxUserSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return maxUserSize == session.maxUserSize && Objects.equals(id, session.id) && Objects.equals(courseId, session.courseId) && Objects.equals(startAt, session.startAt) && Objects.equals(endAt, session.endAt) && Objects.equals(createdAt, session.createdAt) && Objects.equals(updatedAt, session.updatedAt) && Objects.equals(coverImage, session.coverImage) && paymentType == session.paymentType && sessionStatus == session.sessionStatus && Objects.equals(nsUsers, session.nsUsers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, courseId, startAt, endAt, createdAt, updatedAt, coverImage, paymentType, sessionStatus, nsUsers, maxUserSize);
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
}
