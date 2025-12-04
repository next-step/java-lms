package nextstep.qna.domain;

public class BaseEntity {
    private Long id;
    private TimeStamp timestamp;

    public BaseEntity(Long id) {
        this(id, new TimeStamp());
    }

    public BaseEntity(Long id, TimeStamp timestamp) {
        this.id = id;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseEntity{id=" + id + ", timestamp=" + timestamp + '}';
    }
}
