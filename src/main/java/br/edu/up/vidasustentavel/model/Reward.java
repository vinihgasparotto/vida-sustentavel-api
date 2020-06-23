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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author vinig
 */
@Entity
@Table(name = "reward", schema = "app")
@NamedQueries({
    @NamedQuery(name = "Reward.findAll", query = "SELECT r FROM Reward r"),
    @NamedQuery(name = "Reward.findByIdReward", query = "SELECT r FROM Reward r WHERE r.idReward = :idReward"),
    @NamedQuery(name = "Reward.findByXpReward", query = "SELECT r FROM Reward r WHERE r.xpReward = :xpReward")})
public class Reward implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @JsonIgnore
    @Basic(optional = false)
    @Column(name = "id_reward")
    private Integer idReward;
    @JsonProperty("xp_reward")
    @Column(name = "xp_reward")
    private Integer xpReward;
    @JsonProperty("skill")
    @JoinColumn(name = "id_skill", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Skill idSkill;
    @JsonIgnore
    @JoinColumn(name = "id_task", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task idTask;

    public Reward() {
    }

    public Reward(Integer idReward) {
        this.idReward = idReward;
    }

    public Integer getIdReward() {
        return idReward;
    }

    public void setIdReward(Integer idReward) {
        this.idReward = idReward;
    }

    public Integer getXpReward() {
        return xpReward;
    }

    public void setXpReward(Integer xpReward) {
        this.xpReward = xpReward;
    }

    public Skill getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(Skill idSkill) {
        this.idSkill = idSkill;
    }

    public Task getIdTask() {
        return idTask;
    }

    public void setIdTask(Task idTask) {
        this.idTask = idTask;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReward != null ? idReward.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reward)) {
            return false;
        }
        Reward other = (Reward) object;
        if ((this.idReward == null && other.idReward != null) || (this.idReward != null && !this.idReward.equals(other.idReward))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.Reward[ idReward=" + idReward + " ]";
    }
    
}
