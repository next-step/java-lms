package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class AnswersTest {
    public final Answers TEST_ANSWERS = new Answers();

    @DisplayName("질문을 삭제하면 올바른 삭제 이력 리스트를 반환한다.")
    @Test
    public void deleteTest() throws CannotDeleteException {
        TEST_ANSWERS.add(AnswerTest.A1);
        TEST_ANSWERS.add(AnswerTest.A3);
        assertThat(TEST_ANSWERS.delete(NsUserTest.JAVAJIGI))
                .containsExactlyElementsOf(
                        List.of(
                                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()),
                                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now())
                        )
                );
    }

    @DisplayName("작성자가 다른 댓글이 있으면 삭제할 수 없다.")
    @Test
    public void shouldNotDeleteIfWriterIsDifferentEachOther() {
        TEST_ANSWERS.add(AnswerTest.A1);
        TEST_ANSWERS.add(AnswerTest.A2);
        assertThatThrownBy(() -> TEST_ANSWERS.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}