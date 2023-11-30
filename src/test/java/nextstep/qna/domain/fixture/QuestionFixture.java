package nextstep.qna.domain.fixture;

import nextstep.qna.domain.Question;
import nextstep.users.domain.fixture.NsUserFixture;

public class QuestionFixture {
    public static final Question Q1 = new Question(NsUserFixture.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserFixture.SANJIGI, "title2", "contents2");
}
