package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문를 지우면 상태가 deleted로 바뀐다.")
    void delete() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        Q1.delete();
        Assertions.assertThat(Q1.isDeleted()).isTrue();
        Q1.getAnswers().forEach(answer -> Assertions.assertThat(answer.isDeleted()).isTrue());
    }
}
