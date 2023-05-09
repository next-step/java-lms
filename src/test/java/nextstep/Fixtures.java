package nextstep;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class Fixtures {
    public static Question createQuestion(NsUser nsUser) {
        return new Question(nsUser, "title1", "contents1");
    }

    public static Answer createAnswer(NsUser nsUser) {
        return new Answer(nsUser, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    }

}
