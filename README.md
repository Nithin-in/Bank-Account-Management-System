# Bank Account Management System — Batch 7

A console-based Java project for **Object-Oriented Programming through Java**. It demonstrates account management and money transactions using classes, encapsulation, abstraction, inheritance, polymorphism, and exception handling.

## Features
- Create bank accounts with automatically assigned account numbers
- Verify a four-digit PIN before account operations
- Check balances, deposit money, and withdraw money
- Transfer funds between accounts
- View transaction history
- Validate amounts and reject insufficient funds or unknown accounts

## OOP concepts demonstrated
- **Encapsulation:** account data is private and operations validate inputs.
- **Abstraction:** the console menu and `Bank` class hide internal account storage details.
- **Composition:** a `Bank` manages `BankAccount` objects.
- **Inheritance:** `DepositTransaction` and `WithdrawalTransaction` extend abstract `BankTransaction`.
- **Runtime polymorphism:** the transaction is executed through the abstract `BankTransaction` type.
- **Exception handling:** invalid transactions and missing accounts produce clear messages.

## Requirements
- JDK 17 or later
- Terminal / command prompt or a Java IDE

## Compile and run
From the repository root:

```bash
javac -d out src/*.java
java -cp out Main
```

## Run the automated tests
```bash
javac -d out src/*.java tests/*.java
java -ea -cp out BankAccountTest
```

The test harness covers account creation, deposits, invalid deposits, withdrawals, insufficient funds, transfers, PIN verification, missing accounts, and a polymorphic transaction.

## Project structure
```text
Bank-Account-Management-System/
├── src/
│   ├── Main.java
│   ├── Bank.java
│   ├── BankAccount.java
│   ├── BankTransaction.java
│   ├── DepositTransaction.java
│   └── WithdrawalTransaction.java
├── tests/
│   └── BankAccountTest.java
├── docs/
│   ├── report.tex
│   ├── report.pdf
│   └── assets/
│       └── college-logo.jpg
├── .gitignore
└── README.md
```

## Course report
The course-end report is available as [`docs/report.pdf`](docs/report.pdf), with editable LaTeX source at [`docs/report.tex`](docs/report.tex). The cover page is filled in for Batch 7: **B Nithin**, roll number **26885A0547**, course facilitator **Mr. Ganesh Deshmukh** (OOP A9601 / OOPL A9602).

## Limitations
This is an educational demonstration, not production banking software. Accounts are stored in memory and disappear when the application exits. PINs are stored as plain strings, and account listing is not protected by an administrator role. A real system needs persistent storage, secure credential hashing, authorization, audit logging, concurrency controls, and appropriate compliance controls.

## Repository
https://github.com/Nithin-in/Bank-Account-Management-System
