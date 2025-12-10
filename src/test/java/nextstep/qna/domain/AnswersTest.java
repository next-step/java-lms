package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


class AnswersTest {

    @Test
    void validateDeletableUser_다른사람이_쓴_답변이_있으면_삭제불가() {
        NsUser user = NsUserTest.JAVAJIGI;
        Answer answer1 = AnswerTest.A1;
        Answer answer2 = AnswerTest.A2;

        Answers answers = new Answers(answer1, answer2);

        assertThatThrownBy(() -> answers.validateDeletableUser(user))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void deleteAll_성공() {
        Answer answer1 = AnswerTest.A1;
        Answer answer2 = AnswerTest.A2;

        Answers answers = new Answers(answer1, answer2);
        List<DeleteHistory> deleteHistories = answers.deleteAll();

        assertThat(deleteHistories).hasSize(2);
    }
}