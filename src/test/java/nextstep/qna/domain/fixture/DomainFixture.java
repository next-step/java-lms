package nextstep.qna.domain.fixture;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;

import static nextstep.users.domain.fixture.DomainFixture.*;

public class DomainFixture {

    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(SANJIGI, Q1, "Answers Contents2");

}
