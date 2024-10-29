package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Answer answer;

    @BeforeEach
    public void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, new Question(), "Answers Contents1");
    }

    @Test
    void delete_답변을_삭제하면_삭제상태가_true가_된다() throws CannotDeleteException {
        answer.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    void delete_답변을_삭제하면_삭제_이력을_반환한다() throws CannotDeleteException {
        DeleteHistory history = answer.delete(NsUserTest.JAVAJIGI);

        assertThat(history).isNotNull();
    }

    @Test
    void delete_삭제_권한이_없는_사용자가_삭제하면_에러가_발생한다() {
        assertThatThrownBy(() -> answer.delete(new NsUser())).isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }
}
