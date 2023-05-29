package nextstep.courses.domain;

public class RecruitmentCount {

    private static final String RECRUIT_COUNT_NOT_NEGATIVE = "모집인원은 음수 일 수 없습니다.";
    private static final int MIN_RECRUIT_COUNT = 0;

    private int count;

    public RecruitmentCount(int count) {
        validate(count);
        this.count = count;
    }

    private void validate(int count) {
        if (count < MIN_RECRUIT_COUNT) {
            throw new IllegalArgumentException(RECRUIT_COUNT_NOT_NEGATIVE);
        }
    }

    public int count() {
        return count;
    }

}
