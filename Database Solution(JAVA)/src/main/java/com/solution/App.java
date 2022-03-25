package com.solution;

import java.io.FileReader;
import java.util.*;
import com.opencsv.CSVReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class App {

    // batch size here is max Records to be processed per batch of commits.
    final static int BATCH_SIZE = 1000;
    // to track total records processed from file.
    static int TOTAL_RECORDS = 0;

    // Assume filepath in env variable
    final static String FILEPATH = System.getenv("FILE_PATH");

    static Logger LOGGER = LogManager.getLogger("App");

    public static void main(String[] args) {

        insertEntries(FILEPATH);
    }

    static void insertEntries(String filepath) {
        try {
            CSVReader csv = new CSVReader(new FileReader(filepath));
            String[] line = new String[0];
            Map<String, String> headers = new LinkedHashMap<>();
            List<Record[]> RecordBatches;

            // process header
            // asasume header names will be same, except case insensitive
            // and spaces instead of underscore
            if ((line = csv.readNext()) != null) {
                for (String s : line) {
                    s = s.trim().toUpperCase().replace(" ", "_");
                    headers.put(s, s);
                }

            }
            RecordBatches = processFile(headers, csv);
            batchInsert(RecordBatches);

            System.out.println("Successful Insertion of " + TOTAL_RECORDS + " records!");

        } catch (Exception e) {
            System.out.println("Errors Detected, please check log file!");
        } finally {
            HibernateUtil.shutdown();
            System.exit(0);
        }
    }

    // uses headers to match values and formats to hibernate DB data model
    // representation.
    // returns a Record which represents the data passed to this method.
    static Record addDataToRecord(Map<String, String> headers)
            throws Exception {

        // edge case for empty line in csv, return null if so
        Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
        boolean isLineEmpty = false;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            if (entry.getValue().equals("")) {
                isLineEmpty = true;
            } else {
                isLineEmpty = false;
                break;
            }
        }
        if (isLineEmpty) {
            return null;
        }

        Record r = new Record();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy", Locale.ENGLISH);
        r.setORDER_ID(headers.get("ORDER_ID"));

        LocalDate rawOrderDate = LocalDate.parse(headers.get("ORDER_DATE"), formatter);
        Date orderDate = Date.valueOf(rawOrderDate);
        r.setORDER_DATE(orderDate);

        LocalDate rawShipDate = LocalDate.parse(headers.get("SHIP_DATE"), formatter);
        Date Shipdate = Date.valueOf(rawShipDate);
        r.setSHIP_DATE(Shipdate);

        r.setSHIP_MODE(headers.get("SHIP_MODE"));

        int quantity = Integer.parseInt(headers.get("QUANTITY"));
        r.setQUANTITY(quantity);

        double discount = Double.parseDouble(headers.get("DISCOUNT"));
        r.setDISCOUNT(discount);

        double profit = Double.parseDouble(headers.get("PROFIT"));
        r.setPROFIT(profit);

        r.setPRODUCT_ID(headers.get("PRODUCT_ID"));
        r.setCUSTOMER_NAME(headers.get("CUSTOMER_NAME"));
        r.setCATEGORY(headers.get("CATEGORY"));
        r.setCUSTOMER_ID(headers.get("CUSTOMER_ID"));
        r.setPRODUCT_NAME(headers.get("PRODUCT_NAME"));

        return r;
    }

    // processes each line in csv to records, then making them into batches.
    // returns a List of Record Arrays. Each Record Array is a batch to be
    // processed.
    static List<Record[]> processFile(Map<String, String> headers, CSVReader csv)
            throws Exception {

        List<Record[]> RecordBatches = new ArrayList<>();
        Map<String, String> currRecord = new LinkedHashMap<>(headers);
        String[] line = null;

        Record[] batch = new Record[BATCH_SIZE];
        int batchSize = 0;

        while ((line = csv.readNext()) != null) {

            Record r = new Record();
            Iterator<Map.Entry<String, String>> it = currRecord.entrySet().iterator();
            for (String s : line) {

                Map.Entry<String, String> currHeader = it.next();
                currHeader.setValue(s);
            }
            r = addDataToRecord(currRecord);

            // if empty line in csv, move on to next line
            if (r == null) {
                continue;
            }

            if (batchSize < BATCH_SIZE) {
                batch[batchSize] = r;
                batchSize++;
            } else {
                RecordBatches.add(batch);
                batch = new Record[BATCH_SIZE];
                batchSize = 0;
                batch[batchSize++] = r;

            }
            TOTAL_RECORDS++;

        }

        //edge case where if all rows is empty; addDataToRecord() returns null for all
        if (batch[0] == null && RecordBatches.size() == 0) {
            return null;
        }

        //edge case to add when total records < batch size
        //or when number of records dont fit neatly into each batch
        if (batch[0] != null) {
            RecordBatches.add(batch);
        }
        return RecordBatches;
    }

    // commits each batch to execute inserts to prevent connection timeout
    static void batchInsert(List<Record[]> Records) throws Exception {

        for (Record[] arr : Records) {
            Session session = HibernateUtil.getFactory().openSession();
            session.beginTransaction();
            for (Record r : arr) {
                if (r != null) {
                    session.save(r);
                }
            }
            session.getTransaction().commit();
            session.close();
        }
    }
}
