INSERT INTO `hibernate_sequence` (`next_val`) VALUES (12);
/*
-- Query: SELECT `role`.`id`,
    `role`.`role_name`
FROM `paymybuddy_db`.`role`
LIMIT 0, 1000

-- Date: 2022-02-22 10:10
*/
INSERT INTO `Role` (`id`,`role_name`) VALUES (1,'ROLE_USER');
INSERT INTO `Role` (`id`,`role_name`) VALUES (2,'ROLE_ADMIN');

/*
-- Query: SELECT `user`.`id`,
    `user`.`create_time`,
    `user`.`email`,
    `user`.`enable`,
    `user`.`password`,
    `user`.`username`,
    `user`.`role_id`
FROM `paymybuddy_db`.`user`
LIMIT 0, 1000

-- Date: 2022-02-22 10:07
*/
INSERT INTO `User` (`id`,`create_time`,`email`,`enable`,`password`,`username`,`role_id`) VALUES (2,'2022-02-22 09:52:56.915000','Marine@gmail.com',TRUE,'$2a$10$I/oC1ZDWQudMbetc8vSBAOtjVzmnKlUGX.DzG8p5BMpvK62pXMQrW','Marine',1);
INSERT INTO `User` (`id`,`create_time`,`email`,`enable`,`password`,`username`,`role_id`) VALUES (3,'2022-02-22 09:52:56.841000','Yann@gmail.com',TRUE,'$2a$10$pQqLMRpTdn8fEdkoC9thfu5n7I8lU.8cphTCXtuPMO0KUHRy60uQK','Yann',1);
INSERT INTO `User` (`id`,`create_time`,`email`,`enable`,`password`,`username`,`role_id`) VALUES (4,'2022-02-22 09:52:56.763000','Bob@gmail.com',TRUE,'$2a$10$SvCzhzIU52zMW72R5zKnwOpbarpkTrOs29Q2d/HW5KCfNv1rLbIm6','Bob',1);
INSERT INTO `User` (`id`,`create_time`,`email`,`enable`,`password`,`username`,`role_id`) VALUES (5,'2022-02-22 09:52:56.692000','nathalie@gmail.com',TRUE,'$2a$10$C8GSVSZBk0IbdVMk39AMcOH6DlK2oDn9toOj.IGeqfB3.dTLBYhzm','Nathalie',1);
INSERT INTO `User` (`id`,`create_time`,`email`,`enable`,`password`,`username`,`role_id`) VALUES (6,'2022-02-22 09:52:56.988000','admin@gmail.com',TRUE,'$2a$10$SQC8AUYGO7ofu69JxK3jWexyw.qL8f/fNEygrL0WZelWzbR51qdSi','admin',2);

/*
-- Query: SELECT `balance`.`user_id`,
    `balance`.`amount`
FROM `paymybuddy_db`.`balance`
LIMIT 0, 1000

-- Date: 2022-02-22 10:12
*/
INSERT INTO `Balance` (`user_id`,`amount`) VALUES (2,1000);
INSERT INTO `Balance` (`user_id`,`amount`) VALUES (3,1000);
INSERT INTO `Balance` (`user_id`,`amount`) VALUES (4,1000);
INSERT INTO `Balance` (`user_id`,`amount`) VALUES (5,1000);
INSERT INTO `Balance` (`user_id`,`amount`) VALUES (6,1000);

/*
-- Query: SELECT `connections`.`id`,
    `connections`.`create_time`,
    `connections`.`note`,
    `connections`.`connection_id`,
    `connections`.`user_id`
FROM `paymybuddy_db`.`connections`
LIMIT 0, 1000

-- Date: 2022-02-22 10:10
*/
INSERT INTO `Connections` (`id`,`create_time`,`note`,`connection_id`,`user_id`) VALUES (1,'2022-02-22 09:52:57.095000',NULL,3,2);
INSERT INTO `Connections` (`id`,`create_time`,`note`,`connection_id`,`user_id`) VALUES (2,'2022-02-22 09:52:57.134000',NULL,2,3);
INSERT INTO `Connections` (`id`,`create_time`,`note`,`connection_id`,`user_id`) VALUES (3,'2022-02-22 09:52:57.136000',NULL,2,4);
INSERT INTO `Connections` (`id`,`create_time`,`note`,`connection_id`,`user_id`) VALUES (4,'2022-02-22 09:52:57.138000',NULL,2,5);
INSERT INTO `Connections` (`id`,`create_time`,`note`,`connection_id`,`user_id`) VALUES (5,'2022-02-22 09:52:57.140000',NULL,4,6);

/*
-- Query: SELECT `bank_account`.`id`,
    `bank_account`.`bank_account_number`,
    `bank_account`.`user_id`
FROM `paymybuddy_db`.`bank_account`
LIMIT 0, 1000

-- Date: 2022-02-22 10:12
*/
INSERT INTO `Bank_account` (`id`,`bank_account_number`,`user_id`) VALUES (7,'Marine\'s bank account',2);
INSERT INTO `Bank_account` (`id`,`bank_account_number`,`user_id`) VALUES (8,'Yann\'s bank account',3);
INSERT INTO `Bank_account` (`id`,`bank_account_number`,`user_id`) VALUES (9,'Bob\'s bank account',4);
INSERT INTO `Bank_account` (`id`,`bank_account_number`,`user_id`) VALUES (10,'Nathalie\'s bank account',5);
INSERT INTO `Bank_account` (`id`,`bank_account_number`,`user_id`) VALUES (11,'admin\'s bank account',6);

/*
-- Query: SELECT `company_account`.`id`,
    `company_account`.`bank_account_number`,
    `company_account`.`sold`,
    `company_account`.`payment_id`,
    `company_account`.`transfer_id`
FROM `paymybuddy_db`.`company_account`
LIMIT 0, 1000

-- Date: 2022-02-22 10:11
*/

INSERT INTO `company_account` (`id`,`bank_account_number`,`sold`,`payment_id`,`transfer_id`) VALUES (1,'company bank account',1000,NULL,NULL);

/*
-- Query: SELECT `transfer`.`id`,
    `transfer`.`transfer_amount`,
    `transfer`.`date_time`,
    `transfer`.`description`,
    `transfer`.`connection_balance_id`,
    `transfer`.`user_balance_id`
FROM `paymybuddy_db`.`transfer`
LIMIT 0, 1000

-- Date: 2022-02-22 10:09
*/
INSERT INTO `Transfer` (`id`,`transfer_amount`,`date_time`,`description`,`connection_balance_id`,`user_balance_id`) VALUES (1,84,'2022-02-22 09:52:57.170000','',3,2);
INSERT INTO `Transfer` (`id`,`transfer_amount`,`date_time`,`description`,`connection_balance_id`,`user_balance_id`) VALUES (2,87,'2022-02-22 09:52:57.173000','',2,3);
INSERT INTO `Transfer` (`id`,`transfer_amount`,`date_time`,`description`,`connection_balance_id`,`user_balance_id`) VALUES (3,74,'2022-02-22 09:52:57.175000','',2,4);
INSERT INTO `Transfer` (`id`,`transfer_amount`,`date_time`,`description`,`connection_balance_id`,`user_balance_id`) VALUES (4,62,'2022-02-22 09:52:57.177000','',2,5);
INSERT INTO `Transfer` (`id`,`transfer_amount`,`date_time`,`description`,`connection_balance_id`,`user_balance_id`) VALUES (5,51,'2022-02-22 09:52:57.180000','',4,6);

