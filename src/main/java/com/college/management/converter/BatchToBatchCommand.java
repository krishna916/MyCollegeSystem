package com.college.management.converter;

import com.college.management.command.BatchCommand;
import com.college.management.model.Batch;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BatchToBatchCommand implements Converter<Batch, BatchCommand> {
    @Override
    public BatchCommand convert(Batch source) {

        if(source == null){
            return null;
        }

        BatchCommand batchCommand = new BatchCommand();

        batchCommand.setId(source.getId());
        batchCommand.setBatchCode(source.getBatchCode());
        batchCommand.setBatchLimit(String.valueOf(source.getBatchLimit()));
        batchCommand.setNumberOfStudents(source.getNumberOfStudents());

        return batchCommand;
    }
}
