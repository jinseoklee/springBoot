package com.spring.accounts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by David on 2016-07-26.
 */
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private String email;
    private String fullName;
    @Temporal(TemporalType.TIMESTAMP)
    private Date joined;
    @Temporal(TemporalType.TIMESTAMP)
    private Date update;


}
