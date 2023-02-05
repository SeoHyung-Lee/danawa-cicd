
create table tobacco.category (
    id INT NOT NULL AUTO_INCREMENT,
    category_name varchar(255) NOT NULL,
    category_one varchar(255) NOT NULL,
    category_two varchar(255) NOT NULL,
    deleted tinyint(1),
    PRIMARY KEY (`id`)
);

create table tobacco.product (
    id INT NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    img LONGBLOB,
    show_main tinyint(1) DEFAULT false,
    deleted tinyint(1),
    PRIMARY KEY (`id`),
    INDEX `index_product_name` (`name`)
);

create table tobacco.product_category (
    id INT NOT NULL AUTO_INCREMENT,
    product_id INT,
    category_id INT,
    deleted tinyint(1),
    PRIMARY KEY (`id`)
);

create table tobacco.price_compare (
    id INT NOT NULL AUTO_INCREMENT,
    product_id INT,
    site_name varchar(255),
    site_link varchar(255),
    site_price INT,
    deleted tinyint(1),
    PRIMARY KEY (`id`)
);

create table tobacco.admin_user (
    id INT NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    user_id varchar(255) NOT NULL,
    user_pwd varchar(255) NOT NULL,
    deleted tinyint(1),
    PRIMARY KEY (`id`)
);

insert into tobacco.admin_user(name, user_id, user_pwd, deleted) values ('관리자','waning','qpdlvlvmf12!@',false);