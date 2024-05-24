package br.shire42.consume.configure;

import feign.Client;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Configuration
@Slf4j
public class FeignClientConfig {

    @Value("${feign.okhttp.cache.enabled}")
    private boolean cacheEnabled;

    @Value("${feign.okhttp.cache.directory}")
    private String cacheDirectory;

    @Value("${feign.okhttp.cache.maxSize}")
    private int cacheMaxSize;

    @Bean
    public Client buildFeignClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if(cacheEnabled) {
            log.info("Feign cache enabled");
            final Cache cache = new Cache(new File(cacheDirectory), cacheMaxSize);
            builder.cache(cache);
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(10, TimeUnit.SECONDS);
        }

        final OkHttpClient okHttpClient = builder.build();
        return new feign.okhttp.OkHttpClient(okHttpClient);
    }

}
