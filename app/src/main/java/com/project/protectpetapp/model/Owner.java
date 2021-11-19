package com.project.protectpetapp.model;

import java.util.HashMap;
import java.util.Map;

public class Owner {
    public String oid;
    public String petId;
    public String name;
    public String phone;
    public String email;
    public String password;
    public Map<String, OwnerInfo> Owners = new HashMap<>();

    public static class OwnerInfo {
        public String oid;
        public String petId;
        public String name;
        public String phone;
        public String email;
        public String password;
    }
}
