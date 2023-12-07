package nextstep.courses.service;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("imageService")
public class ImageService {
    @Resource(name = "imageRepository")
    private ImageRepository imageRepository;

    @Transactional
    public void uploadImage(String title, long imageSize, String imageType, int width, int height) {
        Image image = new Image(title, imageSize, imageType, width, height);
        imageRepository.save(image);
    }
}
