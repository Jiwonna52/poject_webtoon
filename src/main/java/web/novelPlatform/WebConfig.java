package web.novelPlatform;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void  addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**") // thymleaf src에 들어갈 경로(html)
                .addResourceLocations("file:////images/"); // 실제 파일이 존재하는 경로(우분투)
    }
}
