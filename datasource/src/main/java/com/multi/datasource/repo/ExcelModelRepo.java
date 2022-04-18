package com.multi.datasource.repo;

import com.multi.datasource.entity.ExcelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelModelRepo extends JpaRepository<ExcelModel,String > {
}
