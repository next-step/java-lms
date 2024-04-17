package nextstep.courses.domain;

public class CoverImage {
    private Long Size;
    private FileExtension fileExtension;
    private Long width;
    private Long hight;

    public CoverImage() {
        this(0L, FileExtension.gif, 300L, 200L);
    }

    public CoverImage(Long size, FileExtension fileExtension, Long width, Long hight) {
        if(size > 1024L){
            throw new IllegalArgumentException("파일 사이즈는 1MB 이하여야 합니다");
        }

        if(!(width >= 300L && hight >= 200L && (width * 2 == hight * 3))){
            throw new IllegalArgumentException("이미지의 width는 300픽셀, height는 200픽셀 이상, width와 height의 비율은 3:2여야 한다");
        }

        Size = size;
        this.fileExtension = fileExtension;
        this.width = width;
        this.hight = hight;
    }
}
