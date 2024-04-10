package nextstep.courses.domain;

public class SessionApply {

    public void apply(Session session) {
        if (!session.isRecruitState()) {
            throw new IllegalArgumentException("모집 중인 강의가 아닙니다.");
        }
    }
}
