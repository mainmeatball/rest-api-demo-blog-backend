package helloblog.dto;

import helloblog.controller.validator.ValidPassword;
import helloblog.controller.validator.ValidUsername;

public class UserDto {

    @ValidUsername
    private String username;
    @ValidPassword
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}