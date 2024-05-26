package br.shire42.consume.configure;

import lombok.extern.log4j.Log4j2;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Log4j2
public class FeignConnectionInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        log.info("Getting connection from the pool for request: {}", request.url());

        // Proceed with the request
        Response response = chain.proceed(request);

        log.info("Connection obtained from the pool for request: {}", request.url());
        return response;
    }

}
