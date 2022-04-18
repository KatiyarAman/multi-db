package com.multi.datasource.catalog.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Data
@Entity
@Table(name = "product",schema = "hackathon_catalog")
public class Product {

    private@Id Long id;
    private@Column(name = "prdouct_id") String productId;
}
