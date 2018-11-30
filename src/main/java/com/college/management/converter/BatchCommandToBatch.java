package com.college.management.converter;

import com.college.management.command.BatchCommand;
import com.college.management.model.Batch;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BatchCommandToBatch implements Converter<BatchCommand, Batch> {
    @Override
    public Batch convert(BatchCommand source) {

        if(source == null){
            return null;
        }
        Batch batch = new Batch();

        batch.setBatchCode(source.getBatchCode());
        batch.setBatchLimit(Integer.parseInt(source.getBatchLimit()));
        batch.setNumberOfStudents(source.getNumberOfStudents());


        return batch;
    }
}
