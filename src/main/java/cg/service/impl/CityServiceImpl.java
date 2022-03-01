package cg.service.impl;

import cg.model.City;
import cg.model.Country;
import cg.repository.ICityRepository;
import cg.service.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityServiceImpl implements ICityService {
    @Autowired
    private ICityRepository iCityRepository;

    @Override
    public Page<City> findAll(Pageable pageable) {
        return iCityRepository.findAll(pageable);
    }

    @Override
    public Optional<City> findOne(Long id) {
        return iCityRepository.findById(id);
    }

    @Override
    public void save(City city) {
        iCityRepository.save(city);
    }

    @Override
    public void delete(Long id) {
        iCityRepository.deleteById(id);
    }

    @Override
    public Page<City> findAllByName(Pageable pageable, String name) {
        return iCityRepository.findAllByCityNameContaining(pageable, name);
    }

    @Override
    public Page<City> findAllByCountry(Pageable pageable, Country country) {
        return iCityRepository.findAllByCountry(pageable, country);
    }
}
