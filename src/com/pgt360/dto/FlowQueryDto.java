/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pgt360.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author iquispe
 */
@Setter
@Getter
@ToString
public class FlowQueryDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private long id;
    private Integer codeFlow;
    private String description;
    private Long cash;
    private Date createdDate;
    private Integer createdBy;
    private Date deletedDate;
    private Integer deletedBy;
    private Date lastModifiedDate;
    private Integer lastModifiedBy;
    private String state;
}