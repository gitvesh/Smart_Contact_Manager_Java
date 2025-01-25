package scm.SmartContactManager.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;
@Service
public class Image_service implements imageService{

    private Cloudinary cloudinary;
    public Image_service(Cloudinary cloudinary)
    {
        this.cloudinary=cloudinary;
    }
    @Override
    public String uploadImage(MultipartFile image,String filename) throws IOException {
        //String fileName= UUID.randomUUID().toString();
        byte[] data=new byte[image.getInputStream().available()];
        image.getInputStream().read(data);
        cloudinary.uploader().upload(data, ObjectUtils.asMap(
                "public_id",filename
        ));
        return  this.getUrlFrompublicId(filename);

    }

    @Override
    public String getUrlFrompublicId(String publicid) {
        return cloudinary.url().transformation(new Transformation<>().width(500).height(500).crop("fill")).generate(publicid);
    }
}
