package com.excel.reader.ExcelToJson;

import com.excel.reader.controller.Api;
import com.excel.reader.engine.ExcelReader;
import com.excel.reader.repo.DatabaseService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackageClasses = {Api.class, ExcelReader.class, DatabaseService.class})
public class ExcelToJsonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExcelToJsonApplication.class, args);
	}

}
