package org.amused.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;


public class Data {

    @JsonProperty("color")
    @JsonAlias("Color")
    private String color;

    @JsonProperty("capacity")
    @JsonAlias("Capacity")
    private String capacity;
    @JsonProperty("capacity GB")
    private Integer capacityGB;

    public Integer getCapacityGB() {
        return capacityGB;
    }

    public void setCapacityGB(Integer capacityGB) {
        this.capacityGB = capacityGB;
    }

    private Double price;

    @JsonAlias("Generation")
    private String generation;
    private String year;
    @JsonProperty("CPU model")
    private String CPUModel;
    @JsonProperty("Hard disk size")
    private String hardDiskSize;

    @JsonProperty("Strap Colour")
    private String strapColor;

    @JsonProperty("Case Size")
    private String caseSize;

    @JsonProperty("Description")
    private String description;

    @JsonProperty("Screen size")
    private Double screenSize;

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGeneration() {
        return generation;
    }

    public void setGeneration(String generation) {
        this.generation = generation;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getCPUModel() {
        return CPUModel;
    }

    public void setCPUModel(String CPUModel) {
        this.CPUModel = CPUModel;
    }

    public String getHardDiskSize() {
        return hardDiskSize;
    }

    public void setHardDiskSize(String hardDiskSize) {
        this.hardDiskSize = hardDiskSize;
    }

    public String getStrapColor() {
        return strapColor;
    }

    public void setStrapColor(String strapColor) {
        this.strapColor = strapColor;
    }

    public String getCaseSize() {
        return caseSize;
    }

    public void setCaseSize(String caseSize) {
        this.caseSize = caseSize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getScreenSize() {
        return screenSize;
    }

    public void setScreenSize(Double screenSize) {
        this.screenSize = screenSize;
    }
}
