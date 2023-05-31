package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer answer = new Answer(11L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @BeforeAll
    public static void init() {
        Q1.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {
        assertThat(Q1.isDeleted()).isFalse();
        Q1.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }
}
