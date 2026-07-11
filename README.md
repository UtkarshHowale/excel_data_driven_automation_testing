# 📊 Data-Driven Testing with Selenium, TestNG & Apache POI

## 📌 Overview

This project demonstrates the implementation of **Data-Driven Testing (DDT)** using **Selenium WebDriver**, **TestNG**, and **Apache POI**.

For this practice project, login test data is maintained in an Excel file (`TestData.xlsx`). The test reads the required worksheet using a reusable `ExcelUtil` class and executes the same test with multiple data sets through TestNG's `@DataProvider`.

The current implementation uses the **SauceDemo Login** functionality to demonstrate the concept of externalized test data.

> **Note:** This project uses a public dummy application for learning purposes. In real-world applications, sensitive information such as login credentials, API keys, or secrets should not be stored in Excel files or source code. They are typically managed using secure configuration files, environment variables, secret management tools, or CI/CD pipelines.

---

## 📁 Project Structure

```text
Project
│
├── Tests
│   └── ExcelTestDataHandling.java
│
├── Utils
│   ├── ExcelUtil.java
│   └── TestDataProvider.java
│
├── TestData.xlsx
│
└── pom.xml
```

---

## 🔄 Execution Flow

```text
TestNG Test
      │
      ▼
@DataProvider
      │
      ▼
TestDataProvider
      │
      ▼
ExcelUtil
      │
      ▼
Read LoginTestData Sheet
      │
      ▼
Object[][]
      │
      ▼
Execute Login Test for Each Row
```

---

## ✅ Login Scenarios Covered

| Username      | Password     | Scenario                            |
| ------------- | ------------ | ----------------------------------- |
| standard_user | wrongPass    | Valid Username + Invalid Password   |
| wrongUser     | secret_sauce | Invalid Username + Valid Password   |
| wrongUser     | wrongPass    | Invalid Username + Invalid Password |
| standard_user | *(Blank)*    | Blank Password                      |
| *(Blank)*     | secret_sauce | Blank Username                      |
| standard_user | secret_sauce | Successful Login                    |

---

## 💡 Key Concepts Practiced

* Data-Driven Testing (DDT)
* Selenium WebDriver
* TestNG `@DataProvider`
* Apache POI for Excel handling
* Utility class for reusable Excel operations
* Explicit Waits with Selenium
* Separation of test data from test logic

---

## ♻️ Reusability

The `ExcelUtil` method accepts the worksheet name as a parameter, making the implementation reusable for different test scenarios.

As additional features are automated, new worksheets can be added to the same **`TestData.xlsx`** file. The existing utility can read them without requiring any changes to the Excel handling logic.

**Example:**

```text
TestData.xlsx
│
├── LoginTestData
├── RegistrationTestData
├── CheckoutTestData
├── ProductSearchTestData
└── AddToCartTestData
```

The existing **`TestDataProvider`** class can be extended by adding new `@DataProvider` methods that use the same `ExcelUtil` class to read different worksheets.

**Example (`TestDataProvider.java`):**

```java
@DataProvider(name = "loginData")
public Object[][] loginData() throws IOException {
    return ExcelUtil.getTestData("LoginTestData");
}

@DataProvider(name = "registrationData")
public Object[][] registrationData() throws IOException {
    return ExcelUtil.getTestData("RegistrationTestData");
}

@DataProvider(name = "checkoutData")
public Object[][] checkoutData() throws IOException {
    return ExcelUtil.getTestData("CheckoutTestData");
}
```

The corresponding test classes can then use the required DataProvider.

```java
// Login Test
@Test(dataProvider = "loginData", dataProviderClass = TestDataProvider.class)

// Registration Test
@Test(dataProvider = "registrationData", dataProviderClass = TestDataProvider.class)

// Checkout Test
@Test(dataProvider = "checkoutData", dataProviderClass = TestDataProvider.class)
```
---

## 🛠️ Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Apache POI
* Maven
