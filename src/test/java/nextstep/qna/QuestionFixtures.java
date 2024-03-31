package nextstep.qna;

import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUserFixtures;

public class QuestionFixtures {
    public static final Question Q1 = new Question(NsUserFixtures.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserFixtures.SANJIGI, "title2", "contents2");
}
