package cg.service;

import cg.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAll();

    Optional<Country> findOne(Long id);

    void save(Country country);

    void delete(Long id);
}
