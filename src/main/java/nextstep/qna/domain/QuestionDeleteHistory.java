package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionDeleteHistory {

  private final Question question;
  private final List<Answer> answers;

  public QuestionDeleteHistory(Question question, List<Answer> answers) {
    this.question = question;
    this.answers = Collections.unmodifiableList(answers);
  }

  public List<DeleteHistory> getDeleteHistories() {
    DeleteHistory questionHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());
    List<DeleteHistory> answerHistories = answers.stream()
        .map(answer -> new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()))
        .collect(Collectors.toUnmodifiableList());

    List<DeleteHistory> histories = new ArrayList<>(answers.size() + 1);
    histories.add(questionHistory);
    histories.addAll(answerHistories);

    return histories;
  }
}
