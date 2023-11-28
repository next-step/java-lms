package nextstep.qna.domain;

import static nextstep.qna.domain.QuestionTest.Q1;

import nextstep.users.domain.NsUserTest;

public class AnswerTest {

        public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
}
