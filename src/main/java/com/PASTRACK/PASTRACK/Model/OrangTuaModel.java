package com.PASTRACK.PASTRACK.Model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import lombok.Getter;

import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Getter

@Entity
@Table(name="orangtua")

public class OrangTuaModel extends UserModel implements Serializable {
    @OneToMany(mappedBy = "orangtua", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<StudentModel> anak;
}
