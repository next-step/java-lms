package nextstep.fixture;

import nextstep.qna.domain.Answer;

import static nextstep.fixture.NsUserFixture.*;
import static nextstep.fixture.QuestionFixture.*;

public class AnswerFixture {

    private static final String ANSWERS_CONTENTS_1 = "Answers Contents1";
    private static final String ANSWERS_CONTENTS_2 = "Answers Contents2";

    public static final Answer JAVAJIGI_ANSWER_AND_QUESTION = new Answer(1L,
                                                                         JAVAJIGI,
                                                                         JAVAJIGI_QUESTION,
                                                                         ANSWERS_CONTENTS_1);

    public static final Answer JAVAJIGI_QUESTION_SANJIGI_ANSWER = new Answer(2L,
                                                                             SANJIGI,
                                                                             JAVAJIGI_QUESTION,
                                                                             ANSWERS_CONTENTS_2);
}
