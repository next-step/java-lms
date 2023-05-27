package nextstep.courses.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.qna.domain.generator.SimpleIdGenerator;

import java.util.Objects;

public class Image {
    private final long id;
    private String path;

    private Image(long id, String path) {

        if (id == 0L) {
            throw new IllegalArgumentException("유효하지 않는 아이디에요 :( [입력 값 : " + id + "]");
        }

        if (path == null || path.isEmpty()) {
            throw new UnAuthorizedException("이미지 경로가 입력되질 않았어요 :(");
        }

        this.id = id;
        this.path = path;
    }

    public static Image of(long id, String path) {
        return new Image(id, path);
    }

    public static Image createImage(String path) {
        long id = SimpleIdGenerator.getAndIncrement(Image.class);
        return new Image(id, path);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return id == image.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
