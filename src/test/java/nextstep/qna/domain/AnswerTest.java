package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    public static Answer javajigiAnswer(Question question) {
        return new Answer(JAVAJIGI, question, null);
    }

    public static Answer sanjigiiAnswer(Question question) {
        return new Answer(SANJIGI, question, null);
    }

    public static Answer honuxiAnswer(Question question) {
        return new Answer(HONUX, question, null);
    }

    public static Answer crongAnswer(Question question) {
        return new Answer(CRONG, question, null);
    }

    @Test
    @DisplayName("성공 - 답변 작성자와 일치할 경우 답변을 삭제 한다.")
    void success_qa_writer_answer_writer_same() throws Exception {
        A1.delete(JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("실패 - 답변 작성자와 일치하지 않을 경우 답변을 삭제 하지 못한다.")
    void success_delete_answer_writer_answer_writer_same() throws Exception {
        Assertions.assertThatThrownBy(() -> A1.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변의 작성자와 일치하지 않아 답변을 삭제할 수 없습니다.");
    }

}
