package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static java.time.LocalDateTime.now;
import static nextstep.qna.domain.ContentType.ANSWER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    private static final Question Q1 = Question.toQuestion(NsUserTest.JAVAJIGI, "title1", "contents1");
    private static final Answer A1 = Answer.toAnswer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    private static final Answers ANSWERS = new Answers();

    @BeforeEach
    void setUp() {
        ANSWERS.addAnswer(A1);
    }

    @DisplayName("질문 답변자가 다른 사람이 존재 할 경우 삭제 시 에러를 던진다.")
    @Test
    void 다른사람이쓴답변이있는경우() {
        assertThatThrownBy(() -> {
            ANSWERS.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제시 삭제이력 결과 값 검증")
    @Test
    void 질문삭제시_삭제이력검증() throws CannotDeleteException {
        assertThat(ANSWERS.delete(NsUserTest.JAVAJIGI)).containsExactly(
                new DeleteHistory(ANSWER, A1.getId(), NsUserTest.JAVAJIGI, now())
        );
    }


}