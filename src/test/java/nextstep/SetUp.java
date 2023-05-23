package nextstep;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class SetUp {

    public static Answer createAnswer1(NsUser nsUser) {
        return new Answer(nsUser, SetUp.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    }

    public static Answer createAnswer2(NsUser nsUser) {
        return new Answer(nsUser, SetUp.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");
    }

    public static Question createQuestion(NsUser nsUser) {
        return new Question(nsUser, "title1", "contents1");
    }
}
