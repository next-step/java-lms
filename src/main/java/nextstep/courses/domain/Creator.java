package nextstep.courses.domain;

public class Creator {
    private Long creatorId;

    private String name;

    public Creator(Long creatorId, String name) {
        this.creatorId = creatorId;
        this.name = name;
    }

    public Creator(String name) {
        this.name = name;
    }

    public Creator(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Creator{" +
                "creatorId=" + creatorId +
                ", name='" + name + '\'' +
                '}';
    }
}
