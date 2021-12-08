

INSERT into user (username, email, password, create_time)
 values 
 ('Bob','Bob@email.com','bobpassword',CURRENT_TIMESTAMP);
 
INSERT into bankAccount (bankAccountNumber, user_user_id)
values
('FR001234123412341234123412A', 
(SELECT user_id FROM user WHERE username = 'bob')
);

INSERT into balance (amount, user_user_id)
values
('20.00' , 1);

INSERT into transaction (datetime, transaction_amount, balance_balance_id)
values 
(CURRENT_TIMESTAMP,'10.00', 
(SELECT balance_id FROM balance JOIN user WHERE balance_id = user_id)
);



INSERT INTO errors (errormessage, transaction_transaction_id)
values
('this is an error message', 
(SELECT transaction_id FROM transaction JOIN balance WHERE transaction_id = balance_id)
);

INSERT INTO transactionStatut (statut, transaction_transaction_id)
values
(1 , 
(SELECT transaction_id FROM transaction JOIN balance WHERE transaction_id = balance_id)
 );
