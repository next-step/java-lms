package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer JAVAJIGI_ANSWER = new Answer(NsUserTest.JAVAJIGI, QuestionTest.JAVAHIGI_QUESTION, "Answers Contents1");
    public static final Answer SANJIGI_ANSWER = new Answer(NsUserTest.SANJIGI, QuestionTest.JAVAHIGI_QUESTION, "Answers Contents2");
}
