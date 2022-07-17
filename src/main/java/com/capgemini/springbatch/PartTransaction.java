package com.capgemini.springbatch;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@SuppressWarnings("restriction")
//@XmlRootElement(name = "transactionRecord")
public class PartTransaction {

    @Digits(integer = 9, fraction = 0, message = "id must be an integer up to 9 digits")
    private String id;

    @NotBlank(message = "name must be a non-blank string")
    @Size(max = 50, message = "name must be a string up to 50 chars length")
    private String name;

    @Size(max = 20, message = "producer code must be a string up to 20 chars length")
    private String producerCode;

    public String getProducerCode() {
        return producerCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
