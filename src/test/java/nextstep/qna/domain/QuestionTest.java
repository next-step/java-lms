package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static nextstep.qna.domain.ContentType.QUESTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {

    private static final Question Q1 = Question.toQuestion(NsUserTest.JAVAJIGI, "title1", "contents1");
    private static final Question Q2 = Question.toQuestion(NsUserTest.SANJIGI, "title2", "contents2");

    private static final Answer A1 = Answer.toAnswer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    private static final Answer A2 = Answer.toAnswer(NsUserTest.JAVAJIGI, Q2, "Answers Contents1");
    private static final Answer A3 = Answer.toAnswer(NsUserTest.SANJIGI, Q2, "Answers Contents2");

    @BeforeEach
    void setUp() {
        Q1.addAnswer(A1);
        Q2.addAnswer(A2);
        Q2.addAnswer(A3);
    }

    @DisplayName("질문 생성 시 작성자가 없을 경우 에러를 던진다.")
    @Test
    void 작성자가없는질문_생성시_에러() {
        assertThatThrownBy(() -> {
            Question.toQuestion(null, "title1", "contents1");
        }).isInstanceOf(UnAuthorizedException.class);
    }

    @DisplayName("질문 삭제 시 삭제여부 결과 검증")
    @Test
    void 삭제성공() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @DisplayName("질문 작성자가 아닐경우 삭제 시 에러를 던진다.")
    @Test
    void 작성자가아닐경우() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }  

    @DisplayName("질문 답변자가 다른 사람이 존재 할 경우 삭제 시 에러를 던진다.")
    @Test
    void 다른사람이쓴답변이있는경우() {
        assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제시 삭제이력 결과 값 검증")
    @Test
    void 질문삭제시_삭제이력검증() throws CannotDeleteException {
        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).containsExactly(
                new DeleteHistory(QUESTION, Q1.getId(), NsUserTest.JAVAJIGI, now()),
                new DeleteHistory(ANSWER, A1.getId(), NsUserTest.JAVAJIGI, now())
        );
    }
}
