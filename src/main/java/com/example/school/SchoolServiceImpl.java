package com.example.school;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;

    public SchoolServiceImpl(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public School createSchool(School school) {
        return schoolRepository.save(school);
    }

    @Override
    @Transactional
    public School updateSchool(Long id, School school) {
        // Recherche l'entité existante dans la base de données
        School existing = schoolRepository.findById(id).orElseThrow();

        // Met à jour les propriétés de l'entité existante avec les nouvelles valeurs
        existing.setName(school.getName());
        existing.setAddress(school.getAddress());
        existing.setDirecteur(school.getDirecteur());
        existing.setTelephone(school.getTelephone());
        existing.setEmail(school.getEmail());

        // Utilise saveAndFlush() pour forcer l'attachement de l'entité et une mise à jour immédiate
        return schoolRepository.saveAndFlush(existing);
    }

    @Override
    @Transactional
    public void deleteSchool(Long id) {
        schoolRepository.deleteById(id);
    }
}
