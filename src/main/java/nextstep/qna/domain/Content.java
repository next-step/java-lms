package nextstep.qna.domain;

import nextstep.users.domain.NsUser;

import java.util.Objects;

abstract public class Content {
    protected Long id;
    protected ContentInfo info;

    public Content() {
    }

    public Content(Long id, NsUser writer, String contents) {
        this(id, new ContentInfo(writer, contents));
    }

    public Content(Long id, ContentInfo info) {
        this.id = id;
        this.info = info;
    }

    public boolean isDeleted() {
        return info.isDeleted();
    }

    public boolean isOwner(NsUser loginUser) {
        return info.isOwner(loginUser);
    }

    public NsUser getWriter() {
        return info.getWriter();
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Content content = (Content) o;
        return Objects.equals(id, content.id) && Objects.equals(info, content.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, info);
    }
}
