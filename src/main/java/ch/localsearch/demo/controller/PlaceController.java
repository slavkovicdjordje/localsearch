package ch.localsearch.demo.controller;

import ch.localsearch.demo.model.Restaurant;
import ch.localsearch.demo.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/places")
@RequiredArgsConstructor
public class PlaceController {
    private final PlaceService placeService;

    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> get(@PathVariable String id) {
        return new ResponseEntity<>(placeService.getPlace(id), HttpStatus.OK);
    }
}
