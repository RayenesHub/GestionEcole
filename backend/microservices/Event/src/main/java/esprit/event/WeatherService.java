package esprit.event;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private final String API_KEY = "9914da7109fd70c864f9ca5ecc20958e"; // Utilise la clé correcte
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    public String getWeatherByCity(String city) {
        RestTemplate restTemplate = new RestTemplate();

        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q", city)
                .queryParam("appid", API_KEY)
                .queryParam("units", "metric")
                .queryParam("lang", "fr")
                .toUriString();

        return restTemplate.getForObject(url, String.class);
    }
}
