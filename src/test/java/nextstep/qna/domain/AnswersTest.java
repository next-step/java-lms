package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

class AnswersTest {

    @Test
    void 답변을_삭제할_떼_다른_사람이_쓴_답변이_있으면_예외가_발생한다() {
        // given
        final NsUser user1 = new NsUser(1L, "user1", "password1", "name1", "abc@gmail.com");
        final NsUser user2 = new NsUser(2L, "user2", "password2", "name2", "efg@gmail.com");
        final Question question = new Question(user1, "title", "contents");
        final Answer answer1 = new Answer(user2, question, "contents1");
        final Answer answer2 = new Answer(user1, question, "contents2");
        question.addAnswer(answer1);
        question.addAnswer(answer2);

        // when
        final Answers answers = new Answers(List.of(answer1, answer2));

        // then
        assertThatThrownBy(() -> answers.validateAnswersOwnership(user1))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
