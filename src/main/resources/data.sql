

INSERT into user (username, email, password, create_time)
 values 
 ('Bob','Bob@email.com','bobpassword',CURRENT_TIMESTAMP);
 
INSERT into bankAccount (bankAccountNumber, user_id)
values
('FR001234123412341234123412A', 
(SELECT id FROM user WHERE username = 'bob')
);

INSERT into balance (amount, user_id)
values
('20.00' , 
(SELECT id FROM user WHERE username = 'bob' )
);

INSERT into transaction (datetime, transaction_amount, balance_id)
values 
(CURRENT_TIMESTAMP,'10.00', 
(SELECT balance.id FROM balance JOIN user WHERE balance.id = user.id)
);


INSERT INTO errors (errormessage, transaction_id)
values
('this is an error message', 
(SELECT transaction.id FROM transaction JOIN balance WHERE transaction.id = balance.id)
);

INSERT INTO transactionStatut (statut, transaction_id)
values
(1 , 
(SELECT transaction.id FROM transaction JOIN balance WHERE transaction.id = balance.id)
 );
