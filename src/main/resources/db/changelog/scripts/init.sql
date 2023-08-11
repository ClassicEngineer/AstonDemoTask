
CREATE TABLE account  (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    balance decimal DEFAULT 0.0,
    pin varchar(4),
    beneficiary_name varchar(50)
);

CREATE TABLE transaction_history (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    type enum('WITHDRAW', 'DEPOSIT'),
    transaction_date datetime(6),
    account_id int NOT NULL ,
    operation_sum decimal,
    FOREIGN KEY (account_id) REFERENCES account(id)
);

CREATE TABLE transaction_transfer_history (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    transaction_date datetime(6),
    account_id int NOT NULL ,
    receive_account_id int NOT NULL,
    operation_sum decimal,
    FOREIGN KEY (account_id) REFERENCES account(id),
    FOREIGN KEY (receive_account_id) REFERENCES account(id)
);

