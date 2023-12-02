package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private final Long id;

    private final QuestionInformation information;

    private final Answers answers;

    public Question(Long id, QuestionInformation information) {
        this.id = id;
        this.information = information;
        this.answers = new Answers();
    }

    public Question(Long id, QuestionInformation information, Answers answers) {
        this.id = id;
        this.information = information;
        this.answers = answers;
    }

    public Question delete(NsUser loginUser) {
        information.validateDeleted();
        information.validateWriter(loginUser);
        Answers deletedAnswers = answers.delete(loginUser);
        QuestionInformation updatedInformation = this.information.delete();
        return new Question(this.id, updatedInformation, deletedAnswers);
    }

    public List<DeletedHistory> buildHistories() {
        List<DeletedHistory> result = new ArrayList<>();
        DeletedHistory history = new DeletedHistory(ContentType.QUESTION,
                                                    this.id,
                                                    information.getWriter());
        List<DeletedHistory> histories = answers.buildHistories();
        result.add(history);
        result.addAll(histories);
        return List.copyOf(result);
    }

    public Question addAnswers(Answers answers) {
        return new Question(this.id, this.information, answers);
    }

    public boolean isDeleted() {
        return this.information.isDeleted();
    }

    public Long getId() {
        return this.id;
    }

    public NsUser getWriter() {
        return this.information.getWriter();
    }
}
