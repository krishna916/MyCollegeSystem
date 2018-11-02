package com.college.management.converter;

import com.college.management.command.DepartmentCommand;
import com.college.management.model.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentToDepartmentCommand implements Converter<Department, DepartmentCommand> {


    @Override
    public DepartmentCommand convert(Department source) {

        if(source == null){

        return null;
        }

        DepartmentCommand departmentCommand = new DepartmentCommand();

        departmentCommand.setId(source.getId());
        departmentCommand.setDepartmentName(source.getDepartmentName());
        departmentCommand.setDepartmentCode(source.getDepartmentCode());

        return departmentCommand;
    }
}
