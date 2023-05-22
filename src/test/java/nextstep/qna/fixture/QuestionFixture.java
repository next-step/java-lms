package nextstep.qna.fixture;

import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionFixture {
    public static Question create(NsUser nextStepUser) {
        return new Question(nextStepUser, "title1", "contents1");
    }
}
