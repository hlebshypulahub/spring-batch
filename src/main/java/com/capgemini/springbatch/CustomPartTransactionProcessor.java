package com.capgemini.springbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

public class CustomPartTransactionProcessor implements ItemProcessor<PartTransaction, Part> {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
    private static final Logger logger = LoggerFactory.getLogger(CustomPartTransactionProcessor.class);

    @Override
    public Part process(PartTransaction partTransaction) {

        Set<ConstraintViolation<PartTransaction>> violations = validator.validate(partTransaction);

        if (violations.isEmpty()) {
            Part part = new Part();
            part.setId(Integer.parseInt(partTransaction.getId()));
            part.setName(partTransaction.getName());
            part.setProducerCode(partTransaction.getProducerCode());
            return part;
        } else {
            for (ConstraintViolation<?> violation : violations) {
                logger.error(violation.getMessage() + ", value: " + violation.getInvalidValue());
            }
            return null;
        }
    }
}
