package com.college.management.converter;

import com.college.management.command.RoleCommand;
import com.college.management.model.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class RoleCommandToRole implements Converter<RoleCommand, Role>{

    @Override
    public Role convert(RoleCommand source) {
        if(source == null){
            return null;
        }

        final Role role = new Role();
        role.setId(source.getId());
        role.setName(role.getName());
        return role;
    }
}
