package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;

import java.util.List;

public class QuestionTest {
    public static final Question Q1 = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1", Answers.of(List.of(AnswerTest.A1)), null);
    public static final Question Q2 = new Question(2L, NsUserTest.SANJIGI, "title2", "contents2", Answers.of(List.of()), null);

}
