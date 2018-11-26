package com.college.management.services;

import com.college.management.command.ProfessorCommand;
import com.college.management.converter.DepartmentToDepartmentCommand;
import com.college.management.converter.ProfessorToProfessorCommand;
import com.college.management.converter.UserToUserCommand;
import com.college.management.model.Professor;
import com.college.management.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
@PreAuthorize("hasRole('ROLE_PROFESSOR')")
public class ProfessorServiceImpl implements ProfessorService {

   @Autowired
   private ProfessorRepository professorRepository;

   @Autowired
   private ProfessorToProfessorCommand professorToProfessorCommand;

   @Autowired
   private DepartmentToDepartmentCommand departmentToDepartmentCommand;

   @Autowired
   private UserToUserCommand userToUserCommand;


    @Override
    public ProfessorCommand findProfessorByEmail(String email) {

        Professor professor = professorRepository.selectProfessorFromUserEmail(email);

        ProfessorCommand professorCommand = professorToProfessorCommand.convert(professor);

        professorCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(professor.getDepartment()));

        professorCommand.setUserCommand(userToUserCommand.convert(professor.getUser()));



       return professorCommand;
    }

    @Override
    public ProfessorCommand findProfessorByUserId(Long userId) {

        Professor professor = professorRepository.selectProfessorFromUserid(userId);

        ProfessorCommand professorCommand = professorToProfessorCommand.convert(professor);

        professorCommand.setUserCommand(userToUserCommand.convert(professor.getUser()));

        professorCommand.setDepartmentCommand(departmentToDepartmentCommand.convert(professor.getDepartment()));

        return professorCommand;
    }

    @Override
    public void updateProfessor(ProfessorCommand professorCommand) {



        professorRepository.updateProfessor(
                professorCommand.getFirstName(),
                professorCommand.getLastName(),
                professorCommand.getAddress(),
                professorCommand.getPhone(),
                professorCommand.getState(),
                professorCommand.getCity(),
                professorCommand.getBloodGroup(),
                professorCommand.getId()
        );


    }
}
