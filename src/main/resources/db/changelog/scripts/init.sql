
CREATE TABLE account  (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    balance decimal DEFAULT 0.0,
    pin varchar(4),
    beneficiary_name varchar(50)
);


CREATE TABLE transaction_history (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    type enum('WITHDRAW', 'DEPOSIT', 'TRANSFER'),
    transaction_date date,
    in_account_id int,
    out_account_id int NULL,
    operation_sum decimal,
    FOREIGN KEY (in_account_id) REFERENCES account(id),
    FOREIGN KEY (out_account_id) REFERENCES account(id)
);


