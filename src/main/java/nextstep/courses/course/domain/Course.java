package nextstep.courses.course.domain;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.hasText;

import java.time.LocalDateTime;
import java.util.ArrayList;
import nextstep.common.domain.BaseEntity;
import nextstep.courses.cohort.domain.Cohorts;
import nextstep.courses.course.domain.enumaration.CourseChargeType;

public class Course extends BaseEntity {

    private String title;
    private Long creatorId;
    private CourseChargeType courseChargeType;
    private Cohorts cohorts;

    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, CourseChargeType.PAID,
                new Cohorts(new ArrayList<>()));
    }

    public Course(String title, Long creatorId, CourseChargeType courseChargeType) {
        this(0L, title, creatorId, LocalDateTime.now(), null, courseChargeType,
                new Cohorts(new ArrayList<>()));
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this(id, title, creatorId, createdAt, updatedAt, CourseChargeType.PAID,
                new Cohorts(new ArrayList<>()));
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            CourseChargeType courseChargeType,
            Cohorts cohorts
    ) {
        super(id, createdAt, updatedAt);

        if (!hasText(title)) {
            throw new IllegalArgumentException("강의제목은 필수값 입니다.");
        }

        if (isNull(creatorId) || creatorId <= 0L) {
            throw new IllegalArgumentException("강의 생성자 정보는 필수 값 입니다.");
        }

        if (isNull(courseChargeType)) {
            throw new IllegalArgumentException("강의 결제타입은 필수 값 입니다.");
        }

        this.title = title;
        this.creatorId = creatorId;
        this.courseChargeType = courseChargeType;
        this.cohorts = cohorts;
    }

    public boolean isPaid() {
        return this.courseChargeType.equals(CourseChargeType.PAID);
    }

    public boolean isFree() {
        return this.courseChargeType.equals(CourseChargeType.FREE);
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }


    @Override
    public String toString() {
        return "Course{" +
                "id='" + super.getId() + '\'' +
                "title='" + title + '\'' +
                ", creatorId=" + creatorId +
                ", courseChargeType=" + courseChargeType +
                '}';
    }
}
