package com.nkoad.wallbler.facebook.mapper;

import com.nkoad.wallbler.facebook.model.FacebookAccount;
import com.nkoad.wallbler.facebook.model.FacebookAccountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-13T23:47:48+0100",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.18 (Oracle Corporation)"
)
@Component
public class FacebookAccountMapperImpl extends FacebookAccountMapper {

    @Override
    public FacebookAccount accountDtoToAccount(FacebookAccountDto facebookAccountDto) {
        if ( facebookAccountDto == null ) {
            return null;
        }

        FacebookAccount facebookAccount = new FacebookAccount();

        facebookAccount.setAccountName( facebookAccountDto.getAccountName() );
        facebookAccount.setAccessToken( facebookAccountDto.getAccessToken() );
        facebookAccount.setGroupId( facebookAccountDto.getGroupId() );
        facebookAccount.setFacebookType( facebookAccountDto.getFacebookType() );
        facebookAccount.setExecutorScheduler( facebookAccountDto.getExecutorScheduler() );
        facebookAccount.setAccessTokenScheduler( facebookAccountDto.getAccessTokenScheduler() );

        facebookAccount.setValid( accountValidator.validate(facebookAccountDto) );

        return facebookAccount;
    }

    @Override
    public FacebookAccountDto accountToAccountDto(FacebookAccount facebookAccount) {
        if ( facebookAccount == null ) {
            return null;
        }

        FacebookAccountDto facebookAccountDto = new FacebookAccountDto();

        facebookAccountDto.setAccountName( facebookAccount.getAccountName() );
        facebookAccountDto.setAccessToken( facebookAccount.getAccessToken() );
        facebookAccountDto.setGroupId( facebookAccount.getGroupId() );
        facebookAccountDto.setFacebookType( facebookAccount.getFacebookType() );
        facebookAccountDto.setValid( facebookAccount.isValid() );
        facebookAccountDto.setAccessTokenScheduler( facebookAccount.getAccessTokenScheduler() );
        facebookAccountDto.setExecutorScheduler( facebookAccount.getExecutorScheduler() );

        return facebookAccountDto;
    }
}
