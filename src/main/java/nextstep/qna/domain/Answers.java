package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answers {
	private List<Answer> answers;

	public Answers(List<Answer> answers) {
		this.answers = new ArrayList<>(answers);
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}

	public static Answers of(Answer answer) {
		return new Answers(Collections.singletonList(answer));
	}

	public List<Answer> delete(NsUser loginUser) throws UnAuthorizedException {
		return this.answers.stream().map(answer -> answer.delete(loginUser)).collect(Collectors.toList());
	}

	public List<DeleteHistory> deleteAll() {
		List<DeleteHistory> deleteHistories = new ArrayList<>();

		for (Answer answer : answers) {
			deleteHistories.add(DeleteHistory.ofAnswer(ContentType.ANSWER, answer.create(), LocalDateTime.now()));
		}

		return deleteHistories;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Answers answers1 = (Answers)o;
		return Objects.equals(answers, answers1.answers);
	}

	@Override
	public int hashCode() {
		return Objects.hash(answers);
	}

	@Override
	public String toString() {
		return "Answers{" +
			"answers=" + answers +
			'}';
	}
}
