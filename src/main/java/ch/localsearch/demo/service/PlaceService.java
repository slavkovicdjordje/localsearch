package ch.localsearch.demo.service;

import ch.localsearch.demo.api.SwisscomApi;
import ch.localsearch.demo.api.model.RestaurantExternal;
import ch.localsearch.demo.model.Day;
import ch.localsearch.demo.model.Restaurant;
import ch.localsearch.demo.model.WorkingHours;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private static final String HYPHEN = "-";
    private static final String CLOSED = "closed";
    private static final String OPEN = "open";
    private static final String MONDAY = "monday";
    private static final String TUESDAY = "tuesday";
    private static final String WEDNESDAY = "wednesday";
    private static final String THURSDAY = "thursday";
    private static final String FRIDAY = "friday";
    private static final String SATURDAY = "saturday";
    private static final String SUNDAY = "sunday";

    private final SwisscomApi swisscomApi;

    public Restaurant getPlace(String id) {
        RestaurantExternal restaurantExternal = swisscomApi.get(id);

        return map(restaurantExternal);
    }

    private Restaurant map(RestaurantExternal restaurantExternal) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantExternal.getName());
        restaurant.setAddress(restaurantExternal.getAddress());
        addDay(restaurant, restaurantExternal, MONDAY);
        addDay(restaurant, restaurantExternal, TUESDAY);
        addDay(restaurant, restaurantExternal, WEDNESDAY);
        addDay(restaurant, restaurantExternal, THURSDAY);
        addDay(restaurant, restaurantExternal, FRIDAY);
        addDay(restaurant, restaurantExternal, SATURDAY);
        addDay(restaurant, restaurantExternal, SUNDAY);

        return restaurant;
    }

    private void addDay(Restaurant restaurant, RestaurantExternal restaurantExternal, String dayOfWeek) {
        List<WorkingHours> workingHours = restaurantExternal.getOpeningHoursExternal().getDays().get(dayOfWeek) == null ?
                new ArrayList<>() : restaurantExternal.getOpeningHoursExternal().getDays().get(dayOfWeek)
                .stream().map(wh -> new WorkingHours(wh.getStart(), wh.getEnd())).toList();
        if (isWorkingHoursSameAsDayBefore(restaurant, workingHours)) {
            Day day = restaurant.getDays().get(restaurant.getDays().size() - 1);

            if (day.getDays().contains(HYPHEN)) {
                day.setDays(day.getDays().split(HYPHEN)[0] + HYPHEN + dayOfWeek);
            } else {
                day.setDays(day.getDays() + HYPHEN + dayOfWeek);
            }
        } else {
            Day day = new Day();
            day.setDays(dayOfWeek);
            if (workingHours.isEmpty()) {
                day.setStatus(CLOSED);
            } else {
                day.setStatus(OPEN);
            }
            day.setWorkingHours(workingHours);

            restaurant.getDays().add(day);
        }
    }

    private boolean isWorkingHoursSameAsDayBefore(Restaurant restaurant, List<WorkingHours> workingHours) {
        return !restaurant.getDays().isEmpty()
                && restaurant.getDays().get(restaurant.getDays().size() - 1).getWorkingHours() != null
                && restaurant.getDays().get(restaurant.getDays().size() - 1).getWorkingHours().equals(workingHours);
    }

}
