package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    public static final Answer A1 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents2");


    @DisplayName("작성자와 이름이 다른 경우 예외")
    @Test
    void 작성자_이름_다름_예외() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 없을 때 History 리스트 사이즈 1개")
    @Test
    void empty_answer() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertAll(
                () -> assertThat(Q1.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).hasSize(1)
        );
    }

    @DisplayName("답변 있을 때 History 리스트 사이즈 검증")
    @Test
    void exist_answers() throws CannotDeleteException {
        Q2.addAnswer(A1);
        Q2.addAnswer(A2);
        List<DeleteHistory> deleteHistories = Q2.delete(NsUserTest.SANJIGI);

        assertAll(
                () -> assertThat(Q2.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).hasSize(3)
        );
    }


}
