package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.*;

import nextstep.users.domain.NsUser;

public class Answers {

    private List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = answers;
    }

    public void add(Answer answer) {
        answers.add(answer);
    }

    public boolean isContainOtherOwner(NsUser loginUser) {
       return answers.stream()
           .anyMatch(answer -> !answer.isOwner(loginUser));

    }

    public void delete() {
        answers.forEach(answer -> answer.delete());
    }

    public List<DeleteHistory> getDeleteHistory() {
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        for (Answer answer : answers) {
            deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        }
        return deleteHistories;
    }
}
