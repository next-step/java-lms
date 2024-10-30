package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Answers answers;
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    public void setUp() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer contents");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "answer contents2");

        answers = new Answers(List.of(answer1, answer2));
    }

    @Test
    void delete_답변을_삭제하면_삭제상태가_true가_된다() {
        answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
    }

    @Test
    void delete_답변을_삭제하면_삭제_이력을_반환한다() {
        List<DeleteHistory> histories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(histories).hasSize(2);
    }

    @Test
    void delete_삭제_권한이_없는_사용자가_삭제하면_에러가_발생한다() {
        assertThatThrownBy(() -> answers.delete(new NsUser())).isInstanceOf(RuntimeException.class)
                .hasMessage("nextstep.qna.CannotDeleteException: 답변을 삭제할 권한이 없습니다.");
    }
}
