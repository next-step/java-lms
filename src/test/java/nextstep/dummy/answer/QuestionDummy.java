package nextstep.dummy.answer;

import nextstep.qna.domain.Question;

public class QuestionDummy {

    private final Question javajigiQuestion;

    public QuestionDummy() {
        this.javajigiQuestion = new Question(
                new NsUserDummy().javajigi, "title1", "contents1");
    }

    public Question getJavajigiQuestion() {
        return javajigiQuestion;
    }
}
