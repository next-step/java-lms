package nextstep.courses.domain.model;

import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;

public class Student {
    private final NsUser user;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Student(NsUser user) {
        this(user, LocalDateTime.now(), LocalDateTime.now());
    }

    public Student(NsUser user, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.user = user;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void pay(Long price) {
        user.pay(price);
    }

    public Long getNsUserId() {
        return user.getId();
    }

    public NsUser getUser() {
        return user;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    @Override
    public String toString() {
        return "Student{" +
                "user=" + user +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
