package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static Answer A1;
    public static Answer A2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("질문의 댓글 작성자가 아닐 때 삭제 할 수 없음")
    void anwser_not_ower() {
        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_삭제() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
