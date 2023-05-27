package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    private static Answers answers;

    @BeforeEach
    public void setUp(){
        answers = new Answers() {{
            add(AnswerTest.A1);
            add(AnswerTest.A2);
        }};
    }

    @Test
    void 답변추가() {
        answers.add(AnswerTest.YEAHCHAN_A1);
        assertAll(
                () -> assertThat(answers.size()).isEqualTo(3),
                () -> assertThat(answers.contains(AnswerTest.YEAHCHAN_A1)).isTrue()
        );
    }

    @Test
    void 다른유저답변_삭제불가() {
        assertThatThrownBy(() -> answers.delete(NsUserTest.YEAHCHAN)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변모두_삭제가능() throws CannotDeleteException {
        Answers newAnswers = new Answers() {{
            add(AnswerTest.YEAHCHAN_A1);
            add(AnswerTest.YEAHCHAN_A2);
        }};
        List<DeleteHistory> deleteHistories = newAnswers.delete(NsUserTest.YEAHCHAN);
        assertAll(
                () -> assertThat(AnswerTest.YEAHCHAN_A1.isDeleted()).isTrue(),
                () -> assertThat(AnswerTest.YEAHCHAN_A2.isDeleted()).isTrue(),
                () -> assertThat(deleteHistories).hasSize(2)
        );
    }
}