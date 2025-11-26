package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


class AnswersTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Test
    void 답변_생성(){
        Answers answers = new Answers();
        answers.add(new Answer(NsUserTest.JAVAJIGI, Q1, "content1"));
        Assertions.assertThat(answers.getAnswers()).hasSize(1);
    }
  
}