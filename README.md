# Selenium + Java - Miniproject
## Overview
This framework contains a complete solution for testing web applications with Java and Selenium WebDriver. Every test lives in its own structured folder, and common setup tasks—like opening a browser, navigating to the site, and logging in—are handled automatically behind the scenes. When a test finds a bug, the framework captures screenshots and detailed logs so you can see what went wrong.
# Prerequisites
Chrome/Firefox (latest versions recommended)

Java JDK 18 (Oracle JDK)

Maven 3.9.9 or higher

ChromeDriver

## Project Structure
```python
miniproject-ecommerce [dem]/

├──.idea
├──screenshots
├── src/
│   ├── main/
│   │   └── java/
│   │   │  ├──InternshipProject
│   │   │  │  ├── ConsentCookies/  (Handling consent cookies class)
│   │   │  │  ├── Elements/        (Elements classes)
│   │   │  │  ├── Globals/         (Globals class)
│   │   │  │  ├── Pages/           (Page Object classes)
│   │   │  │  ├── Utilities/       (Utilities classes)
│   │   │  │  ├── resources/       (Configuration properties file)
│   └── test/
│       └── java/
│           └── miniProjectTest/ (Test classes)
│    ├──test-output/(Html page with test reports)
├── pom.xml         (Maven dependencies)
└── README.md       (This file)
├── testng.xml/     (Run test 1,2
├── testng2.xml/    (Run test 3,4,5,6,7,8)
```

## WebDriver Setup
Place the appropriate WebDriver executables in the resources/drivers directory or configure the path in config.properties.

## Configuration in Globals
Edit:

Base URL

Browser type (chrome/firefox/edge)

Other environment-specific configurations

## Technologies Used
```
Java 18
TestNG
Extent Reports
```
## How to setup this project
```
git clone https://github.com/paolaNdoni/miniproject-ecommerce.git
```
## Key Features
```
-Page Object Model(POM)
The framework implements the Page Object Model design pattern to improve test maintenance and reduce code duplication.

-Singleton Design Pattern

-The framework uses a factory design pattern to manage WebDriver instances:

-Automated screenshot capture on test failure.
```
### Best Practices Implemented

Explicit Waits: Using WebDriverWait for better synchronization

Element Locator Strategy: Using best practices for locator selection (ID > Name > CSS > XPath)

Exception Handling: Proper exception handling for WebDriver operations

Configuration Management: Externalized configuration through properties files

## Reports 
```
-Reports: After execution, a detailed HTML report will be generated at ./ExtendReport.html.
The report contains information on test cases executed, passed, failed, and skipped, along with screenshots for failed tests.
```
## Author
@paolaNdoni 

Email Address:ndonipaola@gmail.com