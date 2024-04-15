package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.A1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static Question Q1;
    public static Question Q2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
        A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void 질문_작성자가_아닌_경우() {
        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 질문_삭제() throws CannotDeleteException {
        Q1.addAnswer(A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

}
