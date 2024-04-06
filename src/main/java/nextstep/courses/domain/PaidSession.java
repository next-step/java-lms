package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public class PaidSession extends Session {

    public PaidSession(int maximumNumberOfStudent) {
        super(maximumNumberOfStudent);
    }

    @Override
    public void register(List<NsUser> users) {
        validateLessEqualThenMaximumNumber(users);
    }

    private void validateLessEqualThenMaximumNumber(List<NsUser> users) {
        if (students.size() + users.size() >= maximumNumberOfStudent) {
            throw new IllegalArgumentException("최대 수강인원을 초과하였습니다.");
        }
    }
}
