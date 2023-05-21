package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private Question question1;
    private Question question2;
    private LocalDateTime now;

    @BeforeEach
    public void beforeEach() {
        now = LocalDateTime.now();
        question1 = new Question(1L, NsUserTest.JAVAJIGI, "제목1", "내용1", now);
        question2 = new Question(2L, NsUserTest.SANJIGI, "제목2", "내용2", now);
    }

    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_로그인_사용자와_질문한_사람이_같은_경우() {
        assertThatCode(() -> question1.delete(NsUserTest.JAVAJIGI, now))
                .doesNotThrowAnyException();
    }

    @DisplayName("로그인 사용자와 질문한 사람이 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_로그인_사용자와_질문한_사람이_다른_경우() {
        assertThatThrownBy(() -> question1.delete(NsUserTest.SANJIGI, now))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("질문자와 답변글의 모든답변자가 같은 경우 예외가 발생하지 않는지 확인")
    @Test
    void delete_질문자와_모든_답변자가_같은_경우() {
        question1.addAnswer(AnswerTest.A1);
        assertThatCode(() -> question1.delete(NsUserTest.JAVAJIGI, now))
                .doesNotThrowAnyException();
    }

    @DisplayName("질문자와 답변글의 모든답변자가 다른 경우 CannotDeleteException 예외가 발생하는지 확인")
    @Test
    void delete_질문자와_모든_답변자가_다른_경우() {
        question2.addAnswer(AnswerTest.A1);
        assertThatThrownBy(() -> question2.delete(NsUserTest.SANJIGI, now))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("질문 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인 (답변이 있는경우)")
    @Test
    void delete_삭제_상태_변경_답변이_있는경우() throws CannotDeleteException {
        //given
        question1.addAnswer(AnswerTest.A1);

        //when
        question1.delete(NsUserTest.JAVAJIGI, now);

        //then
        assertThat(question1.isDeleted()).isEqualTo(true);
    }

    @DisplayName("질문 삭제 시 삭제 상태(deleted - boolean type)로 변경되는지 확인 (답변이 없는경우)")
    @Test
    void delete_삭제_상태_변경_답변이_없는경우() throws CannotDeleteException {
        question1.delete(NsUserTest.JAVAJIGI, now);
        assertThat(question1.isDeleted()).isEqualTo(true);
    }

    @DisplayName("질문 삭제 시 답변의 삭제 상태(deleted - boolean type)로 변경되는지 확인")
    @Test
    void delete_답변_삭제_상태_변경() throws CannotDeleteException {
        //given
        Answer answer = AnswerTest.A1;
        question1.addAnswer(answer);

        //when
        question1.delete(NsUserTest.JAVAJIGI, now);

        //then
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 없는경우)")
    @Test
    void delete_삭제_이력_답변이_없는경우() throws CannotDeleteException {
        //given
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.QUESTION, question1.getId(), question1.getWriter(), now);

        //when
        List<DeleteHistory> deleteHistories = question1.delete(NsUserTest.JAVAJIGI, now);

        //then
        assertThat(deleteHistories).containsExactly(deleteHistory);
    }

    @DisplayName("질문 삭제시 삭제 이력이 반환되는지 확인 (답변이 있는경우)")
    @Test
    void delete_삭제_이력_답변이_있는경우() throws CannotDeleteException {
        //given
        question1.addAnswer(AnswerTest.A1);
        DeleteHistory deleteHistoryOfQuestion = new DeleteHistory(ContentType.QUESTION, question1.getId(), question1.getWriter(), now);
        DeleteHistory deleteHistoryOfAnswer = new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), now);

        //when
        List<DeleteHistory> deleteHistories = question1.delete(NsUserTest.JAVAJIGI, now);

        //then
        assertThat(deleteHistories).containsExactly(deleteHistoryOfQuestion, deleteHistoryOfAnswer);
    }
}
