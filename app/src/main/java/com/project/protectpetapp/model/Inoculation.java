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
public class Inoculation {
    public String inoId; //접종id
    public String name; // 접종명
    public String cycle; //int로 바꾸거나 접종주기
    public String moment; //접종시기 (나이?  최초 접ㅅ종? 머라해야해)
    public String essential; //필수상태
    public String content; // 접종내용
    public String part; //접종부위
    public String count; //접종횟수

}
