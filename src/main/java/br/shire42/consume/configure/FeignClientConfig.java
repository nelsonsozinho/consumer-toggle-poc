
package br.shire42.consume.configure;

import feign.Client;
import jakarta.servlet.MultipartConfigElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class FeignClientConfig {

    @Value("${feign.okhttp.cache.enabled}")
    private boolean cacheEnabled;

    @Value("${feign.okhttp.cache.directory}")
    private String cacheDirectory;

    @Value("${feign.okhttp.cache.maxSize}")
    private int cacheMaxSize;

    private final FeignConnectionInterceptor interceptor;

    @Bean
    public Client buildFeignClient(MultipartConfigElement multipartConfigElement) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        builder.protocols(List.of(Protocol.H2_PRIOR_KNOWLEDGE));

        if(cacheEnabled) {
            log.info("Feign cache enabled");
            final Cache cache = new Cache(new File(cacheDirectory), cacheMaxSize);

            builder.cache(cache);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.connectionPool(new ConnectionPool(10, 5, TimeUnit.MINUTES));
            builder.addInterceptor(interceptor);
        }

        final OkHttpClient okHttpClient = builder.build();

        return new feign.okhttp.OkHttpClient(okHttpClient);
    }


}
