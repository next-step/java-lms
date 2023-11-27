package nextstep.qna.domain;

import java.util.List;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AnswersTest {


    @Test
    void 답변_삭제_후_결과_확인() throws CannotDeleteException {
        Answers answers = answers();
        answers.delete(NsUserTest.JAVAJIGI);
        assertThat(answers.isAllDeleted()).isTrue();
    }

    @Test
    void 답변_추가() {
        Answers answers = emptyAnswers();
        answers.add(new Answer());
        assertThat(answers.getAnswers()).hasSize(1);
    }

    @Test
    void 삭제_이력() throws CannotDeleteException {
        Answers answers = answers();
        answers.delete(NsUserTest.JAVAJIGI);
        assertThat(answers.getDeleteHistories()).hasSize(2);
    }

    private static Answers answers() {
        NsUser writer = NsUserTest.JAVAJIGI;
        Question question = new Question(writer, "title1", "contents1");
        return new Answers(List.of(
            new Answer(writer, question, ""),
            new Answer(writer, question, "")
        ));
    }

    private static Answers emptyAnswers() {
        return new Answers();
    }

}
