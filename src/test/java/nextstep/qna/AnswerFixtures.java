package nextstep.qna;

import nextstep.qna.domain.Answer;
import nextstep.users.domain.NsUserFixtures;

public class AnswerFixtures {
    public static final Answer A1 = new Answer(NsUserFixtures.JAVAJIGI, QuestionFixtures.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserFixtures.SANJIGI, QuestionFixtures.Q1, "Answers Contents2");

}
