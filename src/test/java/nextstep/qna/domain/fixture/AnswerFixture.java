package nextstep.qna.domain.fixture;

import nextstep.qna.domain.Answer;
import nextstep.users.domain.NsUserFixture;

public class AnswerFixture {

    public static final Answer A1 = new Answer(NsUserFixture.JAVAJIGI, QuestionFixture.Q1, "Answers Contents1");

    public static final Answer A2 = new Answer(NsUserFixture.SANJIGI, QuestionFixture.Q1, "Answers Contents2");

}
