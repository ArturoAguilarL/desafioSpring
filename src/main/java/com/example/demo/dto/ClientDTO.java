package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@AllArgsConstructor
@Getter
@Setter
public class ClientDTO {
    private Integer clientId;
    private String name;
    private String lastName;
    private Integer dni;
    private Date birth;
    private String email;
    private AddressDTO address;
}
