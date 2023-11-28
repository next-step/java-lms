package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final Answer A3 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents3");
    public static final Answer A4 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents4");
}
