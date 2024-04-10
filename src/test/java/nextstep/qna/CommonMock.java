package nextstep.qna;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.qna.domain.QuestionTest;
import nextstep.users.domain.NsUserTest;

public class CommonMock {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

}
