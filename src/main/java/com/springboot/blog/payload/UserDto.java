package com.springboot.blog.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.*;

@Getter
@AllArgsConstructor
public class UserDto {

    @Data
    public static class Registration {

        @NotNull
        @Pattern(regexp = "[\\w\\d]{1,30}", message = "string contains alphabet or digit with length 1 to 30")
        private String name;

        @NotNull
        @Pattern(regexp = "[\\w\\d]{1,30}", message = "string contains alphabet or digit with length 1 to 30")
        private String username;

        @NotNull
        @Email
        private String email;

        @NotBlank
        @Size(min = 2, max = 32)
        private String password;

    }

    @Data
    public static class Login{

        private String usernameOrEmail;

        @NotBlank
        @Size(min = 2, max = 32)
        private String password;
    }


}
