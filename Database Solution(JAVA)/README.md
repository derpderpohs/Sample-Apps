## Assumptions

- CSV file headers are named the same as database column values with minor conversion of spaces to underscores. e.g. csv= order id, database = ORDER_ID.
- File path to csv contained in System Environment Variable named FILE_PATH. It is an absolute file path.
- External libraries/frameworks/etc are allowed to be used.
- mySQL shcema = test and table = store_order.

## Overview

The following libraries/frameworks are used:
- Maven
- OpenCSV
- Log4j2
- Hibernate

The program reads the CSV files and inserts into database table in batches of 1000.
1000 is to avoid the insert SQL data threshold for a single operation.
Program will stop and output offending row on inserting a malformed row with invalid values.
Validation enforced by POJO class representing a record in the database with Hibernate annotations.
Server details can be reconfigured by hibernate.cfg.xml.

## Decisions & Justifications

Databse Schema and csv data are not compatible.
For example, ORDER_ID is unique field, but sales data may include multiple same ORDER_ID.

In situation where there is no defined business rules, I will take the safest option.
Stopping when encountering errors ensures that data can be rectified before entering the database proper.

Based on this logic no need to deal with specific exceptions other than exit gracefully.
So, bubble exceptions to top level instead of handling at every level.

In ideal situation, clarification of business rules for this data would be needed before development.


## Maven

I used this for dependency management and build ease.
Makes handling dependencies easier, with pom.xml centralised place for tracking project dependencies.
Simplifies build process.
I chose this over Gradle for its maturity and better support.


## OpenCSV

Provided CSV is riddled with all the malformed CSV edge cases.
As per DRY and reinventing wheel principle, used library for parsing and avoid complexity.
OpenCSV is also simple and easiest to use, straighforward tool for straighforward task.


## Log4j2

Log4j2 is used to provide logging to allow system support to determine offending rows.
Currently there is news of security risks, but fix is in 2.17.0 used in this exercise.
If better risk mitigation required, can also use alternatives like SL4J
Log4j2 used for ease as config file allows almost plug and play functionality.


## Hibernate

Hibernate used as needed ORM can negate manual validation code for the data.
Able to represent the data model allowing for easier code readibility.
Database operations with Hibernate more concise over directly using JDBC.


