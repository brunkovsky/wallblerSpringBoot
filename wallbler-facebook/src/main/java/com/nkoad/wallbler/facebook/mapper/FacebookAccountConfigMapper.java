package com.nkoad.wallbler.facebook.mapper;

import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfig;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountConfigDto;
import com.nkoad.wallbler.facebook.validator.FacebookAccountValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FacebookAccountConfigMapper {

    @Autowired
    protected FacebookAccountValidator accountValidator;

    @Mapping(target = "valid", expression = "java(accountValidator.validate(accountConfigDto))")
    public abstract FacebookAccountConfig accountConfigDtoToAccountConfig(FacebookAccountConfigDto accountConfigDto);

    public abstract FacebookAccountConfigDto accountConfigToAccountConfigDto(FacebookAccountConfig accountConfig);

}
