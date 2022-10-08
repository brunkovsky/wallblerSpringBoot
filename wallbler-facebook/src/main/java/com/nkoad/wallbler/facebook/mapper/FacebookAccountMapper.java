package com.nkoad.wallbler.facebook.mapper;

import com.nkoad.wallbler.facebook.model.account.FacebookAccount;
import com.nkoad.wallbler.facebook.model.account.FacebookAccountDto;
import com.nkoad.wallbler.facebook.validator.FacebookAccountValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class FacebookAccountMapper {

    @Autowired
    protected FacebookAccountValidator accountValidator;

    @Mapping(target = "valid", expression = "java(accountValidator.validate(facebookAccountDto))")
    public abstract FacebookAccount accountDtoToAccount(FacebookAccountDto facebookAccountDto);

    public abstract FacebookAccountDto accountToAccountDto(FacebookAccount facebookAccount);

}
