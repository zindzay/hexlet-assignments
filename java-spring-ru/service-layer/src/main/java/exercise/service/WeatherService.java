package exercise.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.HttpClient;
import exercise.dto.WeatherDto;
import exercise.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WeatherService {

    @Autowired
    CityRepository cityRepository;

    // Клиент
    HttpClient client;

    // При создании класса сервиса клиент передаётся снаружи
    // В теории это позволит заменить клиент без изменения самого сервиса
    WeatherService(HttpClient client) {
        this.client = client;
    }

    // BEGIN
    public WeatherDto getWeatherByCity(String city) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            String weather = client.get(String.format("http://weather/api/v2/cities/%s", city));
            return objectMapper.readValue(weather, WeatherDto.class);
        } catch (Exception e) {
            return null;
        }
    }
    // END
}
