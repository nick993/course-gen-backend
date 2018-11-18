package org.learn.nick.pojos.vos;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DBProperties {
    private String address;
    private String dbName;
    private String user;
    private String password;
}