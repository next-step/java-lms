package nextstep.qna.domain.history;

import nextstep.qna.domain.Question;
import nextstep.qna.domain.answer.Answer;
import nextstep.qna.domain.answer.Answers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeleteHistories {

    private List<DeleteHistory> deleteHistoryList = new ArrayList<>();

    public void addQuestion(Question question) {
        deleteHistoryList.add(new DeleteHistory().hasQuestion(question));
        Answers answers = new Answers(question.getAnswers());
        addAnswers(answers);
    }

    private void addAnswers(Answers answers) {
        for (Answer answer : answers.getAnswerList()) {
            deleteHistoryList.add(new DeleteHistory().hasAnswer(answer));
        }
    }

    public final List<DeleteHistory> getDeleteHistoryList() {
        return Collections.unmodifiableList(deleteHistoryList);
    }
}
