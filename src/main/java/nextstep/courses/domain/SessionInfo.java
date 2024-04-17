package nextstep.courses.domain;

public class SessionInfo {
    private final String title;
    private final Image coverImage;
    private final Period period;
    private final int studentCapacity;

    public SessionInfo(String title, Image coverImage, Period period, int studentCapacity) {
        this.title = title;
        this.coverImage = coverImage;
        this.period = period;
        this.studentCapacity = studentCapacity;
    }

    public boolean isSessionFull(int studentCount) {
        return studentCount >= studentCapacity;
    }
}
