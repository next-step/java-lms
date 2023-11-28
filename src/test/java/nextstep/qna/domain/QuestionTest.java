package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1");
        Q2.addAnswer(answer);
        Q2.delete(NsUserTest.SANJIGI);

        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        Answer answer = new Answer(11L, NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        Q1.addAnswer(answer);

        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_history_추가() throws CannotDeleteException {
        Answer answer = new Answer(11L, NsUserTest.SANJIGI, QuestionTest.Q2, "Answers Contents1");
        Q2.addAnswer(answer);
        List<DeleteHistory> deleteHistories = Q2.delete(NsUserTest.SANJIGI);

        assertThat(deleteHistories.size()).isEqualTo(2);
        assertThat(deleteHistories.get(0).toString().contains("sanjigi")).isTrue();
    }
}
