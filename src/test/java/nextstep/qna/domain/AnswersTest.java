package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {
    private Answers answers = new Answers();

    @Test
    void 여러사람의_답변이있을시_전체삭제() {
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2"));

        assertThatThrownBy(() -> {answers.delete(NsUserTest.SANJIGI);})
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 한사람의_답변이있을시_전체삭제() throws CannotDeleteException {
        Answer answers_contents1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        Answer answers_contents2 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q2, "Answers Contents2");
        answers.add(answers_contents1);
        answers.add(answers_contents2);

        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answers_contents1.isDeleted()).isEqualTo(true);
        assertThat(answers_contents2.isDeleted()).isEqualTo(true);

    }
}
