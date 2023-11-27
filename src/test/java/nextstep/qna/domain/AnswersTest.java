package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.groups.Tuple.*;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    private Answers answers;

    @BeforeEach
    void setUp() {
        Question question = new Question();

        answers = new Answers(List.of(
                new Answer(1L, NsUserTest.JAVAJIGI, question, "댓글1"),
                new Answer(2L, NsUserTest.JAVAJIGI, question, "댓글2"))
        );
    }

    @DisplayName("댓글 작성자가 아닌 사용자가 삭제를 하려고 하면 에러를 발생시키고, 모든 answer의 삭제 상태는 false 입니다.")
    @Test
    void invalidUser() {
        // given
        // when
        // then
        Assertions.assertThatThrownBy(() -> answers.deleteAnswers(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        Assertions.assertThat(answers.getAnswers()).hasSize(2)
                .extracting("deleted")
                .containsExactly(false, false);
    }

    @DisplayName("댓글을 삭제하면 댓글의 삭제 상태를 true로 변경합니다.")
    @Test
    void deleteAnswer() throws CannotDeleteException {
        // given
        // when
        boolean result = answers.deleteAnswers(NsUserTest.JAVAJIGI);
        // then
        Assertions.assertThat(answers.getAnswers()).hasSize(2)
                .extracting("deleted")
                .containsExactly(true, true);
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("댓글을 삭제하면 삭제 히스토리 목록을 생성합니다.")
    @Test
    void writeDeleteHistory() {
        // given
        // when
        List<DeleteHistory> result = answers.createAnswerDeleteHistory();
        // then
        Assertions.assertThat(result).hasSize(2)
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactlyInAnyOrder(
                        tuple(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI),
                        tuple(ContentType.ANSWER, 2L, NsUserTest.JAVAJIGI)
                );
    }
}