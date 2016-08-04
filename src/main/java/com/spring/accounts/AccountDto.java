package com.spring.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by David on 2016-07-26.
 */
public class AccountDto {

    @Data
    public static class Create{
        @NotBlank @Size(min=5, max=100)
        private String username;

        @NotBlank @Size(min=5)
        private String password;
    }

    @Data
    public static class Response{
        private Long id;
        private String username;
        private String email;
        private String fullName;
        private Date joined;
        private Date update;
    }

    @AllArgsConstructor
    @Data
    public static class Update{
        private String username;
        private String password;
    }
}
