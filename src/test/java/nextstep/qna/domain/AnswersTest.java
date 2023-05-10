package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.Fixtures.createAnswer1;
import static nextstep.Fixtures.createAnswer2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.groups.Tuple.*;

class AnswersTest {

    @Test
    @DisplayName("답변을 추가")
    void test01() {
        Answers answers = new Answers();

        answers.addAnswer(createAnswer1(NsUserTest.JAVAJIGI));

        assertThat(answers.getAnswers()).hasSize(1);
    }

    @Test
    @DisplayName("답변들을 모두 삭제")
    void test02() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer1(NsUserTest.JAVAJIGI));
        answers.addAnswer(createAnswer2(NsUserTest.JAVAJIGI));

        List<DeleteHistory> deleteHistories = answers.deleteAll(NsUserTest.JAVAJIGI);

        assertThat(answers.getAnswers()).extracting("deleted").containsExactly(true, true);
        assertThat(deleteHistories).extracting("contentType", "deletedBy")
                                   .containsExactly(tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI),
                                                    tuple(ContentType.ANSWER, NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("답변들을 모두 삭제 시 작성자가 다를 시 에러 발생")
    void test03() {
        Answers answers = new Answers();
        answers.addAnswer(createAnswer1(NsUserTest.SANJIGI));

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }


}
