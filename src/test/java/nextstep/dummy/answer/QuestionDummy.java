package nextstep.dummy.answer;

import nextstep.qna.domain.Question;

public class QuestionDummy {

    public final Question a_user_question;

    public QuestionDummy() {
        this.a_user_question = new Question(
                new NsUserDummy().a_user, "title1", "contents1");
    }

}
