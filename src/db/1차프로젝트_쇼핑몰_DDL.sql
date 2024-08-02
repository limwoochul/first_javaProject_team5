DROP DATABASE IF EXISTS SHOPPING;

CREATE DATABASE IF NOT EXISTS SHOPPING;

USE SHOPPING;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
	`pr_num`	int primary key auto_increment	NOT NULL,
	`pr_name`	varchar(50)						NOT NULL,
	`pr_price`	int								NOT NULL,
	`pr_inventory`	int							NOT NULL DEFAULT 0,
	`pr_cg_num`	int								NOT NULL
);

DROP TABLE IF EXISTS `cart`;

CREATE TABLE `cart` (
	`ca_num`	int primary key auto_increment	NOT NULL,
	`ca_amount`	int								NOT NULL DEFAULT 1,
	`ca_me_id`	varchar(15)						NOT NULL,
	`ca_pr_num`	int								NOT NULL
);

DROP TABLE IF EXISTS `member`;

CREATE TABLE `member` (
	`me_id`	varchar(15) primary key	NOT NULL,
	`me_pw`	varchar(15)				NOT NULL,
	`me_name`	varchar(15)			NOT NULL,
	`me_phone`	varchar(15)			NOT NULL,
	`me_address`	varchar(100)	NOT NULL,
	`me_authority`	varchar(5)		NOT NULL DEFAULT 'USER',
	`me_answer`	varchar(15)			NOT NULL,
	`me_qu_num`	int					NOT NULL
);

DROP TABLE IF EXISTS `question`;

CREATE TABLE `question` (
	`qu_num`	int primary key auto_increment	NOT NULL,
	`qu_content`	varchar(50)					NOT NULL
);

DROP TABLE IF EXISTS `buy`;

CREATE TABLE `buy` (
	`bu_num`	int primary key auto_increment	NOT NULL,
	`bu_amount`	int								NOT NULL DEFAULT 1,
	`bu_state`	varchar(10)						NOT NULL DEFAULT '구매',
	`bu_pr_num`	int								NOT NULL,
	`bu_me_id`	varchar(15)						NOT NULL
);

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
	`cg_num`	int primary key auto_increment	NOT NULL,
	`cg_name`	varchar(10)						NOT NULL
);

ALTER TABLE `product` ADD CONSTRAINT `FK_category_TO_product_1` FOREIGN KEY (
	`pr_cg_num`
)
REFERENCES `category` (
	`cg_num`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_member_TO_cart_1` FOREIGN KEY (
	`ca_me_id`
)
REFERENCES `member` (
	`me_id`
);

ALTER TABLE `cart` ADD CONSTRAINT `FK_product_TO_cart_1` FOREIGN KEY (
	`ca_pr_num`
)
REFERENCES `product` (
	`pr_num`
);

ALTER TABLE `member` ADD CONSTRAINT `FK_question_TO_member_1` FOREIGN KEY (
	`me_qu_num`
)
REFERENCES `question` (
	`qu_num`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_product_TO_buy_1` FOREIGN KEY (
	`bu_pr_num`
)
REFERENCES `product` (
	`pr_num`
);

ALTER TABLE `buy` ADD CONSTRAINT `FK_member_TO_buy_1` FOREIGN KEY (
	`bu_me_id`
)
REFERENCES `member` (
	`me_id`
);

