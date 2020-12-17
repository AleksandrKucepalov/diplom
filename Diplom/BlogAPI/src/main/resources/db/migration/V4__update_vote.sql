truncate table blog.post_votes;
  INSERT INTO blog.post_votes  (id,post_id,time,user_id,value)
  VALUES
  (1,1, now()-INTERVAL rand()*50 DAY,  1, -1),
  (2,1, now()-INTERVAL rand()*50 DAY,  3, -1),
  (3,1, now()-INTERVAL rand()*50 DAY,  2, 1),

  (4,2, now()-INTERVAL rand()*50 DAY,  1, 1),
  (6,2, now()-INTERVAL rand()*50 DAY,  3, 1),
  (7,2, now()-INTERVAL rand()*50 DAY,  2, 1),

  (10,3, now()-INTERVAL rand()*50 DAY,  3, -1),
  (11,3, now()-INTERVAL rand()*50 DAY,  1, -1),
  (12,3, now()-INTERVAL rand()*50 DAY,  2, -1),

  (13,4, now()-INTERVAL rand()*50 DAY,  1, 1),

  (14,5, now()-INTERVAL rand()*50 DAY,  2, -1),

  (15,6, now()-INTERVAL rand()*50 DAY,  1, 1),
  (16,6, now()-INTERVAL rand()*50 DAY,  3, 1),

  (17,7, now()-INTERVAL rand()*50 DAY,  3, 1),
  (18,7, now()-INTERVAL rand()*50 DAY,  2, 1),

  (19,8, now()-INTERVAL rand()*50 DAY,  1, 1),
  (20,8, now()-INTERVAL rand()*50 DAY,  3, -1),
  (21,8, now()-INTERVAL rand()*50 DAY,  2, 1),

  (22,9, now()-INTERVAL rand()*50 DAY,  1, 1),
  (23,9, now()-INTERVAL rand()*50 DAY,  3, 1),

  (24,10, now()-INTERVAL rand()*50 DAY,  2,-1),

  (25,11, now()-INTERVAL rand()*50 DAY,  3, -1),

  (26,12, now()-INTERVAL rand()*50 DAY,  1, 1),

  (35,13, now()-INTERVAL rand()*50 DAY,  2, 1),

  (27,14, now()-INTERVAL rand()*50 DAY,  3, 1),

  (28,15, now()-INTERVAL rand()*50 DAY,  3, -1),

  (29,16, now()-INTERVAL rand()*50 DAY,  2, -1),
  (30,16, now()-INTERVAL rand()*50 DAY,  3, -1),

  (31,20, now()-INTERVAL rand()*50 DAY,  3, 1),

  (32,21, now()-INTERVAL rand()*50 DAY,  2, 1),

  (9,23, now()-INTERVAL rand()*50 DAY,  3, 1),
  (8,25, now()-INTERVAL rand()*50 DAY,  3, 1),
  (5,27, now()-INTERVAL rand()*50 DAY,  3, -1)