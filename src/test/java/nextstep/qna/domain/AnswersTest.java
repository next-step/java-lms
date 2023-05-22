package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswersTest {
    private static final Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    void 답변자가_아닌_유저가_answers를_삭제하려고_하면_예외가_발생한다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answers answers = new Answers(List.of(answer));

        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void answers를_지우면_deleteHistory_list가_반환된다() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answers answers = new Answers(List.of(answer));

        assertThat(answers.delete(NsUserTest.JAVAJIGI).size()).isEqualTo(1);
    }

    @Test
    void answers를_지우면_포함된_모든_answer의_deleted는_true_이다() throws CannotDeleteException {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        Answers answers = new Answers(List.of(answer));
        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }
}
