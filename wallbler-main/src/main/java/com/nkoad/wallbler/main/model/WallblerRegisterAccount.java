package com.nkoad.wallbler.main.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WallblerRegisterAccount {

    private String accountName;
    private String schedulerName;

}
