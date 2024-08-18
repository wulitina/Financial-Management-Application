# Banking Application

This Spring Boot banking application serves as a simple yet functional platform for managing bank accounts and performing basic banking operations. The application supports creating bank accounts, fetching account details, making deposits and withdrawals, deleting accounts, transferring funds between accounts, and viewing transaction histories.



## Features
### Account Management

- Create a new Account
- Retrieve account details
- Retrieve all accounts
- Withdraw amount from an account
- Deposit amount to an account
- Delete an account
### Exception Handling

- Global and specific exception handling using @ControllerAdvice and @ExceptionHandler.
### Fund Transfer

- Transfer funds between accounts
### Transaction History

- Log transactions for deposit, withdrawal, and transfer operations
- Fetch an account's transaction history


## Running the Application
1. Clone the repository.
2. Configure the database in application.properties.
3. Run the application using your IDE or execute:
4. Use Postman or any other API client to test the REST APIs.


# Detail
- Developed REST APIs for a banking application, supporting the creation of bank accounts, fetching account details, making deposits and withdrawals, transferring funds between accounts, and viewing transaction histories.
- Provides functionalities for creating and managing bank accounts. Implements functionalities for deposits, withdrawals, and fund transfers. Offers detailed transaction history retrieval for each account.
- Utilized Spring Security and JWT (JSON Web Token) for user authentication and authorization. Demonstrates backend development skills in the financial services domain, particularly in building secure and efficient REST APIs.

![img.png](pic-readme%2Fimg.png)

# Stack
Java, Spring Boot, Spring Security, JWT, MySQL

## Spring Boot Banking Application API Testing Documentation
This document provides information for testing the REST APIs of the Spring Boot Banking Application.

- Base URL
```bash
http://localhost:8081/api/accounts
```
### Endpoints
1. Create Account
   - URL: /
   - Method: POST
   - Request Body:
```json
{
  "accountHolderName": "John Doe",
  "balance": 1000.0
}
```

- Response:
```json
{
  "id": 1,
  "accountHolderName": "John Doe",
  "balance": 1000.0
}
```
2. Get Account by ID
   - URL: /{id}
   - Method: GET
   - Path Parameter:
```json
{
    "id": 1
}
```

- Response:
```json
{
    "id": 1,
        "accountHolderName": "John Doe",
        "balance": 1000.0
}
```

3. Deposit Amount
   - URL: /{id}/deposit
   - Method: PUT
   - Path Parameter:
```json
{
    "id": 1
}
```
- Request Body:

```json
{
    "amount": 500.0
}

```
- Response:
```json
{
  "id": 1,
  "accountHolderName": "John Doe",
  "balance": 1500.0
}
```

4. Withdraw Amount
   - URL: /{id}/withdraw
   - Method: PUT
   - Path Parameter:
   ```json
   {
   "id": 1
   }
   ```
   - Request Body:
   ```json

   {
   "amount": 200.0
   }
   ```
   - Response:
   ```json
   {
   "id": 1,
   "accountHolderName": "John Doe",
   "balance": 1300.0
   }
   ```
5. Get All Accounts
   - URL: /
   - Method: GET
   - Response:
   ```json
   [
   {
   "id": 1,
   "accountHolderName": "John Doe",
   "balance": 1300.0
   }
   ]
   ```
6. Delete Account
   - URL: /{id}
   - Method: DELETE
   - Path Parameter:
   ```json
   {
   "id": 1
   }
   ```
   - Response:
   ```json
   "Account is deleted successfully!"
   ```
7. Transfer Funds
   - URL: /transfer
   - Method: POST
   - Request Body:
   ```json
   {
   "fromAccountId": 1,
   "toAccountId": 2,
   "amount": 300.0
   }
   ```
   - Response:
   ```json
   "Transfer Successfully"
   ```
8. Get Account Transactions
   - URL: /{id}/transactions
   - Method: GET
   - Path Parameter:
   ```json
   {
   "id": 1
   }
   ```
   - Response:
   ```json
   [
   {
   "id": 1,
   "accountId": 1,
   "amount": 500.0,
   "transactionType": "DEPOSIT",
   "timestamp": "2023-08-18T12:34:56"
   },
   {
   "id": 2,
   "accountId": 1,
   "amount": 200.0,
   "transactionType": "WITHDRAW",
   "timestamp": "2023-08-18T13:34:56"
   },
   {
   "id": 3,
   "accountId": 1,
   "amount": 300.0,
   "transactionType": "TRANSFER",
   "timestamp": "2023-08-18T14:34:56"
   }
   ]
   ```
### Test Scenarios
1. Create an account
- Verify the account is created with the correct details.

2. Retrieve an account by ID
- Verify the account details match the expected values.
3. Deposit funds into an account
- Verify the balance is updated correctly.
4. Withdraw funds from an account
- Verify the balance is updated correctly.
- Verify the balance cannot go below zero.
5. Retrieve all accounts
- Verify the list contains all the accounts.
6. Delete an account
- Verify the account is deleted.
7. Transfer funds between accounts
- Verify the balances of both accounts are updated correctly.
8. Retrieve transactions for an account
- Verify the transaction list contains all the correct details.