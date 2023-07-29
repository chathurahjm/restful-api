package org.amused.utils;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVDataBinderUtil {

    public static Iterator<Object[]> readCSV(String filePath) throws IOException {
        List<Object[]> testData = new ArrayList<>();
        try (Reader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withFirstRecordAsHeader())) {

            for (CSVRecord csvRecord : csvParser) {
                String name = csvRecord.get("name");
                String year = csvRecord.get("year");
                Double price = Double.parseDouble(csvRecord.get("price"));
                String cpuModel = csvRecord.get("CPU model");
                String color = csvRecord.get("color");
                String capacity = csvRecord.get("capacity");

                testData.add(new Object[]{name, year, price, cpuModel, color, capacity});
            }
        }

        return testData.iterator();
    }
}
