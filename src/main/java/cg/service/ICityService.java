package cg.service;

import cg.model.City;
import cg.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ICityService {
    Page<City> findAll(Pageable pageable);

    Optional<City> findOne(Long id);

    void save(City city);

    void delete(Long id);

    Page<City> findAllByName(Pageable pageable, String name);

    Page<City> findAllByCountry(Pageable pageable, Country country);
}
