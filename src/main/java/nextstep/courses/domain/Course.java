package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Course {
    private Long id;

    private String title;

    private BaseEntity baseEntity;

    private Integer generation;

    private List<Session> seesionList;

    public Course() {
    }

    public Course(Integer generation) {
        this.generation = generation;
        this.seesionList = new ArrayList<>();
    }


    public List<Session> register(Session session){
        this.seesionList.add(session);
        return this.seesionList;
    }

    public Course(String title, Long creatorId, Integer generation) {
        this(0L, title, creatorId, generation, LocalDateTime.now(), null);
    }

    public Course(Long id, String title, Long creatorId, Integer generation, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.title = title;
        this.generation = generation;
        this.baseEntity = new BaseEntity(creatorId, createdAt, updatedAt);
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return baseEntity.getCreatorId();
    }

    public LocalDateTime getCreatedAt() {
        return baseEntity.getCreatedAt();
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", baseEntity=" + baseEntity +
                '}';
    }
}
