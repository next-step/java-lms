package nextstep.dummy.answer;

import nextstep.qna.domain.Question;

public class QuestionDummy {

    private final Question a_user_question;

    public QuestionDummy() {
        this.a_user_question = new Question(
                new NsUserDummy().a_user, "title1", "contents1");
    }

    public Question getA_user_question() {
        return a_user_question;
    }
}
