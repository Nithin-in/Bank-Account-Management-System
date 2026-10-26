# Bank Account Management System — Batch 7

A console-based Java application for the Object-Oriented Programming through Java course.

## Features
- Create bank accounts with unique account numbers
- Verify four-digit PIN before account operations
- Check balance, deposit and withdraw money
- Transfer money between accounts
- Review transaction history
- Input validation and exception handling

## OOP concepts
- **Encapsulation:** account details are private and accessed through validated methods.
- **Abstraction:** `Bank` coordinates account operations and `Main` handles the console menu.
- **Composition:** a `Bank` manages multiple `BankAccount` objects.
- **Inheritance:** `DepositTransaction` and `WithdrawalTransaction` extend abstract `BankTransaction`.
- **Polymorphism:** account transactions execute through the abstract `BankTransaction` type.

## Requirements
JDK 17 or later.

## Compile and run
```bash
javac -d out src/*.java
java -cp out Main
```

## Run tests
```bash
javac -d out src/*.java tests/*.java
java -ea -cp out BankAccountTest
```

## Project structure
```text
src/      Java application source
 tests/   Assertion-based test harness
docs/     LaTeX report source and PDF
```

## Limitations
Educational prototype only: records are kept in memory and disappear when the application exits. It does not implement production-grade security, persistent storage, or regulatory controls.
