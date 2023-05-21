package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class AnswersTest {

    private static final Answers ANSWERS = new Answers() {{
        add(AnswerTest.A1);
        add(AnswerTest.A2);
    }};

    @Test
    void 답변추가() {
        ANSWERS.add(AnswerTest.YEAHCHAN_A1);
        assertAll(
                () -> assertThat(ANSWERS.size()).isEqualTo(3),
                () -> assertThat(ANSWERS.contains(AnswerTest.YEAHCHAN_A1)).isTrue()
        );
    }

    @Test
    void 다른유저답변_삭제불가() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        assertThatThrownBy(() -> ANSWERS.delete(NsUserTest.YEAHCHAN, deleteHistories)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void 답변모두_삭제가능() throws CannotDeleteException {
        Answers answers = new Answers() {{
            add(AnswerTest.YEAHCHAN_A1);
            add(AnswerTest.YEAHCHAN_A2);
        }};
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.delete(NsUserTest.YEAHCHAN, deleteHistories);
        assertAll(
                () -> assertThat(AnswerTest.YEAHCHAN_A1.isDeleted()).isTrue(),
                () -> assertThat(AnswerTest.YEAHCHAN_A2.isDeleted()).isTrue()
        );
    }
}