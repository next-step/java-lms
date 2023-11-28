package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    private List<Answer> answers;

    public Answers() {
        this.answers = new ArrayList<>();
    }

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isOwnerAllAnswers(NsUser writer) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(writer));
    }

    public List<DeleteHistory> delete() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory history = answer.delete();
            deleteHistories.add(history);
        }
        return deleteHistories;
    }
}
