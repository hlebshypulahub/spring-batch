package com.capgemini.springbatch;

public class Part {
    private int id;
    private String name;
    private String producerCode;

    public String getProducerCode() {
        return producerCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }
}