package Project.BookMyShow.models;

import java.util.*;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass   // to make sure all the child classes will have this attribute
// @Entity          // no need to create table for this just want to have all attribute in child classes
public class BaseModle {

    @Id                  // to make id as PK
    @GeneratedValue(strategy= GenerationType.IDENTITY)  // to generate key
    private long id;
    private Date createdAt;
    private Date lastModifiedAt;
}
