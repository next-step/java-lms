package nextstep.fixture;

import nextstep.qna.domain.Answer;

import static nextstep.fixture.NsUserFixture.*;
import static nextstep.fixture.QuestionFixture.*;

public class AnswerFixture {

    private static final String ANSWERS_CONTENTS_1 = "Answers Contents1";
    private static final String ANSWERS_CONTENTS_2 = "Answers Contents2";

    public static final Answer A1 = new Answer(1L,
                                               JAVAJIGI,
                                               Q1,
                                               ANSWERS_CONTENTS_1);

    public static final Answer A2 = new Answer(2L,
                                               SANJIGI,
                                               Q1,
                                               ANSWERS_CONTENTS_2);

    public static final Answer A3 = new Answer(3L,
                                               JAVAJIGI,
                                               Q1,
                                               ANSWERS_CONTENTS_1);
}
