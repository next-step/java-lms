package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDeleteHistory {

  private final List<DeleteHistory> deleteHistories;
  public QuestionDeleteHistory(Question question, List<Answer> answers) {
    deleteHistories = initDeleteHistories(question, answers);
  }

  public List<DeleteHistory> initDeleteHistories(Question question, List<Answer> answers) {
    DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
    List<DeleteHistory> answerHistories = answers.stream()
        .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
        .collect(Collectors.toList());

    List<DeleteHistory> histories = new ArrayList<>(answers.size() + 1);
    histories.add(questionHistory);
    histories.addAll(answerHistories);

    return histories;
  }

  public List<DeleteHistory> getDeleteHistories() {
    return deleteHistories;
  }
}
