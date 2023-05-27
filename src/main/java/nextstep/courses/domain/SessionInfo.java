package nextstep.courses.domain;

public class SessionInfo {
    private final String title;
    private final Long creatorId;
    private final Course course;
    private final int maxNumberOfUsers;
    private int registeredNumberOfUsers;

    public SessionInfo(String title, Long creatorId, Course course, int maxNumberOfUsers, int registeredNumberOfUsers) {
        this.title = title;
        this.creatorId = creatorId;
        this.course = course;
        this.maxNumberOfUsers = maxNumberOfUsers;
        this.registeredNumberOfUsers = registeredNumberOfUsers;
    }

    public String getTitle() {
        return title;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public Long getCourseId() {
        return course.getId();
    }

    public void increaseRegisteredUser(int userId) {
        if (isFull()) {
            throw new IllegalArgumentException("이미 등록된 강의입니다.");
        }
        this.registeredNumberOfUsers++;
    }

    private boolean isFull() {
        return this.registeredNumberOfUsers >= this.maxNumberOfUsers;
    }

    public int countUsers() {
        return this.registeredNumberOfUsers;
    }
}
