drop table if exists tag2post;
create table tag2post (id bigint not null auto_increment, post_id bigint not null, tag_id bigint not null, primary key (id)) engine=MyISAM;
truncate table blog.tag2post;
INSERT INTO blog.tag2post  (id,post_id,tag_id)
VALUES
(1,1, 1),
(2,1, 2),
(3,1, 3),
(4,2, 1),
(5,2, 3),
(6,3, 2),
(7,4, 2),
(8,4, 3),
(9,7,1),
(10,8,2),
(11,9, 3),
(12,11, 1),
(13,11, 2),
(14,11, 3),
(15,14, 1),
(16,15, 2),
(17,16, 3),
(18,17, 3),
(19,18, 1),
(20,19, 4),
(21,3, 4),
(22,6, 4),
(23,10, 4),
(24,20, 3),
(25,20, 4),
(26,22, 2),
(27,21, 1),
(28,23, 4),
(29,25, 1),
(30,25, 2),
(31,25, 3),
(32,25, 4),
(33,26, 6);