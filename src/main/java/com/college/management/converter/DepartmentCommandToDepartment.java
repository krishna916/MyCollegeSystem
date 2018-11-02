package com.college.management.converter;

import com.college.management.command.DepartmentCommand;
import com.college.management.model.Department;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DepartmentCommandToDepartment implements Converter<DepartmentCommand, Department> {



    @Override
    public Department convert(DepartmentCommand source) {

        if(source == null){
            return null;
        }

        Department department = new Department();

        department.setId(source.getId());
        department.setDepartmentName(source.getDepartmentName());
        department.setDepartmentCode(source.getDepartmentCode());

        return department;
    }
}
