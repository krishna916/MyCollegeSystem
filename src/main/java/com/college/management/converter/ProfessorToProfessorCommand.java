package com.college.management.converter;

import com.college.management.command.ProfessorCommand;
import com.college.management.model.Professor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessorToProfessorCommand implements Converter<Professor, ProfessorCommand> {


    @Override
    public ProfessorCommand convert(Professor source) {
        if(source == null) {
            return null;
        }

        ProfessorCommand professorCommand = new ProfessorCommand();

        professorCommand.setId(source.getId());
        professorCommand.setFirstName(source.getFirstName());
        professorCommand.setLastName(source.getLastName());
        professorCommand.setAddress(source.getAddress());
        professorCommand.setPhone(source.getPhone());
        professorCommand.setGender(source.getGender());
        professorCommand.setState(source.getState());
        professorCommand.setCity(source.getCity());
        professorCommand.setBloodGroup(source.getBloodGroup());
        professorCommand.setDateOfBirth(source.getDateOfBirth());



        return professorCommand;
    }
}
