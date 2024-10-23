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

    @Test
    @DisplayName("성공 - existOrderUser 메서드가 작성자가 아닌 다른 사람의 답변이 존재할 때 true를 반환한다.")
    void existOtherUserTest() {
        NsUser owner = new NsUser(1L, "user_id1", "password", "홍길동", "test1@a.com");
        NsUser otherUser = new NsUser(1L, "user_id1", "password", "홍길동", "test1@a.com");
        Question question = new Question(owner, "제목", "내용");
        Answer answer1 = new Answer(owner, question, "답변1");
        Answer answer2 = new Answer(owner, question, "답변2");
        Answer answer3 = new Answer(otherUser, question, "답변3");
        Answers answers = new Answers();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);

        assertThat(answers.existOtherUser(owner)).isTrue();
    }
}