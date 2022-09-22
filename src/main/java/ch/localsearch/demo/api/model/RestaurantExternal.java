package ch.localsearch.demo.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RestaurantExternal {
    @JsonProperty("displayed_what")
    private String name;
    @JsonProperty("displayed_where")
    private String address;
    @JsonProperty("opening_hours")
    private OpeningHoursExternal openingHoursExternal;
}
