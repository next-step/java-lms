package nextstep.courses.controller;

public class EnrollRequestDto {
    private final Long courseIdx;
    private final Long userIdx;


    public EnrollRequestDto(Long courseIdx, Long userIdx) {
        this.courseIdx = courseIdx;
        this.userIdx = userIdx;
    }

    public Long getCourseIdx() {
        return courseIdx;
    }

    public Long getUserIdx() {
        return userIdx;
    }

    public Object getSessionIdx() {
        return null;
    }
}
