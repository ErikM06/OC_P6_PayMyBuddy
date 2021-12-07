

INSERT into user (username, email, password, create_time)
 values 
 ('Bob','Bob@email.com','bobpassword',CURRENT_TIMESTAMP);
 
INSERT into bankAccount (bankAccountNumber, user_user_id)
values
('FR001234123412341234123412A', user.user_id(1));

INSERT into transactions (datetime, transaction_amount, balance_balance_id)
values 
(CURRENT_TIMESTAMP,'10.00', balance_id(1));

INSERT into balance (amount, user_user_id)
values
('20.00' , user.user_id(1));

INSERT INTO errors (errormessage, transaction_transaction_id)
values
('this is an error message', transaction.transaction_id(1));

INSERT INTO transactionStatut (statut, transaction_transaction_id)
values
(('0', transaction.transaction_id(1)),('1', transaction.transaction_id(1)));
