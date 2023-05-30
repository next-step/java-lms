package nextstep.courses.domain.session;

import java.util.Objects;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.user.User;
import nextstep.courses.domain.user.Users;

public class Session {

    private final Long id;

    private final Duration duration;

    private final CoverImage coverImage;

    private final PriceType priceType;

    private final Status status;

    private final Long maximumCapacity;

    private final Users users;

    public Session(Long id, Duration duration, CoverImage coverImage,
        PriceType priceType, Status status, Long maximumCapacity, Users users) {
        this.id = id;
        this.duration = duration;
        this.coverImage = coverImage;
        this.priceType = priceType;
        this.status = status;
        this.maximumCapacity = maximumCapacity;
        this.users = users;
    }

    public static Session of(Long id, Duration duration, CoverImage coverImagePath,
        PriceType priceType, Status status, Long maximumCapacity, Users users) {
        return new Session(id, duration, coverImagePath, priceType, status, maximumCapacity,
            users);
    }

    public void enroll(User user) throws CannotEnrollException {
        if (isNotCurrentlyEnrolling()) {
            throw new CannotEnrollException("모집중인 강의가 아닙니다 : " + status.description());
        }
        if (isExceedingMaximumCapacity()) {
            throw new CannotEnrollException("최대 수강 인원을 초과했습니다.");
        }
        users.add(user);
    }

    public boolean isEnrolled(User user) {
        return users.isContains(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Session session = (Session) o;
        return Objects.equals(id, session.id) && Objects.equals(duration,
            session.duration) && Objects.equals(coverImage, session.coverImage)
            && priceType == session.priceType && status == session.status && Objects.equals(
            maximumCapacity, session.maximumCapacity) && Objects.equals(users, session.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, duration, coverImage, priceType, status, maximumCapacity,
            users);
    }

    private boolean isNotCurrentlyEnrolling() {
        return !status.equals(Status.ENROLLING);
    }

    private boolean isExceedingMaximumCapacity() {
        return users.count() >= maximumCapacity;
    }

    public Duration duration() {
        return duration;
    }

    public CoverImage coverImage() {
        return coverImage;
    }

    public PriceType priceType() {
        return priceType;
    }

    public Status status() {
        return status;
    }

    public Long maximumCapacity() {
        return maximumCapacity;
    }

    public Users users() {
        return users;
    }

    public Long id() {
        return id;
    }

    public static class Builder {
        private Long id;

        private Duration duration;

        private CoverImage coverImage;

        private PriceType priceType;

        private Status status;

        private Long maximumCapacity;

        private Users users;

        public Builder() {}

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder duration(Duration duration) {
            this.duration = duration;
            return this;
        }

        public Builder coverImage(CoverImage coverImage) {
            this.coverImage = coverImage;
            return this;
        }

        public Builder priceType(PriceType priceType) {
            this.priceType = priceType;
            return this;
        }

        public Builder status(Status status) {
            this.status = status;
            return this;
        }

        public Builder maximumCapacity(Long maximumCapacity) {
            this.maximumCapacity = maximumCapacity;
            return this;
        }

        public Builder users(Users users) {
            this.users = users;
            return this;
        }

        public Session build() {
            return new Session(id, duration, coverImage, priceType, status, maximumCapacity, users);
        }
    }
}
