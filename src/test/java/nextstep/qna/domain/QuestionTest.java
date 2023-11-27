package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문을 삭제할 때 사용자과 작성자가 다르면 예외를 던집니다.")
    @Test
    void notSameUser() {
        // given
        // when
        // then
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("사용자와 댓글 작성자 중 다른 사용자가 있으면 예외를 던집니다.")
    @Test
    void notSameAnswerUser() {
        // given
        Question question = new Question(NsUserTest.SANJIGI, "title2", "댓글1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "댓글1"));
        // when
        // then
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("사용자와 질문 작성자, 댓글 작성자가 모두 같으면 질문과 댓글 상태를 삭제로 바꾼다.")
    @Test
    void changeStatus() throws CannotDeleteException {
        // given
        Question testQuestion = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        testQuestion.addAnswer(new Answer(NsUserTest.JAVAJIGI, testQuestion, "댓글1"));
        // when
        testQuestion.delete(NsUserTest.JAVAJIGI);
        // then
        assertThat(testQuestion.isDeleted()).isTrue();
    }

    @DisplayName("질문과 댓글에 대한 삭제 히스토리 목록을 생성해줍니다.")
    @Test
    void writeDeleteHistory() {
        // given
        Question testQuestion = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        testQuestion.addAnswer(new Answer(2L, NsUserTest.JAVAJIGI, testQuestion, "댓글1"));
        testQuestion.addAnswer(new Answer(3L, NsUserTest.JAVAJIGI, testQuestion, "댓글2"));
        // when
        List<DeleteHistory> result = testQuestion.writeDeleteHistory();
        // then
        assertThat(result).hasSize(3)
                .extracting("contentType", "contentId", "deletedBy")
                .containsExactlyInAnyOrder(
                        tuple(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI),
                        tuple(ContentType.ANSWER, 2L, NsUserTest.JAVAJIGI),
                        tuple(ContentType.ANSWER, 3L, NsUserTest.JAVAJIGI)
                );
    }
}


