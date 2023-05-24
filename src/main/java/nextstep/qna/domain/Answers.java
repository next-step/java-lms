package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Answers {

    private List<Answer> answers = new ArrayList<>();

    public List<Answer> getAnswers() {
        return Collections.unmodifiableList(answers);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isAllOwnerAnswer(NsUser nsUser) {
        return answers.stream()
                .allMatch(answer -> answer.isOwner(nsUser));
    }

    public List<DeleteHistory> deleteAllAnswer(NsUser nsUser) {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            DeleteHistory deleteHistory = answer.deleteAnswer(nsUser);
            deleteHistories.add(deleteHistory);
        }
        return deleteHistories;
    }

    public int countAnswer() {
        return answers.size();
    }
}
