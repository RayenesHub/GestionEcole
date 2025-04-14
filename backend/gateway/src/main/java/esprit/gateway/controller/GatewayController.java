package esprit.gateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import java.net.URI;


@RestController
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/api/students/**")
    public ResponseEntity<String> proxyStudentService(
            @RequestBody(required = false) String body,
            @RequestHeader HttpHeaders headers,
            HttpMethod method,
            HttpServletRequest request) {

        // Construire l'URL cible
        String path = request.getRequestURI().substring("/api/students".length());
        URI uri = UriComponentsBuilder
                .fromUriString("http://STUDENT-SERVICE/api/students" + path)
                .query(request.getQueryString())
                .build()
                .toUri();

        // Transférer la requête
        return proxyRequest(body, headers, method, uri);
    }

    @RequestMapping("/api/courses/**")
    public ResponseEntity<String> proxyCourseService(
            @RequestBody(required = false) String body,
            @RequestHeader HttpHeaders headers,
            HttpMethod method,
            HttpServletRequest request) {

        String path = request.getRequestURI().substring("/api/courses".length());
        URI uri = UriComponentsBuilder
                .fromUriString("http://COURSE-SERVICE/api/courses" + path)
                .query(request.getQueryString())
                .build()
                .toUri();

        return proxyRequest(body, headers, method, uri);
    }

    @RequestMapping("/api/teachers/**")
    public ResponseEntity<String> proxyTeacherService(
            @RequestBody(required = false) String body,
            @RequestHeader HttpHeaders headers,
            HttpMethod method,
            HttpServletRequest request) {

        String path = request.getRequestURI().substring("/api/teachers".length());
        URI uri = UriComponentsBuilder
                .fromUriString("http://TEACHER-SERVICE/api/teachers" + path)
                .query(request.getQueryString())
                .build()
                .toUri();

        return proxyRequest(body, headers, method, uri);
    }

    private ResponseEntity<String> proxyRequest(
            String body,
            HttpHeaders headers,
            HttpMethod method,
            URI uri) {

        HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
        return restTemplate.exchange(uri, method, requestEntity, String.class);
    }
}