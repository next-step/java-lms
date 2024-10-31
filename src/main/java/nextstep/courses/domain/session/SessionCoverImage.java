package nextstep.courses.domain.session;

import java.util.Arrays;

public class SessionCoverImage {
    private String extension;
    private int width;
    private int height;

    public SessionCoverImage(String extension, int width, int height){
        this.extension = extension;
        this.width = width;
        this.height = height;
    }

    public boolean isValidCoverImage() {
        if(!Arrays.asList("gif","jpg", "jpeg", "png", "svg").contains(extension)) {
            return false;
        }
        if(width < 300 || height < 200 || width * 2 != height * 3) {
            return false;
        }
        return true;
    }
}
