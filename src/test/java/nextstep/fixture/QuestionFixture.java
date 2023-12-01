package nextstep.fixture;

import nextstep.qna.domain.Question;

import static nextstep.fixture.NsUserFixture.*;

public class QuestionFixture {

    private static final String TITLE_1 = "title1";
    private static final String CONTENTS_1 = "contents1";
    private static final String TITLE_2 = "title2";
    private static final String CONTENTS_2 = "contents2";

    public static final Question JAVAJIGI_QUESTION = new Question(1L,
                                                                  JAVAJIGI,
                                                                  TITLE_1,
                                                                  CONTENTS_1);

   public static final Question SANGIJI_QUESTION = new Question(2L,
                                                                SANJIGI,
                                                                TITLE_2,
                                                                CONTENTS_2);
}
