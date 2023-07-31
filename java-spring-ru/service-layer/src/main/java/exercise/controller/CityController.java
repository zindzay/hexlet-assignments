package exercise.controller;

import exercise.dto.SearchResultDto;
import exercise.dto.WeatherDto;
import exercise.model.City;
import exercise.repository.CityRepository;
import exercise.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;


@RestController
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    private final WeatherService weatherService;

    // BEGIN
    @GetMapping(path = "/cities/{id}")
    public WeatherDto getWeatherByCityId(@PathVariable Long id) {
        City city = cityRepository.findById(id).orElseThrow(() -> new RuntimeException("City not found by id."));

        return weatherService.getWeatherByCity(city.getName());
    }

    @GetMapping(path = "/search")
    public List<SearchResultDto> searchWeatherByCity(@RequestParam(required = false) String name) {
        List<City> cities;

        if (name == null) {
            cities = cityRepository.findAllOrderBy();
        } else {
            String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
            cities = cityRepository.findCitiesByNameIsStartingWith(capitalizedName);
        }

        return cities.stream()
                .map(city -> weatherService.getWeatherByCity(city.getName()))
                .filter(Objects::nonNull)
                .map(weather -> new SearchResultDto(weather.temperature(), weather.name()))
                .toList();
    }
    // END
}

