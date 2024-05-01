package nextstep.sessions.domain;

public class SessionImageByte {
    public static final int MAX_BYTE = 1000;

    private final int imageByte;

    public SessionImageByte(int imageByte) {
        validateImageByte(imageByte);
        this.imageByte = imageByte;
    }

    private void validateImageByte(int imageByte) {
        if (imageByte > MAX_BYTE) {
            throw new IllegalArgumentException("이미지 크기는 1MB보다 작아야 합니다.");
        }
    }
}
