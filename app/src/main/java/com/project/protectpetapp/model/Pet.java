package com.project.protectpetapp.model;

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
public class Pet {
    public String petId;
    public String name;
    public String birth;
    public String breed;
    public String gender;


}
