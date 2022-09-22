package ch.localsearch.demo.api;

import ch.localsearch.demo.api.model.RestaurantExternal;
import ch.localsearch.demo.configuration.SwisscomConfiguration;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class SwisscomApi {
    private final SwisscomConfiguration configuration;

    public RestaurantExternal get(String id) {
        String url = configuration.getUrl() + id;
        RestTemplate restTemplate = new RestTemplate();

        return restTemplate
                .getForEntity(url, RestaurantExternal.class)
                .getBody();
    }
}
