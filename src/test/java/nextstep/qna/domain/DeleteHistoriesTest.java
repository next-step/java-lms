package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUser;

class DeleteHistoriesTest {

    @Test
    void 추가() {
        // given
        final NsUser user1 = new NsUser(1L, "user1", "password1", "name1", "abc@gmail.com");
        final NsUser user2 = new NsUser(2L, "user2", "password2", "name2", "efg@gmail.com");
        final Question question = new Question(user1, "title", "contents");
        final Answer answer1 = new Answer(user2, question, "contents1");
        final Answer answer2 = new Answer(user1, question, "contents2");
        final List<Answer> answers = List.of(answer1, answer2);
        answers.forEach(question::addAnswer);

        // when
        final DeleteHistories deleteHistories = new DeleteHistories();
        deleteHistories.add(question);

        // then
        assertThat(deleteHistories.getDeleteHistories()).hasSize(3);
    }
}
