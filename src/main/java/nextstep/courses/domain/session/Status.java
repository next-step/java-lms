package nextstep.courses.domain.session;

import nextstep.courses.exception.NotRecruitingException;

public enum Status {

    PREPARE("준비중"),
    RECRUIT("모집중"),
    FINISH("종료");

    private String description;

    Status(String description) {
        this.description = description;
    }

    public static void validate(Status status) throws NotRecruitingException {
        if (RECRUIT.equals(status)) {
            return;
        }

        throw new NotRecruitingException(String.format("해당 강의의 현재 상태는 %s입니다.", status.description));
    }
}
