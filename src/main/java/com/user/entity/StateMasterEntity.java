package com.user.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter.Indenter;

import lombok.Data;


@Data
@Entity
@Table(name = "STATES_MASTER")
public class StateMasterEntity {


@Id
@GeneratedValue
@Column(name = "tciStateId")
private Integer stateId;


@Column(name = "tciCntryD")
private Integer cntryD;



@Column(name = "tcsStateNm")
private String stateNm;



}
