package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

public class AnswerBody {

    private AnswerKey answerKey;
    private AnswerContent answerContent;

    public AnswerBody(Long id, NsUser writer, Question question, String contents) {
        this(new AnswerKey(id, question), new AnswerContent(writer, contents));
    }

    public AnswerBody(AnswerKey answerKey, AnswerContent answerContent) {
        this.answerKey = answerKey;
        this.answerContent = answerContent;
    }

    public boolean isOwner(NsUser writer) {
        return this.answerContent.isOwner(writer);
    }

    public Long getId() {
        return this.answerKey.getId();
    }

    public NsUser getWriter() {
        return this.answerContent.getWriter();
    }

    public Question getQuestion() {
        return this.answerKey.getQuestion();
    }

    public String getContents() {
        return this.answerContent.getContents();
    }

    public void toQuestion(Question question) {
        this.answerKey.toQuestion(question);
    }

}
