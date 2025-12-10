package nextstep.courses.domain.session.policy.tuition;


public class FreeTuition implements TuitionPolicy {

    @Override
    public void validate(long payAmount) {
        // 무료 강의는 수강료 검증 없음, 정원 제한 없음
    }

}