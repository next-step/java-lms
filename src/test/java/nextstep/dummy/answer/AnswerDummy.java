package nextstep.dummy.answer;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class AnswerDummy {

    public final Question a_user_question;
    public final Answer a_answer;
    public final Answer b_answer;

    public AnswerDummy() {

        NsUser a_user = new NsUserDummy().a_user;
        NsUser b_user = new NsUserDummy().b_user;
        this.a_user_question = new Question(a_user, "title1", "contents1");
        this.a_answer = new Answer(a_user, a_user_question, "Answers Contents1");
        this.b_answer = new Answer(b_user, a_user_question, "Answers Contents2");

    }

}
