/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.up.vidasustentavel.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
    private Integer idUserSkill;
    @Basic(optional = false)
    @Column(name = "experience")
    private int experience;
    @Basic(optional = false)
    @Column(name = "level")
    private int level;
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Skill skillId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User userId;

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

    public Skill getSkillId() {
        return skillId;
    }

    public void setSkillId(Skill skillId) {
        this.skillId = skillId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
