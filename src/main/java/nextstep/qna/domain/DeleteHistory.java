package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;
import nextstep.users.domain.User;

import java.time.LocalDateTime;
import java.util.Objects;

public class DeleteHistory {
    private final long id;

    private final ContentType contentType;

    private final long contentId;

    private final String deletedId;

    private final LocalDateTime createdDate;

    private DeleteHistory(long id, ContentType contentType, long contentId, String deletedId, LocalDateTime createdDate) {

        if (Objects.isNull(contentType)) {
            throw new IllegalArgumentException("컨텐츠 타입에 값이 입력되질 않았어요 :(");
        }

        if (Objects.isNull(deletedId)) {
            throw new UnAuthorizedException("삭제자에 값이 입력되질 않았어요 :(");
        }

        if (deletedId.isEmpty()) {
            throw new UnAuthorizedException("삭제자에 값이 입력되질 않았어요 :(");
        }

        if (contentId == 0L) {
            throw new IllegalArgumentException("유효하지 않는 컨텐츠 아이디에요 :( [ 입력 값 : " + contentId + "]");
        }

        this.id = id;
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedId = deletedId;
        this.createdDate = createdDate;
    }

    public static DeleteHistory of(ContentType contentType, long contentId, String deletedId) {
        long id = SimpleIdGenerator.getAndIncrement(DeleteHistory.class);
        return new DeleteHistory(id, contentType, contentId, deletedId, LocalDateTime.now());
    }

    public static DeleteHistory of(long id, ContentType contentType, long contentId, String deletedId, LocalDateTime createdDate) {
        return new DeleteHistory(id, contentType, contentId, deletedId, createdDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeleteHistory that = (DeleteHistory) o;
        return id == that.id && contentId == that.contentId && contentType == that.contentType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId);
    }


    @Override
    public String toString() {
        return "DeleteHistory{" +
                "id=" + id +
                ", contentType=" + contentType +
                ", contentId=" + contentId +
                ", deletedId='" + deletedId + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
