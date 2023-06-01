package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @BeforeEach
    void setUp() {

    }

    @Test
    void delete_성공() throws CannotDeleteException {
        Answers answers = new Answers();
        answers.add(A1);

        answers.delete(NsUserTest.JAVAJIGI, new ArrayList<>());

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI, new ArrayList<>()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}