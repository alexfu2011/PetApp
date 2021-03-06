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
public class PetInoculation {
    public String petId;
    public String inoId;
    public int inoCount; //접종차수? 횟수?
    public String inoDate; //접종일자
    public String inoNextDate; //다음접종일자
}
