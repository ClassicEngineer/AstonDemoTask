INSERT INTO account (id, balance, pin, beneficiary_name) VALUES (1, 1000.0, 1234, 'Oleg'),
                                                                (2, 500.0, 1111, 'Sanya'),
                                                                (3, 100.0, 8888, 'Dan');

INSERT INTO transaction_history (id, type, transaction_date, in_account_id, out_account_id, operation_sum)
VALUES (1, 'DEPOSIT', '2023-08-08', 1, NULL,  1.0),
       (2, 'TRANSFER', '2023-08-08', 2, 3,  150.0),
       (3, 'WITHDRAW', '2023-08-08', 3, NULL,  100.0);