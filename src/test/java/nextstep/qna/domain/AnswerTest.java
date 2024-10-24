package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");
}
