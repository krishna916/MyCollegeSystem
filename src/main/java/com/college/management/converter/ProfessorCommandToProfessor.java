package com.college.management.converter;

import com.college.management.command.ProfessorCommand;
import com.college.management.model.Professor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ProfessorCommandToProfessor implements Converter<ProfessorCommand, Professor> {
    @Override


    public Professor convert(ProfessorCommand source) {

        if(source == null){
            return null;
        }

        Professor professor = new Professor();

        professor.setId(source.getId());
        professor.setFirstName(source.getFirstName());
        professor.setLastName(source.getLastName());
        professor.setAddress(source.getAddress());
        professor.setGender(source.getGender());
        professor.setPhone(source.getPhone());
        professor.setBloodGroup(source.getBloodGroup());
        professor.setCity(source.getCity());
        professor.setState(source.getState());
        professor.setDateOfBirth(source.getDateOfBirth());


        return professor;
    }
}
