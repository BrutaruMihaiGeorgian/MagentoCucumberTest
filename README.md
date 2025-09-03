# MagentoCucumberTest

Automated test suite for Magento, implemented in **Java + Cucumber + JUnit 5**.

This project uses **BDD (Behavior Driven Development)** with feature files and step definitions to automate critical flows in a Magento application.

---

## Table of Contents
- [Project Overview](#project-overview)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Reports](#reports)
- [Contributing](#contributing)
- [Author](#author)
- [License](#license)

---

## Project Overview
This project automates key flows in a Magento application using Cucumber feature files. Step definitions and hooks are organized into `steps` and `hooks` packages. Tests are executed via **JUnit 5** using a runner class (`RunCucumberTest`).

---

## Prerequisites
- **Java JDK 17+**  
- **Maven** (dependency management)  
- **IDE** (e.g., IntelliJ IDEA)  
- Browser driver for Selenium/WebDriver if UI automation is involved  

---

## Getting Started
1. Clone the repository:  
   ```bash
   git clone https://github.com/BrutaruMihaiGeorgian/MagentoCucumberTest.git
   ```
2. Navigate into the project:
   ```bash
   cd MagentoCucumberTest
   ```
3. Ensure Maven downloads all dependencies:
   ```bash
   mvn clean install
   ```

---

## Project Structure
```
MagentoCucumberTest/
├── src/
│   ├── main/
│   └── test/
│       ├── java/
│       │   ├── runner/        ← RunCucumberTest.java (JUnit 5 runner)
│       │   ├── steps/         ← Step definitions for Cucumber
│       │   └── hooks/         ← Hooks (before/after) for test setup
│       └── resources/
│           └── features/      ← Cucumber feature files (*.feature)
├── pom.xml                     ← Maven configuration
└── README.md
```

---

## Running Tests
Run all Cucumber tests via the runner class:
```bash
mvn test
```
or directly from your IDE by running `RunCucumberTest.java` as a JUnit test.

---

## Reports
After execution, reports are generated in the `target/` directory:
- HTML report: `target/cucumber-report.html`  
- JUnit XML: `target/cucumber-junit.xml`

---

## Contributing
Contributions are welcome! To contribute:
1. Fork the repository  
2. Create a feature branch: `git checkout -b feature/new-feature`  
3. Commit your changes with clear messages  
4. Push the branch and open a Pull Request  

---

## Author
**Mihai Brutaru**

---

## License
*(Add license, e.g., MIT, if applicable)*
