package nextstep.courses.domain.session;

import lombok.Builder;
import lombok.Getter;

import java.util.Objects;

@Getter
@Builder
public class Sessioninfo {

    private final String title;

    private final Long creatorId;

    private final String coverImage;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Sessioninfo)) return false;
        final Sessioninfo that = (Sessioninfo) o;
        return Objects.equals(getTitle(), that.getTitle()) && Objects.equals(getCreatorId(), that.getCreatorId()) && Objects.equals(getCoverImage(), that.getCoverImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getCreatorId(), getCoverImage());
    }

}
