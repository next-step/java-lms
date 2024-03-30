package nextstep.qna.domain;

import static nextstep.qna.domain.TestFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(
        CURRENT_DATE_TIME_PROVIDER, NsUserTest.JAVAJIGI,
        "title1", "contents1"
    );
    public static final Question Q2 = new Question(
        CURRENT_DATE_TIME_PROVIDER, NsUserTest.SANJIGI,
        "title2", "contents2"
    );

    @Test
    @DisplayName("관련된 모든 글의 작성자가 본인이라면 성공적으로 삭제한다")
    void delete() throws CannotDeleteException {
        // given
        Q1.addAnswer(AnswerTest.A1);

        // when
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistories).hasSize(2);

        DeleteHistory questionDeleteHistory = deleteHistories.stream()
            .filter(history -> history.getContentType() == ContentType.QUESTION)
            .findFirst()
            .orElseThrow();
        assertThat(questionDeleteHistory.getContentId()).isEqualTo(Q1.getId());
        assertThat(questionDeleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("글 작성자가 아닌데 삭제하려하면 예외가 발생한다")
    void delete_fail_for_not_owner() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
