package nextstep.courses.domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Session {
    private Long id;
    private String image;
    private SessionDate sessionDate;
    private LectureType lectureType;
    private SessionInfo sessionInfo;

    private Session(Builder builder) {
        this.id = builder.id;
        this.image = builder.image;
        this.lectureType = builder.lectureType;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public LectureType getLectureType() {
        return lectureType;
    }


    public static class Builder {
        private Long id = 0L;
        private String image = "";
        private LectureType lectureType = LectureType.FREE;
        private LectureStatus lectureStatus = LectureStatus.PREPARING;
        private LocalDate startDate = LocalDate.now();
        private LocalDate endDate = LocalDate.now().plusDays(30);
        private List<Long> userIds = new ArrayList<>();
        private int maxUser = 100;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder image(String image) {
            this.image = image;
            return this;
        }

        public Builder lectureType(LectureType lectureType) {
            this.lectureType = lectureType;
            return this;
        }

        public Builder lectureStatus(LectureStatus lectureStatus) {
            this.lectureStatus = lectureStatus;
            return this;
        }

        public Builder startDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder endDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public Builder userIds(List<Long> userIds) {
            this.userIds = userIds;
            return this;
        }

        public Builder maxUser(int maxUser) {
            this.maxUser = maxUser;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }
}