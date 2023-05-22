package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AnswersTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    @DisplayName("Answers_생성_테스트")
    public void Answers_생성_테스트() {
        assertThat(new Answers()).isEqualTo(new Answers());
        assertThat(new Answers(A1, A2)).isEqualTo(new Answers(A1, A2));
    }

    @Test
    @DisplayName("delete_method_test")
    public void delete_method_test() throws CannotDeleteException {
        Answers answers = new Answers(A1, A1);
        assertThat(answers.delete(NsUserTest.JAVAJIGI)).hasSize(2);
    }

    @Test
    @DisplayName("delete_method_validate_test")
    public void delete_method_validate_test() {
        Answers answers = new Answers(A1, A1);
        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("Add_method_test")
    public void Add_method_test(){
        Answers answers = new Answers();
        answers.add(A1);
        answers.add(A2);
        assertThat(answers).isEqualTo(new Answers(A1,A2));
    }

}
