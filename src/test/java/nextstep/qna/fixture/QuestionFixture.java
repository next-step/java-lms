package nextstep.qna.fixture;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionFixture {
    public static Question create(NsUser nextStepUser) {
        return new Question(nextStepUser, "title1", "contents1");
    }

    public static Answer createAnswer(NsUser nextStepUser, Question question) {
        return new Answer(nextStepUser, question, "Answers Contents1");
    }

    public static Answer createAnswer(NsUser nextStepUser) {
        return new Answer(nextStepUser, create(NsUserTest.JAVAJIGI), "Answers Contents1");
    }
}
