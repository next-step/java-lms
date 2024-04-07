package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private Question question;
    private Answer answer1;
    private Answer answer2;
    private Answer answer3;

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        answer1 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1");
        answer2 = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2");
        answer3 = new Answer(NsUserTest.SANJIGI, question, "Answers Contents3");
    }

    @Test
    @DisplayName("모든 답변의 작성자라면 삭제 가능")
    void delete_success_when_owner() {
        Answers answers = new Answers(List.of(answer1, answer2));
        answers.delete(NsUserTest.JAVAJIGI);
        assertThat(answers).isEqualTo(
                new Answers(
                        List.of(
                                new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1", true),
                                new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2", true))
                )
        );
    }

    @Test
    @DisplayName("답변들 중에 작성자가 아닌 것이 포함되면 삭제 불가능")
    void delete_when_not_owner() {
        assertThatThrownBy(() -> {
            new Answers(List.of(answer1, answer2, answer3)).delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변 삭제 시에 이력을 남김")
    void check_delete_history() {
        List<DeleteHistory> deleteHistories = new Answers(List.of(answer1, answer2)).generateDeleteHistory();
        assertThat(deleteHistories).contains(
                new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.JAVAJIGI),
                new DeleteHistory(ContentType.ANSWER, answer2.getId(), NsUserTest.JAVAJIGI)
        );
    }
}
