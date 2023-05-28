package nextstep.dummy.answer;

import nextstep.qna.domain.Answer;
import nextstep.qna.domain.Question;
import nextstep.users.domain.NsUser;

public class AnswerDummy {

    public final NsUser javajigi_writer;
    public final NsUser sanjigi_writer;
    public final Question javajigi_question;
    public final Answer javajigi_answer;
    public final Answer sanjigi_answer;

    public AnswerDummy() {
        this.javajigi_writer = new NsUserDummy().javajigi;
        this.sanjigi_writer = new NsUserDummy().sanjigi;
        this.javajigi_question = new Question(javajigi_writer, "title1", "contents1");
        this.javajigi_answer = new Answer(javajigi_writer, javajigi_question, "Answers Contents1");
        this.sanjigi_answer = new Answer(sanjigi_writer, javajigi_question, "Answers Contents2");
    }

}
