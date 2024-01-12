package nextstep.qna.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUser;

public class Answers {
	private List<Answer> answers;

	public Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public Answers add(Answer answer) {
		this.answers.add(answer);
		return new Answers(this.answers);
	}

	public void checkAuthorization(NsUser loginUser) throws UnAuthorizedException {
		this.answers.forEach(answer -> answer.compareUser(loginUser));
	}

	public List<DeleteHistory> delete(List<DeleteHistory> deleteHistories) {
		this.answers
			.forEach(answer -> {
				answer.setDeleted(true);
				deleteHistories.add(new DeleteHistory(ContentType.ANSWER, answer, LocalDateTime.now()));
			});
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
}
