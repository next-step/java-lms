package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    private Answers answers;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    public void setUp() {
        answers = new Answers();
        answers.add(AnswerTest.A1);

        deleteHistories = List.of(
                new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, null)
        );
    }

    @Test
    @DisplayName("답변을 중복 등록하면, 에러가 발생한다.")
    void addDuplicateAnswer() {
        assertThatThrownBy(() -> answers.add(AnswerTest.A1)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("답변을 등록하면, 답변이 등록된다.")
    void addAnswer() {
        Answer answer = new Answer(3L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents3");
        answers.add(answer);
        assertThat(answers.getAnswers()).contains(answer);
    }

    @Test
    @DisplayName("답변을 모두 획득한다.")
    void getAnswers() {
        assertThat(answers.getAnswers()).hasSize(1)
                .containsAll(List.of(AnswerTest.A1));
    }

    @Test
    @DisplayName("질문을 삭제하면, 질문과 답변 삭제 히스토리를 반환한다.")
    void deleteByAndReturnHistory() {
        assertThat(answers.deleteAll()).hasSize(1).containsAll(deleteHistories);
    }

    @Test
    @DisplayName("답변을 작성한 사용자가 맞는지 확인한다.")
    void isOwner() {
        assertThat(answers.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        assertThat(answers.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

}