package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.ArrayList;
import java.util.List;

public class Answers {
    List<Answer> answers = new ArrayList<>();

    public void add(Answer answer) {
        answers.add(answer);
    }

    boolean hasOtherUsersAnswer(NsUser loginUser) {
        return answers.stream()
                .anyMatch(a -> !a.isOwner(loginUser));
    }

    public void delete() {
        answers.forEach(Answer::deleteAnswer);
    }

    public List<DeleteHistory> deletedHistories() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.forEach(a -> deleteHistories.add(a.deleteHistory()));
        return deleteHistories;
    }
}
