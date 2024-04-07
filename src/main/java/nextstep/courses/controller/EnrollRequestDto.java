package nextstep.courses.controller;

public class EnrollRequestDto {
    private final Long courseIdx;
    private final Long userIdx;
    private final Long sessionIdx;

    public EnrollRequestDto(Long courseIdx, Long userIdx, Long sessionIdx) {
        this.courseIdx = courseIdx;
        this.userIdx = userIdx;
        this.sessionIdx = sessionIdx;
    }

    public Long getCourseIdx() {
        return courseIdx;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public Long getSessionIdx() {
        return this.sessionIdx;
    }
}
