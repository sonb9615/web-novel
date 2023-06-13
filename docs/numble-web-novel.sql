
CREATE TABLE `member` (
	`member_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '회원 정보 id',
	`nickname`	varchar(100)	NULL	COMMENT '닉네임',
	`password`	varchar(100)	NULL	COMMENT '유저 비밀번호',
	`email`	varchar(100)	NULL	COMMENT '이메일',
	`own_cache`	int	NULL	COMMENT '보유캐시',
	`role`	varchar(30)	NULL	COMMENT '유저구분',
    primary key(`member_id`)
);

CREATE TABLE `novel` (
	`novel_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '소설 id',
	`title`	varchar(100)	NULL	COMMENT '소설제목',
	`author`	varchar(100)	NULL	COMMENT '소설작가',
	`novel_info`	varchar(1000)	NOT NULL	COMMENT '작품소개',
	`payment_cnt`	bigint	NULL	DEFAULT 0 COMMENT '구매 카운트',
	`novel_img`	varchar(100)	NULL,
	`episode_cost`	INT	NULL	COMMENT '소설 이용권 가격',
	`genre_id` bigint COMMENT '장르 id',
    `reg_dt` DATE	NULL	COMMENT '등록날짜',
    `udt_dt` DATE	NULL	COMMENT '등록날짜',
    primary key(`novel_id`)
);

CREATE TABLE `episode` (
	`episode_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '에피소드 id',
	`novel_id`	bigint	NOT NULL	COMMENT '소설 id',
	`episode_no`	INT	NOT NULL	COMMENT '소설 회차',
	`episode_title`	varchar(100)	NULL	COMMENT '에피소드 제목',
	`content`	varchar(100)	NULL	COMMENT '소설 내용',
	`total_page`	INT	NULL	COMMENT '총 페이지',
	`free_yn`	boolean	NULL	DEFAULT false	COMMENT '무료 에피소드 판별',
	`capacity`	INT	NULL	COMMENT '용량',
    `reg_dt` DATE	NULL	COMMENT '등록날짜',
    `udt_dt` DATE	NULL	COMMENT '업데이트 날짜',
    primary key(`episode_id`)
);

CREATE TABLE `library` (
	`library_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT 'library id',
	`episode_id`	bigint	NULL	COMMENT '에피소드 id',
	`ticket_id`	bigint	NOT NULL	COMMENT '이용권 id',
	`member_id`	bigint	NOT NULL	COMMENT '회원 정보 id',
	`last_read_page`	INT	NULL	COMMENT '마지막 페이지',
	`last_read_date`	DATE	NULL	COMMENT '마지막 읽은 날짜',
	`star_point`	INT	NULL	COMMENT '별점',
    primary key(`library_id`)
);

CREATE TABLE `cache_charge_his` (
	`charge_his_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '결제 이력 id',
	`member`	bigint	NOT NULL	COMMENT '회원 정보 id',
	`date`	DATE	NULL	COMMENT '결제날짜',
	`cost`	int	NULL	COMMENT '결제 금액',
	`cache`	int	NULL	COMMENT '거래된 캐시',
    primary key(`charge_his_id`)
);

CREATE TABLE `novel_ticket` (
	`ticket_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '이용권 id',
	`novel_id`	varchar(36)	NOT NULL	COMMENT '소설 id',
	`member_id`	bigint	NOT NULL	COMMENT '회원 정보 id',
	`ticket_cnt`	INT	NULL	COMMENT '구매 이용권 수',
	`usable_ticket_cnt`	INT	NULL	COMMENT '사용 가능한 이용권 수',
	`used_ticket_cnt`	INT	NULL	COMMENT '사용한 이용권 수',
	`ticket_cost`	INT	NULL	COMMENT '이용권 단가',
	`reg_dt`	DATE	NULL	COMMENT '이용권 구매 날짜',
    primary key(`ticket_id`)
);

CREATE TABLE `genre` (
	`genre_id` bigint	NOT NULL AUTO_INCREMENT	COMMENT '장르 id',
	`novel_id`	bigint	NOT NULL	COMMENT '소설 id',
	`genre_name`	varchar(100)	NULL	COMMENT '장르명',
    primary key(`genre_id`)
);

CREATE TABLE `favorite_novel` (
	`favorite_novel_id`	bigint	NOT NULL	COMMENT '좋아하는 소설 목록 id',
	`member_id`	bigint	NOT NULL	COMMENT '회원 정보 id',
	`novel_id`	bigint	NOT NULL	COMMENT '소설 id',
	`genre_id`	bigint	NOT NULL	COMMENT '장르 id',
    primary key(`favorite_novel_id`)
);

create index idx_library on library (`member_id`, `episode_id`);

create unique index uq_idx_nickname on member (`nickname`);

commit;