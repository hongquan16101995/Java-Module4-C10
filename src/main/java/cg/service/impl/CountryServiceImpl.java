package cg.service.impl;

import cg.model.Country;
import cg.repository.ICityRepository;
import cg.repository.ICountryRepository;
import cg.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements ICountryService {
    @Autowired
    private ICountryRepository iCountryRepository;

    @Autowired
    private ICityRepository iCityRepository;

    @Override
    public Iterable<Country> findAll() {
        return iCountryRepository.findAll();
    }

    @Override
    public Optional<Country> findOne(Long id) {
        return iCountryRepository.findById(id);
    }

    @Override
    public void save(Country country) {
        iCountryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        Optional<Country> country = iCountryRepository.findById(id);
        country.ifPresent(value -> iCityRepository.deleteAllByCountry(value));
        iCountryRepository.deleteById(id);
    }
}
