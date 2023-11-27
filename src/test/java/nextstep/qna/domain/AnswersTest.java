package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.qna.domain.QuestionTest.Q1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @Test
    @DisplayName("성공 - 질문자와 답변글의 모든 답변자가 같다.")
    void success_qa_writer_answer_writer_same() throws Exception {
        Answers answers = new Answers(
                List.of(new Answer(JAVAJIGI, Q1), new Answer(JAVAJIGI, Q1), new Answer(JAVAJIGI, Q1))
        );

        assertThat(answers.isAllSameBy(JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("성공 - 질문자와 답변자 중 질문자와 다른 답변자가 존재한다.")
    void success_qa_writer_answer_writer_different() throws Exception {
        Answers answers = new Answers(
                List.of(new Answer(JAVAJIGI, Q1), new Answer(JAVAJIGI, Q1), new Answer(SANJIGI, Q1))
        );

        assertThat(answers.isAllSameBy(JAVAJIGI)).isFalse();
    }

    @Test
    @DisplayName("성공 - 질문에 대한 답변이 존재하지 않는다.")
    void success_qna_include_answer_zero() throws Exception {
        Answers answers = new Answers(new ArrayList<>());

        assertThat(answers.isAllSameBy(JAVAJIGI)).isFalse();
    }

}
