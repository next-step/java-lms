package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;
    private List<DeleteHistory> deleteHistories;

    @BeforeEach
    void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        deleteHistories = new ArrayList<>();
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        Answer delete = answer.delete(NsUserTest.JAVAJIGI, deleteHistories);
        Assertions.assertThat(delete.isDeleted()).isTrue();
    }

    @Test
    void delete_답변작성자가_아닐_경우() {
        Assertions.assertThatThrownBy(() -> {
            Answer delete = answer.delete(NsUserTest.SANJIGI, deleteHistories);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
