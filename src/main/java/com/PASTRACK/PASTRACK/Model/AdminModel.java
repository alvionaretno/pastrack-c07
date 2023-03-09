package com.PASTRACK.PASTRACK.Model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name ="admin")
public class AdminModel extends UserModel implements Serializable {
    
}
