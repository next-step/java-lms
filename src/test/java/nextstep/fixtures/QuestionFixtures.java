package nextstep.fixtures;

import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class QuestionFixtures {
    public static Question createQuestion(NsUser nsUser) {
        return new Question(nsUser, "title1", "contents1");
    }
}
