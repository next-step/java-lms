package nextstep.courses.domain;

public class SessionApply {

    public static void apply(Session session, Student student) {
        if (!session.isRecruitState()) {
            throw new IllegalArgumentException("모집 중인 강의가 아닙니다.");
        }
        if (session.isAlreadyFull()) {
            throw new IllegalArgumentException("수강 신청 인원 초과 과정입니다.");
        }

        session.successApply(student);
    }
}
