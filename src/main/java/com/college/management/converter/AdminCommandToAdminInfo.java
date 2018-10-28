package com.college.management.converter;

import com.college.management.command.AdminInformationCommand;
import com.college.management.model.AdminInformation;
import org.springframework.core.convert.converter.Converter;

public class AdminCommandToAdminInfo implements Converter<AdminInformationCommand, AdminInformation> {
    @Override
    public AdminInformation convert(AdminInformationCommand source) {
        return null;
    }
}
