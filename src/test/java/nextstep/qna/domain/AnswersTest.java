package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른 사람이 쓴 답변이 있을 경우 삭제 불가")
    void isDeletableTest() {
        Answers multiUserAnswers = new Answers(List.of(A1, A2));

        assertThatThrownBy(() -> multiUserAnswers.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 삭제")
    void deleteAnswersTest() throws CannotDeleteException {
        Answers answers = new Answers(List.of(A1));

        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories)
                .hasSize(1);
    }

    @Test
    @DisplayName("답변 추가")
    void addAnswer() {

        Answers answers = new Answers(new ArrayList<>(List.of(A1)));

        Answers newAnswers = answers.add(A2);

        assertThat(newAnswers)
                .usingRecursiveComparison()
                .isEqualTo(new Answers(new ArrayList<>(List.of(A1, A2))));

    }

    @Test
    @DisplayName("삭제 테스트")
    void deleteTest() {
        DeleteHistory delete = A1.delete();

        assertThat(A1.isDeleted())
                .isTrue();

        assertThat(delete)
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

}