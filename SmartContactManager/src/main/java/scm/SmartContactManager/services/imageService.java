package scm.SmartContactManager.services;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface imageService {
    String uploadImage(MultipartFile image,String filename) throws IOException;
    String getUrlFrompublicId(String publicid);

}
