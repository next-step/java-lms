package nextstep.image.service;

import nextstep.image.domain.Image;
import nextstep.image.dto.CreateImageRequest;
import nextstep.image.repository.ImageRepository;

public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(CreateImageRequest request) {
        imageRepository.save(request.toEntity());
    }

    public Image findImage(Long imageId) {
        return imageRepository.findById(imageId);
    }
}
