package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.time.DateTimeException;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuditDTO {
    private String id;
    private String role;
}
