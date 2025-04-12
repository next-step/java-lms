package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    Answer answerByJava;
    Answer answerBySan;

    @BeforeEach
    void setUp() {
        answerByJava = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test1");
        answerBySan = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Test2");
    }

    @Test
    @DisplayName("모든 답변 작성자가 동일- 일치하지 않는 답변자가 없는 경우")
    void test_check_all_owner_true() {
        Answers answers = new Answers();
        answers.add(answerByJava);
        answers.add(answerByJava);

        assertThat(answers.hasNotMatchedOwner(NsUserTest.JAVAJIGI)).isFalse();
    }

    @Test
    @DisplayName("모든 답변 작성자가 동일하지 않음 - 일치하지 않는 답변자가 있는 경우")
    void test_check_all_owner_false() {
        Answers answers = new Answers();
        answers.add(answerByJava);
        answers.add(answerBySan);

        assertThat(answers.hasNotMatchedOwner(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("deleteAll 메소드 동작 확인 - DeleteHistory 객체 생성")
    void delete_all_test() {
        Answers answers = new Answers();
        answers.add(answerByJava);
        answers.add(answerByJava);
        List<DeleteHistory> histories = answers.deleteAll();
        assertThat(histories).hasSize(2);
    }
}
