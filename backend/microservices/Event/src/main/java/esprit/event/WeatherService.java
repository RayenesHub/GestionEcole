package esprit.event;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class WeatherService {

    private final String API_KEY = "9914da7109fd70c864f9ca5ecc20958e"; // ta clé API OpenWeather
    private final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

    // Ville par défaut
    private final String DEFAULT_CITY = "Tunis";

    public String getWeatherByCity(String city) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .queryParam("q", city)
                .queryParam("units", "metric")
                .queryParam("appid", API_KEY)
                .toUriString();

        RestTemplate restTemplate = new RestTemplate();
        WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

        if (response != null && response.getMain() != null) {
            return "Température actuelle : " + response.getMain().getTemp() + "°C";
        } else {
            return "Météo non disponible.";
        }
    }

    // Appel avec la ville par défaut
    public String getWeatherForDefaultCity() {
        return getWeatherByCity(DEFAULT_CITY);
    }
}
