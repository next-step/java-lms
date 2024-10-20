package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 다른_사람_답변_여부_체크() {
        // given
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(AnswerTest.A2);

        // when
        boolean anyAnswerByOthers = question.anyAnswerByOthers();

        // then
        Assertions.assertThat(anyAnswerByOthers).isTrue();
    }

    @Test
    void 삭제_상태로_변경() {
        // given
        final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        // when
        question.delete();
        boolean isDeleted = question.isDeleted();

        // then
        Assertions.assertThat(isDeleted).isTrue();
    }
}
