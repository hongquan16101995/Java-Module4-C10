package cg.repository;

import cg.model.City;
import cg.model.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface ICityRepository extends PagingAndSortingRepository<City, Long> {
    Page<City> findAllByCityNameContaining(Pageable pageable, String name);

    Page<City> findAllByCountry(Pageable pageable, Country country);

    void deleteAllByCountry(Country country);
}
