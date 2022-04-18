package com.multi.datasource.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "excel_data",schema = "hackathon_orm")
public class ExcelModel {
    @Id
    private String msn;
    @Column(name = "product_name")
    private String productName;
    @Column(name = "taxonomy_code")
    private String taxonomyCode;
}
