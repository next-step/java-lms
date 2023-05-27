package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswersTest {

    private Answer javajigiAnswer;
    private Answer sanjigiAnswer;
    @BeforeEach
    void beforeEach() {
        javajigiAnswer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "JAVAJIGI Content");
        sanjigiAnswer = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "SANJIGI Contents");
    }

    @Test
    void delete() {
        assertThat(new Answers(List.of(javajigiAnswer)).delete(NsUserTest.JAVAJIGI))
            .hasSize(1);
        assertThat(javajigiAnswer.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> new Answers(List.of(sanjigiAnswer)).delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void add() {
        Answers answers = new Answers();
        answers.add(javajigiAnswer);
        assertThat(answers).isEqualTo(new Answers(List.of(javajigiAnswer)));
    }

}
