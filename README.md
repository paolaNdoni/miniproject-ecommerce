# Selenium + Java - Miniproject
## Overview
This project is an automated testing framework built with Selenium WebDriver and Java. It provides a robust structure for creating and executing web UI tests with a focus on maintainability, readability, and extensibility.
# Prerequisites
Chrome/Firefox (latest versions recommended)

Java JDK 24(or at least a JVM that supports class-file version 68)

ChromeDriver

## Project Structure
miniproject-ecommerce [dem]/

├──.idea

├──screenshots

├── src/

│   ├── main/


│   │   └── java/

│   │   │  ├──InternshipProject

│   │   │   ├── ConsentCookies/        # Handling consent cookies class

│   │   │   ├── Elements/        # Elements classes

│   │   │   ├── Globals/        # Globals class

│   │   │   ├── Pages/        # Page Object classes

│   │   │   ├── Utilities/        # Utilities classes

│   │   │    ├── resources/      # Configuration properties file


│   └── test/


│       └── java/


│           └── miniProjectTest/        # Test classes

│    ├──test-output/          Html page with test reports

├── pom.xml                   # Maven dependencies

└── README.md                 # This file

│   │   │    ├── testng.xml/            #Run test 1,2

│   │   │    ├── testng2.xml/           #Run test 3,4,5,6,7,8

## WebDriver Setup
Place the appropriate WebDriver executables in the resources/drivers directory or configure the path in config.properties.

## Configuration in Globals
Edit:

Base URL

Browser type (chrome/firefox/edge)

Other environment-specific configurations

## Key Features
Page Object Model
The framework implements the Page Object Model design pattern to improve test maintenance and reduce code duplication.

The framework uses a factory design pattern to manage WebDriver instances:

Automated screenshot capture on test failure.

### Best Practices Implemented

Explicit Waits: Using WebDriverWait for better synchronization

Element Locator Strategy: Using best practices for locator selection (ID > Name > CSS > XPath)

Exception Handling: Proper exception handling for WebDriver operations

Configuration Management: Externalized configuration through properties files

Logging: Integrated logging for better debug information


