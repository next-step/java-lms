package nextstep.qna.domain;

import nextstep.Fixtures;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Fixtures.createQuestion(NsUserTest.JAVAJIGI), "Answers Contents2");
}
