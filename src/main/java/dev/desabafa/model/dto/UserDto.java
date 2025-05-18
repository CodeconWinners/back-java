package dev.desabafa.model.dto;

import lombok.Data;

@Data
public class UserDto {

    private String userId;
    private String name;
    private String lastName;
    private String email;
    private String jobTitle;
    private String jobDescription;
    private Integer totalXp;
    private Long questXp;
    private Integer level;

}
