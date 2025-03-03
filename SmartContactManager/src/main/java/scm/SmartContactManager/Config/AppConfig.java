package scm.SmartContactManager.Config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${cloudinary.cloud.name}")
    private String cname;
    @Value("${cloudinary.api.key}")
    private String apikey;
    @Value("${cloudinary.api.secret}")
    private String apiSecret;

    @Bean
    public Cloudinary cloudinary()
    {

        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name",cname,
                "api_key",apikey,
                "api_secret",apiSecret
        ));

    }
}
