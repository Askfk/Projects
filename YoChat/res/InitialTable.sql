CREATE TABLE user {
    user_id BIGSERIAL PRIMARY KEY,
    user_name varchar(45) NOT NULL,
    user_pw varchar(255) NOT NULL,
    user_email varchar(45) NOT NULL,
    user_nick varchar(45),
    user_login BOOLEAN NOT NULL,
    user_gender varchar(30) default 'Female',
    user_age varchar(30) default '0',
    user_nation varchar(30) default 'Mars',
    user_ip varchar(45) default 'off-line',
    friendsUID varchar(255) default NUll,
    matchUID varchar(255) default NULL,
    group_id varchar(255) default NULL
};

CREATE TABLE Group {
    group_id BIGSERIA PRIMARY KEY,
    group_name VARCHAR(45) NOT NULL,
    group_members VARCHAR(100) NOT NULL,
    remark VARCHAR(255) default NULL
};

CREATE TABLE messagePrivate {
    message_id BIGSERIAL PRIMARY KEY,
    pair_id VARCHAR(45) NOT NULL,
    fromID VARCHAR(45) NOT NULL,
    toID VARCHAR(45) NOT NULL,
    message VARCHAR(255) NOT NULL,
    mess_date VARCHAR(45) NOT NULL,
    remark VARCHAR(255) default NULL
};

CREATE TABLE messageGroup {
    message_id BIGSERIAL PRIMARY KEY,
    fromID VARCHAR(45) NOT NULL,
    groupID VARCHAR(45) NOT NULL,
    message VARCHAR(255) NOT NULL,
    mess_date VARCHAR(45) NOT NULL,
    remark VARCHAR(255) default NULL
};