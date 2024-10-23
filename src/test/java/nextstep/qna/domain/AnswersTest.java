package nextstep.qna.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @Test
    @DisplayName("성공 - add 메서드가 답변을 추가한다.")
    void addTest() {
        NsUser user = new NsUser(1L, "user_id1", "password", "홍길동", "test1@a.com");
        Question question = new Question(user, "제목", "내용");

        Answer answer = new Answer(user, question, "답변1");
        Answers answers = new Answers();
        answers.add(answer);
        assertThat(answers.getAnswers()).containsExactly(answer);
    }

}