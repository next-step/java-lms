package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(NsUserTest.JAVAJIGI, "testQuestion", "contents1");
    }

    @DisplayName("Question 도메인 객체 삭제 성공")
    @Test
    void delete_success() {
        DeleteHistory deleteHistory = question.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistory.getContentId()).isEqualTo(question.getId());
        assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.QUESTION);
        assertThat(deleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);

    }

    @DisplayName("Question 도메인 객체 삭제 실패 - 다른 사용자")
    @Test
    void delete_owner_validate_fail() {
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(Q1.isDeleted()).isFalse();
    }

    @DisplayName("Question 삭제시, 그 안에 모든 Answer 삭제 성공")
    @Test
    void delete_question_with_answers_success() {
        // given
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents3"));

        // when
        List<DeleteHistory> deleteHistories = question.deleteQnA(NsUserTest.JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(question.getAnswers().getValues()).extracting("deleted").containsOnly(true);
        assertThat(deleteHistories).hasSize(4);
    }

    @DisplayName("Question 삭제시, 그 안에 모든 Answer 삭제 실패 - 다른 사용자의 Answer 존재")
    @Test
    void delete_question_with_answers_fail() {
        // given
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents1"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents2"));
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "Answers Contents3"));

        // when
        assertThatThrownBy(() -> question.deleteQnA(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }
}
