﻿CREATE TABLE `user_info` (
	`id`	varchar(36)	NOT NULL	COMMENT '유저 id',
	`user_pw`	varchar(36)	NULL	COMMENT '유저 비밀번호',
	`role`	varchar(30)	NULL	COMMENT '유저구분',
	`phone`	varchar(20)	NULL	COMMENT '전화번호',
	`gender`	varchar(10)	NULL	COMMENT '성별',
	`email`	varchar(100)	NULL	COMMENT '이메일',
	`cache`	INT	NULL	COMMENT '남은 캐쉬'
);

CREATE TABLE `novel` (
	`id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`title`	varchar(100)	NULL	COMMENT '소설제목',
	`author`	varchar(100)	NULL	COMMENT '소설작가',
	`like_cnt`	INT	NULL	DEFAULT 0	COMMENT '좋아요 수',
	`novel_info`	varchar(1000)	NOT NULL	COMMENT '작품소개',
	`payment_cnt`	INT	NULL	COMMENT '구매 카운트',
	`novel_img`	varchar(100)	NULL
);

CREATE TABLE `novel_episode` (
	`episode_no`	INT	NOT NULL	COMMENT '소설 회차',
	`id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`episode_title`	varchar(100)	NULL	COMMENT '에피소드 제목',
	`content`	varchar(100)	NULL	COMMENT '소설 내용',
	`page`	INT	NULL	COMMENT '총 페이지',
	`cost`	INT	NULL	COMMENT '회차별 가격',
	`capacity`	INT	NULL	COMMENT '용량'
);

CREATE TABLE `user_library` (
	`Key`	VARCHAR(255)	NOT NULL,
	`user_id`	varchar(36)	NOT NULL	COMMENT '유저 id',
	`episode_no`	INT	NOT NULL	COMMENT '소설 회차',
	`novel_id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`last_page_no`	INT	NULL	COMMENT '마지막 페이지',
	`read_date`	DATE	NULL
);

CREATE TABLE `novel_tag` (
	`id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`tag`	varchar(100)	NULL	COMMENT '소설 태그'
);

CREATE TABLE `cache_charge_his` (
	`payment_no`	varchar(36)	NULL	COMMENT '결제 번호',
	`user_id`	varchar(36)	NOT NULL	COMMENT '유저 id',
	`date`	DATE	NULL	COMMENT '결제날짜',
	`cost`	INT	NULL	COMMENT '결제 금액',
	`cache_cost`	INT	NULL	COMMENT '거래된 캐쉬 금액'
);

CREATE TABLE `user_novel_tickets` (
	`ticket_no`	varchar(36)	NOT NULL	COMMENT '이용권 번호',
	`user_id`	varchar(36)	NOT NULL	COMMENT '유저 id',
	`novel_id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`ticket_cnt`	INT	NULL	COMMENT '구매 이용권 수',
	`usable_ticket_cnt`	INT	NULL	COMMENT '사용 가능한 이용권 수',
	`used_ticket_cnt`	INT	NULL	COMMENT '사용한 이용권 수',
	`ticket_cost`	INT	NULL	COMMENT '이용권 단가',
	`date`	DATE	NULL	COMMENT '이용권 구매 날짜'
);

CREATE TABLE `child_code` (
	`code`	varchar(100)	NOT NULL	COMMENT '자식 코드',
	`parent_code`	varchar(100)	NOT NULL	COMMENT '코드',
	`value`	varchar(100)	NULL	COMMENT '값'
);

CREATE TABLE `parent_code` (
	`code`	varchar(100)	NOT NULL	COMMENT '코드',
	`value`	varchar(100)	NULL	COMMENT '값'
);

ALTER TABLE `user_info` ADD CONSTRAINT `PK_USER_INFO` PRIMARY KEY (
	`id`
);

ALTER TABLE `novel` ADD CONSTRAINT `PK_NOVEL` PRIMARY KEY (
	`id`
);

ALTER TABLE `novel_episode` ADD CONSTRAINT `PK_NOVEL_EPISODE` PRIMARY KEY (
	`episode_no`,
	`id`
);

ALTER TABLE `user_library` ADD CONSTRAINT `PK_USER_LIBRARY` PRIMARY KEY (
	`Key`,
	`user_id`,
	`episode_no`,
	`novel_id`
);

ALTER TABLE `novel_tag` ADD CONSTRAINT `PK_NOVEL_TAG` PRIMARY KEY (
	`id`
);

ALTER TABLE `cache_charge_his` ADD CONSTRAINT `PK_CACHE_CHARGE_HIS` PRIMARY KEY (
	`payment_no`,
	`user_id`
);

ALTER TABLE `user_novel_tickets` ADD CONSTRAINT `PK_USER_NOVEL_TICKETS` PRIMARY KEY (
	`ticket_no`,
	`user_id`
);

ALTER TABLE `child_code` ADD CONSTRAINT `PK_CHILD_CODE` PRIMARY KEY (
	`code`,
	`parent_code`
);

ALTER TABLE `parent_code` ADD CONSTRAINT `PK_PARENT_CODE` PRIMARY KEY (
	`code`
);

