package com.project.protectpetapp.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Owner implements Serializable {
    public String oid;
    public String petId;
    public String name;
    public String phone;
    public String email;
    public String password;
}
