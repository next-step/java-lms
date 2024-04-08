package nextstep.courses.domain;

import nextstep.sessions.domain.Image;
import org.junit.jupiter.api.BeforeEach;

public class FreeSessionTest {

//    Session session;

    @BeforeEach
    void setUp() {
        // 강의의 시작일, 종료일
        String startDate = "20240408";
        String endDate = "20240410";

        // 강의 커버 이미지 객체
        int imageByte = 1000; // kb 단위
        String imageType = "gif";
        int imageWidth = 300;
        int imageHeight = 200;
        Image image = new Image(imageByte, imageType, imageWidth, imageHeight);

        
    }

}
