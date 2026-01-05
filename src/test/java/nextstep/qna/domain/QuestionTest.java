package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static Question Q1;
    public static Answer A1;

    public static Question aQuestion() {
        return QuestionBuilder.aQuestion().withWriter(NsUserTest.JAVAJIGI).withTitle("title1").withContents("contents1").build();
    }

    @BeforeEach
    public void setUp() {
        Q1 = aQuestion();
        A1 = AnswerTest.aAnswer();
    }

    @Test
    public void delete() throws Exception {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.history()).isEqualTo(List.of(DeleteHistoryTest.Q1DeleteHistory, DeleteHistoryTest.A1DeleteHistory));
    }

    @Test
    public void deleteByWrongUser() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
