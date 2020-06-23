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
@Table(name = "user_task", schema = "app")
@NamedQueries({
    @NamedQuery(name = "UserTask.findAll", query = "SELECT u FROM UserTask u"),
    @NamedQuery(name = "UserTask.findByIdUserTask", query = "SELECT u FROM UserTask u WHERE u.idUserTask = :idUserTask"),
    @NamedQuery(name = "UserTask.findByStatus", query = "SELECT u FROM UserTask u WHERE u.status = :status")})
public class UserTask implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_user_task")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUserTask;
    @Column(name = "status")
    private Character status;
    @JoinColumn(name = "id_task", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Task idTask;
    @JsonIgnore
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User idUser;

    public UserTask() {
    }

    public UserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }
    
    public UserTask(User user, Task task, Character status) {
        this.idUser = user;
        this.idTask = task;
        this.status = status;
    }

    public Integer getIdUserTask() {
        return idUserTask;
    }

    public void setIdUserTask(Integer idUserTask) {
        this.idUserTask = idUserTask;
    }

    public Character getStatus() {
        return status;
    }

    public void setStatus(Character status) {
        this.status = status;
    }

    public Task getIdTask() {
        return idTask;
    }

    public void setIdTask(Task idTask) {
        this.idTask = idTask;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUserTask != null ? idUserTask.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserTask)) {
            return false;
        }
        UserTask other = (UserTask) object;
        if ((this.idUserTask == null && other.idUserTask != null) || (this.idUserTask != null && !this.idUserTask.equals(other.idUserTask))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.mycompany.mavenproject1.UserTask[ idUserTask=" + idUserTask + " ]";
    }
    
}
