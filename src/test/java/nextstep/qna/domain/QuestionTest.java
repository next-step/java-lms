package nextstep.qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문를 지우면 상태가 deleted로 바뀐다.")
    void delete() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(AnswerTest.A1.getWriter());
        Assertions.assertThat(Q1.isDeleted()).isTrue();
        Q1.getAnswers().forEach(answer -> Assertions.assertThat(answer.isDeleted()).isTrue());
    }

    @Test
    @DisplayName("writer가 다르면 삭제할 수 없다.")
    void deleteByDifferentWriter() {
        Assertions.assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
