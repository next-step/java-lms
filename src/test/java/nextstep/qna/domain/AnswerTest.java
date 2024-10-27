package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    private Answer answer;

    @BeforeEach
    public void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, new Question(), "Answers Contents1");
    }

    @Test
    void delete_답변을_삭제하면_삭제상태가_true가_된다() throws CannotDeleteException {
        answer.delete();

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void delete_답변을_삭제하면_삭제_이력을_반환한다() throws CannotDeleteException {
        DeleteHistory history = answer.delete();

        assertThat(history).isNotNull();
    }
}
