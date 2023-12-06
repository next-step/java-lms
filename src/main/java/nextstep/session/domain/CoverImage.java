package nextstep.session.domain;

public class CoverImage {
    private Long id;
    private Long sessionId;
    private String name;
    private int size;
    private int width;
    private int height;

    public CoverImage(Long id, Long sessionId, String name, int size, int width, int height) {
        this.id = id;
        this.sessionId = sessionId;
        this.name = name;
        this.size = size;
        this.width = width;
        this.height = height;
        checkImage();
    }

    public CoverImage(Long id, String name, int size, int width, int height) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.width = width;
        this.height = height;
        checkImage();
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    private void checkImage () {
        String extension = name.split("\\.")[1];
        if(!("gif".equals(extension) || "png".equals(extension) || "jpg".equals(extension) || "jpeg".equals(extension))) {
            throw new IllegalArgumentException("gif, png, jpg, jpeg 파일만 업로드 가능합니다.");
        }
        if(size > 1024 * 1024) {
            throw new IllegalArgumentException("1MB 이하의 파일만 업로드 가능합니다.");
        }
        if(width < 300 || height < 200) {
            throw new IllegalArgumentException("300 * 200 이상의 이미지만 업로드 가능합니다.");
        }
        if((double)width/height != (double)3/2) {
            throw new IllegalArgumentException("3:2 비율의 이미지만 업로드 가능합니다.");
        }
    }
}
