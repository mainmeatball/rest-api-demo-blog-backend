package helloblog.dto;

import helloblog.controller.validator.ValidRoles;

import java.util.Set;

public class RolesDto {

    @ValidRoles
    private Set<String> names;

    public RolesDto() {
    }

    public RolesDto(Set<String> names) {
        this.names = names;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }

    @Override
    public String toString() {
        return "RolesDto{" +
                "names=" + names +
                '}';
    }
}