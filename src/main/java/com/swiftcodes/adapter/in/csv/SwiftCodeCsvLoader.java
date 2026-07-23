package com.swiftcodes.adapter.in.csv;

import com.swiftcodes.application.port.out.SwiftCodeRepository;
import com.swiftcodes.domain.model.SwiftCode;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

@Component
class SwiftCodeCsvLoader implements CommandLineRunner {

    private final SwiftCodeRepository repository;

    @Value("${swift.csv.file}")
    private String csvFileName;

    SwiftCodeCsvLoader(SwiftCodeRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() > 0) {
            return;
        }

        InputStream inputStream = new ClassPathResource(csvFileName).getInputStream();
        try (Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {

            CSVFormat format = CSVFormat.DEFAULT.builder()
                    .setHeader()
                    .setSkipHeaderRecord(true)
                    .setTrim(true)
                    .build();

            CSVParser parser = format.parse(reader);
            for (CSVRecord record : parser) {
                SwiftCode swiftCode = new SwiftCode(
                        record.get("SWIFT CODE"),
                        record.get("CODE TYPE"),
                        record.get("NAME"),
                        record.get("ADDRESS"),
                        record.get("COUNTRY ISO2 CODE"),
                        record.get("COUNTRY NAME"),
                        record.get("TOWN NAME"),
                        record.get("TIME ZONE")
                );
                repository.save(swiftCode);
            }
        }
    }
}