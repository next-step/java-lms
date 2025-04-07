package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 삭제 시 삭제 상태 변경")
    @Test
    void deleteStatusTest() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.delete(NsUserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("로그인 사용자와 질문 작성자가 다르면 예외 발생")
    @Test
    void delete_다른_사람이_쓴_글() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자와 답변자가 다를 경우 예외 발생")
    @Test
    void delete_다른_사람이_쓴_답변() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "answer1"));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 작성자와 답변자가 같을 경우 삭제 가능")
    @Test
    void delete_같은_사람이_쓴_답변() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Answer answer1 = new Answer(NsUserTest.JAVAJIGI, question, "answer1");
        question.addAnswer(answer1);

        List<DeleteHistory> deleteHistories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories).isEqualTo(List.of(
                        new DeleteHistory(ContentType.QUESTION, question.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answer1.getId(), NsUserTest.JAVAJIGI, LocalDateTime.now())))
                .hasSize(2);
    }
}
