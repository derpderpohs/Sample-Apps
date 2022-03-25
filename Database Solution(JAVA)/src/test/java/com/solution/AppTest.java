package com.solution;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileReader;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.opencsv.CSVReader;

class AppTest {

    @Test
    // test number of csv records = Records output by ProcessFile
    void testProcessFileEqualRecordNumbers() {
        int count = 0;
        int noOfRecords = 0;

        try {
            String filepath = ".\\src\\test\\resources\\sales.csv";
            CSVReader csv = new CSVReader(new FileReader(filepath));
            CSVReader csvTmp = new CSVReader(new FileReader(filepath));

            String[] line;

            line = csv.readNext();
            Map<String, String> headers = new LinkedHashMap<>();

            for (String s : line) {
                s = s.trim().toUpperCase().replace(" ", "_");
                headers.put(s, s);
            }

            //skip the header
            csvTmp.readNext();
            while ((line = csvTmp.readNext()) != null) {
                count++;
            }
            List<Record[]> allRecords = App.processFile(headers, csv);

            for (Record[] arr : allRecords) {
                for (Record r : arr) {
                    if (r != null)
                        noOfRecords++;
                }
            }

            System.out.println("testing that ProcessFile method processes all lines in csv");
            assertEquals(count, noOfRecords);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    // tests when csv has empty lines only with a header
    @Test
    void testProcessFileOnlyHeaders() {
        try {
            String filepath = ".\\src\\test\\resources\\salesOnlyHeaders.csv";
            CSVReader csv = new CSVReader(new FileReader(filepath));

            String[] line;
            line = csv.readNext();
            Map<String, String> headers = new LinkedHashMap<>();

            for (String s : line) {
                s = s.trim().toUpperCase().replace(" ", "_");
                headers.put(s, s);
            }

            List<Record[]> allRecords = App.processFile(headers, csv);

            System.out.println("testing ProcessFile method with input only headers will return null");
            assertEquals(allRecords, null);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    // tests when csv has empty lines in middle
    @Test
    void testProcessFileEmptyLinesInMiddle() {
        try {
            String filepath = ".\\src\\test\\resources\\salesMiddleEmpty.csv";
            CSVReader csv = new CSVReader(new FileReader(filepath));

            String[] line;
            line = csv.readNext();
            Map<String, String> headers = new LinkedHashMap<>();

            for (String s : line) {
                s = s.trim().toUpperCase().replace(" ", "_");
                headers.put(s, s);
            }

            List<Record[]> allRecords = App.processFile(headers, csv);

            int count = 0;
            for (Record arr [] : allRecords) {
                for (Record r : arr) {
                    if (r != null)
                        count++;
                }
            }

            System.out.println("testing ProcessFile method with csv where there are empty lines in the middle");
            assertEquals(5, count);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

}
