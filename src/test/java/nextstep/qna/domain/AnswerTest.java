package nextstep.qna.domain;

import nextstep.qna.domain.fixture.QuestionFixture;
import nextstep.users.domain.fixture.NsUserFixture;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserFixture.JAVAJIGI, QuestionFixture.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserFixture.SANJIGI, QuestionFixture.Q1, "Answers Contents2");
}
