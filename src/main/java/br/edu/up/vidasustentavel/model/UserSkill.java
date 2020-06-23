/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.up.vidasustentavel.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 *
 * @author vinig
 */
@Entity
@Table(name = "user_skill", schema = "app")
@NamedQueries({
    @NamedQuery(name = "UserSkill.findAll", query = "SELECT u FROM UserSkill u"),
    @NamedQuery(name = "UserSkill.findByIdUserSkill", query = "SELECT u FROM UserSkill u WHERE u.idUserSkill = :idUserSkill"),
    @NamedQuery(name = "UserSkill.findByExperience", query = "SELECT u FROM UserSkill u WHERE u.experience = :experience"),
    @NamedQuery(name = "UserSkill.findByLevel", query = "SELECT u FROM UserSkill u WHERE u.level = :level")})
public class UserSkill implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_user_skill")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserSkill;
    @Basic(optional = false)
    @Column(name = "experience")
    private int experience;
    @Basic(optional = false)
    @Column(name = "level")
    private int level;
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skill;
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private User user;

    public UserSkill() {
    }

    public UserSkill(Integer idUserSkill) {
        this.idUserSkill = idUserSkill;
    }

    public UserSkill(Integer idUserSkill, int experience, int level) {
        this.idUserSkill = idUserSkill;
        this.experience = experience;
        this.level = level;
    }
    
    public UserSkill(Skill skill, User user) {
    	this.skill = skill;
    	this.user = user;
        this.experience = 0;
        this.level = 1;
    }

    public Integer getIdUserSkill() {
        return idUserSkill;
    }

    public void setIdUserSkill(Integer idUserSkill) {
        this.idUserSkill = idUserSkill;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkillId(Skill skill) {
        this.skill = skill;
    }

    public User getUser() {
        return user;
    }

    public void setUserId(User user) {
        this.user = user;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserSkill != null ? idUserSkill.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserSkill)) {
            return false;
        }
        UserSkill other = (UserSkill) object;
        if ((this.idUserSkill == null && other.idUserSkill != null) || (this.idUserSkill != null && !this.idUserSkill.equals(other.idUserSkill))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.UserSkill[ idUserSkill=" + idUserSkill + " ]";
    }
    
}
