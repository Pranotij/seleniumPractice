Overview

This is a Java + Selenium automation framework built with clean architecture and reusability in mind.
It separates test logic, actions, utilities, and validations to keep tests readable and maintainable.

The framework also includes GIF recording support only to visually demonstrate test execution (useful during interviews).

Project Structure (High Level)
src
├── main
│   ├── java
│   │   ├── actions      → Reusable Selenium actions & waits
│   │   ├── base         → Test setup & teardown
│   │   ├── factory      → WebDriver creation
│   │   ├── listeners    → Test listeners & reporting hooks
│   │   ├── locators     → Page locators
│   │   └── utils        → Config, logs, screenshots, GIFs
│   └── resources
│       └── config.properties
│
└── test
    └── java
        ├── tests        → Test classes
        └── validation   → Assertions & validations

Architecture Diagram
┌──────────────────┐
│   Test Classes   │
│ (TwitchTest)     │
└─────────┬────────┘
          │
          ▼
┌──────────────────┐
│ Validation Layer │
│ (Assertions)     │
└─────────┬────────┘
          │
          ▼
┌──────────────────┐
│ Action Layer     │
│ (Click, Wait,    │
│  Type, Scroll)   │
└─────────┬────────┘
          │
          ▼
┌──────────────────┐
│ Selenium WebDriver│
└─────────┬────────┘
          │
          ▼
┌──────────────────┐
│ Browser           │
└──────────────────┘

Supporting Utilities:
- Config Reader
- Logging
- Screenshot on Failure
- GIF Recorder (visual demo only)

GIF Recording (Important Note)

🎥 GIF-related classes are used only to create execution GIFs so interviewers can see the test flow visually.

Tests do not depend on GIF generation

GIFs are purely for demonstration

No impact on test pass/fail logic

Key Highlights

Clean separation of concerns

Reusable action & wait layers

Centralized validation logic

Interview-friendly structure

Easy to scale and maintain

📊 Reporting Features
The framework automatically generates:
Failure Report
Flaky Element Report
Screenshots
Test Execution GIF
These help debug unstable UI behavior.
▶️ How to Run Tests
Using Maven
mvn clean test

Using TestNG

Run:

testng.xml

🎯 Key Features
✔ Mobile browser automation
✔ Page Object Model
✔ Retry handling for unstable elements
✔ Automatic failure capture
✔ Flaky element tracking
✔ GIF recording of test execution
✔ Config-driven setup
✔ Clean layered architecture

🧠 Design Goals
Maintainable test code
Stable UI interaction
Clear separation of concerns
Easy debugging



un tests from the tests package

Check logs, screenshots, and optional GIF output
