package com.higgsup.base.dto;

import lombok.Data;

import java.sql.Timestamp;
import java.util.List;


@Data
public class FranchiseUserDTO {
    private Long id;
    private String email;
    private String password;
    private List<String> role;
    private Boolean active;
    private String startDate;
}
