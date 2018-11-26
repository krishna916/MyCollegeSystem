package com.college.management.services;

import com.college.management.command.ProfessorCommand;

public interface ProfessorService {


    ProfessorCommand findProfessorByEmail(String email);

    ProfessorCommand findProfessorByUserId(Long userId);

    void updateProfessor(ProfessorCommand professorCommand);
}
