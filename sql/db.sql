drop database if exists db_b;
create database db_b;

drop table if exists db_b.user;
create table db_b.user (
  id int auto_increment primary key ,
  email varchar(255) not null unique ,
  password varchar(64) not null
);

drop table if exists db_b.info;
create table db_b.info(
  id int auto_increment primary key ,
  username varchar(255),
  avatar varchar(255) default 'default.png',
  mobile varchar(255),
  createTime datetime,
  updateTime datetime,
  updateIp varchar(255),
  userId int comment 'user id FK'
);
drop table if exists db_b.product;
create table db_b.product(
  id int auto_increment primary key ,
  title varchar(255),
  des text,
  price decimal(10,2) not null ,
  coverPicture varchar(255) not null comment '封面图片',
  slidePictures text not null  comment '幻灯片图片组',
  detailPictures text not null comment '详情图片组',
  spec varchar(255) not null comment '规格',
  stock int not null comment '库存数量',
  status int not null default 0 comment '状态:0-1-2',
  createTime datetime not null comment '创建时间',
  updateTime datetime comment '更新时间'
)comment '产品表';

drop table if exists db_b.category;
create table db_b.category(
  id int auto_increment primary key ,
  title varchar(255) not null ,
  des varchar(255) not null comment '描述',
  parentId int comment '父类id',
  stutus int default 0 comment '状态0-1-',
  createTime datetime not null comment '创建时间',
  updateTime datetime not null comment '更新时间'
)comment '类表';

alter table db_b.info add constraint user_info_fk_userId foreign key (userId)
references db_b.user(id);

select * from db_b.user;

select * from db_b.info;

select * from db_b.product;

select * from db_b.category;

select now();
