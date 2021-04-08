package com.example.demo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddressDTO {
    private String street;
    private Integer height;
    private String location;
    private String province;
    private Integer postCode;
}
