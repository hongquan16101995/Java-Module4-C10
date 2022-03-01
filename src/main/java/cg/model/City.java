package cg.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "city")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String cityName;

    @Min(value = 1000000)
    private Double popular;

    @Min(value = 500000)
    private Double gdp;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public City() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getPopular() {
        return popular;
    }

    public void setPopular(Double popular) {
        this.popular = popular;
    }

    public Double getGdp() {
        return gdp;
    }

    public void setGdp(Double gdp) {
        this.gdp = gdp;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
