package nextstep.courses.course.domain;

import java.time.LocalDateTime;
import nextstep.common.domain.BaseEntity;
import nextstep.courses.course.domain.enumaration.CourseChargeType;

public class Course extends BaseEntity {
    private String title;
    private Long creatorId;
    private CourseChargeType courseChargeType;


    public Course(String title, Long creatorId) {
        this(0L, title, creatorId, LocalDateTime.now(), null, CourseChargeType.PAID);
    }

    // TODO 중요한 도메인 객체에서 여러가지 생성자 버전을 외부에 제공할때 식별자를 외부에 열어놓는다는것 자체가 아직은 좀 불안하네요.
    //  일단 주생성자에만 식별자 파라미터를 열어놓고 그 외엔 모두 닫아놓는 방식으로 구현해보려고 하는데 어떻게 생각하시나요?
    public Course(String title, Long creatorId, CourseChargeType courseChargeType) {
        this(0L, title, creatorId, LocalDateTime.now(), null, courseChargeType);
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        this(id, title, creatorId, createdAt, updatedAt, CourseChargeType.PAID);
    }

    public Course(
            Long id,
            String title,
            Long creatorId,
            LocalDateTime createdAt,
            LocalDateTime updatedAt,
            CourseChargeType courseChargeType
    ) {
        super(id, createdAt, updatedAt);
        this.title = title;
        this.creatorId = creatorId;
        this.courseChargeType = courseChargeType;
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
