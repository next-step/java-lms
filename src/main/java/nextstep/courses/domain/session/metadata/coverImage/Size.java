package nextstep.courses.domain.session.metadata.coverImage;

import java.util.Objects;

public class Size {
    public static final long BYTES_PER_KB = 1024;
    public static final long BYTES_PER_MB = BYTES_PER_KB * 1024;

    private final long bytes;

    private Size(long bytes) {
        if (bytes < 0) {
            throw new IllegalArgumentException("크기는 0 미만일 수 없습니다.");
        }
        if (bytes > BYTES_PER_MB) {
            throw new VolumeExceedException("크기는 1MB 미만이어야 합니다.");
        }
        this.bytes = bytes;
    }

    public static Size ofBytes(long bytes) {
        return new Size(bytes);
    }

    public static Size ofKilobytes(long kb) {
        return new Size(kb * BYTES_PER_KB);
    }

    public static Size ofMegabytes(long megabytes) {
        return new Size(megabytes * BYTES_PER_MB);
    }

    public boolean isGraterThan(Size size) {
        return this.bytes > size.bytes;
    }

    /* ───── VO 동치성 ───── */
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass())
            return false;
        Size size = (Size)o;
        return bytes == size.bytes;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(bytes);
    }

    /* Getter */
    public long getBytes() {
        return bytes;
    }
}
