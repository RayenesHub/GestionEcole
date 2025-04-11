package com.example.school;

import com.example.school.School;
import com.example.school.SchoolService;
import com.example.school.GeolocationService;
import com.example.school.EmailValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schools")
public class SchoolController {

    private final SchoolService schoolService;
    private final GeolocationService geolocationService;
    private final EmailValidationService emailValidationService;

    @Autowired
    public SchoolController(SchoolService schoolService,
                            GeolocationService geolocationService,
                            EmailValidationService emailValidationService) {
        this.schoolService = schoolService;
        this.geolocationService = geolocationService;
        this.emailValidationService = emailValidationService;
    }

    // 🔁 CRUD classique

    @GetMapping
    public List<School> getAll() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public School getById(@PathVariable Long id) {
        return schoolService.getSchoolById(id);
    }

    @PostMapping
    public School create(@RequestBody School school) {
        return schoolService.createSchool(school);
    }

    @PutMapping("/{id}")
    public School update(@PathVariable Long id, @RequestBody School school) {
        return schoolService.updateSchool(id, school);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        schoolService.deleteSchool(id);
    }

    // 🌍 API externe 1 : Géolocalisation à partir de l'adresse de l'école
    @GetMapping("/geo/{id}")
    public ResponseEntity<String> getGeoInfo(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        String geoData = geolocationService.getCoordinates(school.getAddress());
        return ResponseEntity.ok(geoData);
    }

    // 📧 API externe 2 : Vérification de l'email de l'école
    @GetMapping("/email-check/{id}")
    public ResponseEntity<String> checkEmail(@PathVariable Long id) {
        School school = schoolService.getSchoolById(id);
        String result = emailValidationService.verifyEmail(school.getEmail());
        return ResponseEntity.ok(result);
    }
}
