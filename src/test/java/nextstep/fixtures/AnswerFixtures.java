package nextstep.fixtures;

import nextstep.qna.domain.Answer;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class AnswerFixtures {

    public static Answer createAnswer1(NsUser nsUser) {
        return new Answer(nsUser, QuestionFixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    }

    public static Answer createAnswer2(NsUser nsUser) {
        return new Answer(nsUser, QuestionFixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");
    }
}
