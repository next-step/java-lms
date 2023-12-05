package nextstep.qna.domain;

import java.util.Objects;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Question {

	private Long id;

	private String title;

	private String contents;

	private NsUser writer;

	private List<Answer> answers = new ArrayList<>();

	private boolean deleted = false;

	private LocalDateTime createdDate = LocalDateTime.now();

	private LocalDateTime updatedDate;

	public Question() {
	}

	public Question(NsUser writer, String title, String contents) {
		this(0L, writer, title, contents);
	}

	public Question(Long id, NsUser writer, String title, String contents) {
		this.id = id;
		this.writer = writer;
		this.title = title;
		this.contents = contents;
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public Question setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getContents() {
		return contents;
	}

	public Question setContents(String contents) {
		this.contents = contents;
		return this;
	}

	public NsUser getWriter() {
		return writer;
	}

	public void addAnswer(Answer answer) {
		answer.toQuestion(this);
		answers.add(answer);
	}

	public DeleteHistory deleteQuestion(NsUser loginUser) {
		changeDeleted(loginUser);

        return new DeleteHistory(ContentType.QUESTION, id, writer, LocalDateTime.now());
	}

	private Question changeDeleted(NsUser loginUser) {
		validateOwner(loginUser);
		this.deleted = true;
		return this;
	}

	private void validateOwner(NsUser loginUser) {
		if (!writer.equals(loginUser)) {
			throw new CannotDeleteException("질문을 삭제할 권한이 없습니다.");
		}
	}

	public boolean isDeleted() {
		return deleted;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	@Override
	public String toString() {
		return "Question [id=" + getId() + ", title=" + title + ", contents=" + contents
				+ ", writer=" + writer + "]";
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Question question = (Question) o;
		return deleted == question.deleted && Objects.equals(id, question.id)
				&& Objects.equals(title, question.title) && Objects.equals(contents,
				question.contents) && Objects.equals(writer, question.writer)
				&& Objects.equals(answers, question.answers) && Objects.equals(
				createdDate, question.createdDate) && Objects.equals(updatedDate,
				question.updatedDate);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, contents, writer, answers, deleted, createdDate,
				updatedDate);
	}
}
