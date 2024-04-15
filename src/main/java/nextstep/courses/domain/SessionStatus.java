package nextstep.courses.domain;

public class SessionStatus {

    private String status;
    private final String prepare = "prepare";
    private final String end = "end";
    private final String recruit = "recruit";

    public SessionStatus(String status) {
        this.status = status;
    }

    private void statusCheck(String status) {
        if (!(status.equals(prepare) || status.equals(end) || status.equals(recruit)))
            throw new IllegalArgumentException("잘못된 강의 상태 입니다");
    }
}
