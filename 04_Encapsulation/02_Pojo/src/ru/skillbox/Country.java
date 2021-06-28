package ru.skillbox;

public class Country {

    private String countryName;
    private long populationNumber;
    private long totalArea;
    private String capitalName;
    private boolean seaCoast;

    public Country(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public long getPopulationNumber() {
        return populationNumber;
    }

    public void setPopulationNumber(long populationNumber) {
        this.populationNumber = populationNumber;
    }

    public long getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(long totalArea) {
        this.totalArea = totalArea;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public boolean getSeaCoast() {
        return seaCoast;
    }

    public void setSeaCoast(boolean seaCoast) {
        this.seaCoast = seaCoast;
    }
}
