package nextstep.qna.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

public class Answers {

	private final List<Answer> answers;

	public Answers(Answer... answers) {
		this(Arrays.stream(answers).collect(Collectors.toList()));
	}

	private Answers(List<Answer> answers) {
		this.answers = answers;
	}

	public void add(Answer answer) {
		this.answers.add(answer);
	}

	public List<DeleteHistory> delete(NsUser loginUser) {
		if (this.isOwner(loginUser) == false) {
			throw new CannotDeleteException("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
		}

		this.answers.forEach(answer -> answer.delete(true));

		return this.deleteHistories();
	}

	private boolean isOwner(NsUser loginUser) {
		return this.answers.stream().allMatch(answer -> answer.isOwner(loginUser));
	}

	private List<DeleteHistory> deleteHistories() {
		return this.answers.stream()
			.map(Answer::deleteHistory)
			.collect(Collectors.toList());
	}
}
