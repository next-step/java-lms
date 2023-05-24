package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.NotFoundException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    private static final Question Q1 = Question.toQuestion(NsUserTest.JAVAJIGI, "title1", "contents1");
    private static final Answer A1 = Answer.toAnswer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");

    @DisplayName("답변 생성 시 작성자가 없을 경우 에러를 던진다.")
    @Test
    void 작성자가없는답변_생성시_에러() {
        assertThatThrownBy(() -> {
            Answer.toAnswer(null, Q1, "Answers Contents2");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("답변 생성 시 질문이 없을 경우 에러를 던진다.")
    @Test
    void 질문없는답변_생성시_에러() {
        assertThatThrownBy(() -> {
            Answer.toAnswer(NsUserTest.JAVAJIGI, null, "Answers Contents2");
        }).isInstanceOf(NotFoundException.class);
    }

    @DisplayName("답변 삭제 시 질문 작성자가 아닌 사람이 답변을 남겼을경우 에러를 던진다.")
    @Test
    void 다른작성자_답변_삭제시_에러() {
        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변삭제 성공 시 삭제여부 검증")
    @Test
    void 답변삭제_성공() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @DisplayName("답변삭제 성공 시 삭제이력 값 검증")
    @Test
    void 답변삭제_삭제이력_검증() throws CannotDeleteException {
        assertThat(A1.delete(NsUserTest.JAVAJIGI)).isEqualTo(
          new DeleteHistory(ANSWER, A1.getId(), NsUserTest.JAVAJIGI, now())
        );
    }

}
