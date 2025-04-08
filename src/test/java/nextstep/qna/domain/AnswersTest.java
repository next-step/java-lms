package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {
    @Test
    @DisplayName("모든 답변 작성자가 동일한 지 확인 - true 케이스")
    void test_check_all_owner_true() {
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test1"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test2"));

        assertThat(answers.areAllOwnedBy(NsUserTest.JAVAJIGI)).isTrue();
    }

    @Test
    @DisplayName("모든 답변 작성자가 동일한 지 확인 - false 케이스")
    void test_check_all_owner_false() {
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test1"));
        answers.add(new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Test2"));

        assertThat(answers.areAllOwnedBy(NsUserTest.JAVAJIGI)).isFalse();
    }

    @Test
    @DisplayName("deleteAll 메소드 동작 확인 - DeleteHistory 객체 생성")
    void delete_all_test() {
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test1"));
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Test2"));
        List<DeleteHistory> histories = answers.deleteAll();
        assertThat(histories).hasSize(2);
    }
}
