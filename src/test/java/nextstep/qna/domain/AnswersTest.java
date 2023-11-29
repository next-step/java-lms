package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.javajigiAnswer;
import static nextstep.qna.domain.AnswerTest.sanjigiiAnswer;
import static nextstep.qna.domain.QuestionTest.Q1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @Test
    @DisplayName("성공 - 질문자와 답변글의 모든 답변자가 같다.")
    void success_qa_writer_answer_writer_same() throws Exception {
        Answers answers = new Answers(
                List.of(javajigiAnswer(Q1), javajigiAnswer(Q1), javajigiAnswer(Q1))
        );

        assertThat(answers.hasOtherWriter(JAVAJIGI)).isFalse();
    }

    @Test
    @DisplayName("성공 - 질문자와 답변자 중 질문자와 다른 답변자가 존재한다.")
    void success_qa_writer_answer_writer_different() throws Exception {
        Answers answers = new Answers(
                List.of(javajigiAnswer(Q1), javajigiAnswer(Q1), sanjigiiAnswer(Q1))
        );

        assertThat(answers.hasOtherWriter(JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("성공 - 질문에 대한 답변이 존재하지 않는다.")
    void success_qna_include_answer_zero() throws Exception {
        Answers answers = new Answers(new ArrayList<>());

        assertThat(answers.hasOtherWriter(JAVAJIGI)).isFalse();
    }

    @Test
    @DisplayName("성공 - 작성자가 모두 일치하는 답변의 경우 답변을 모두 삭제할 수 있다.")
    void success_answer_all_delete() throws Exception {
        Answers answers = new Answers(
                List.of(javajigiAnswer(Q1), javajigiAnswer(Q1), javajigiAnswer(Q1))
        );

        answers.deleteAll(JAVAJIGI);

        assertThat(answers.answers()).hasSize(3)
                .extracting("deleted")
                .containsOnly(true, true, true);
    }

    @Test
    @DisplayName("실패 - 작성자가 일치하지 않는 답변이 한 개라도 존재하는 경우 답변을 삭제할 수 없다.")
    void fail_answer_all_delete() throws Exception {
        Answers answers = new Answers(
                List.of(javajigiAnswer(Q1), javajigiAnswer(Q1), sanjigiiAnswer(Q1))
        );

        Assertions.assertThatThrownBy(() -> answers.deleteAll(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }




}
