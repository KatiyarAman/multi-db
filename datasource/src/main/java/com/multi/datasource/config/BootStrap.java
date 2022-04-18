package com.multi.datasource.config;

import com.multi.datasource.repo.ExcelModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrap implements CommandLineRunner {
    @Autowired
    ExcelModelRepo excelModelRepo;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(excelModelRepo.findById("MSN153V20NDJK3").get());
    }
}
