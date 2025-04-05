package nextstep.qna.domain;

import com.sun.jdi.request.DuplicateRequestException;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;
    private Answer answer;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        answer = new Answer(11L, NsUserTest.JAVAJIGI, question, "Answers Contents1");

        deleteHistories = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, 11L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    @DisplayName("답변을 중복 등록하면, 에러가 발생한다.")
    void addDuplicateAnswer() {
        assertThatThrownBy(() -> question.addAnswer(answer)).isInstanceOf(DuplicateRequestException.class);
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 답변이 없으면 삭제 가능하다.")
    void canDeleteQuestionWithNoAnswerByOwner() {
        assertThatCode(() -> Q1.canDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("다른 사람이 쓴 질문이면, 삭제 불가능하다.")
    void canDeleteQuestionByOther() {
        assertThatThrownBy(() -> question.canDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 본인이 쓴 답변만 있으면 삭제 가능하다.")
    void canDeleteQuestionWithSelfAnswerByOwner() {
        assertThatCode(() -> question.canDelete(NsUserTest.JAVAJIGI)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("본인이 쓴 질문이고, 타인이 쓴 답변이 있으면 삭제 불가능하다.")
    void canDeleteQuestionWithOtherAnswerByOwner() {
        assertThatThrownBy(() -> question.canDelete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문을 삭제하면, 질문과 답변 삭제 히스토리를 반환한다.")
    void deleteAndReturnHistory() {
        assertThat(question.delete()).hasSize(2).isEqualTo(deleteHistories);
    }


    @Test
    @DisplayName("질문을 삭제하면, 삭제 상태가 변경된다.")
    void deleteAndUpdateDeleteStatus() {
        question.delete();
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

}
