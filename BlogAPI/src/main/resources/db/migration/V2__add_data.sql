truncate table blog.posts;
INSERT INTO blog.posts (id, is_active, moderator_id, moderation_status, text,time,title,user_id,view_count) VALUES
  (1, 1, 1, 'ACCEPTED', '<p>Развитие продукта сопровождается выбором - делать сверх .</p><p>Если интересно, как мы учились работать с техническим долгом, то добро пожаловать под кат.</p><h3>Немного теории</h3><figure class=""><img src="https://habrastorage.org/getpro/habr/upload_files/4da/380/8f8/4da3808f87605366d876c41ff1892e2d" alt="Технический долг справа внизу" title="Технический долг справа внизу" width="484" height="293"><figcaption>Технический долг справа внизу</figcaption></figure>',now()-INTERVAL rand()*100 DAY,'post1', 1,  floor(rand()*100)),
  (2, 0, null,'NEW', '<p>Он словно вырезан из <b>камня</b>, стоек и неподвижен в отличие от его противников.  <i>Дух и жизненная сила</i> в нём достигла <b><i>совершенства</i></b>. Но вот беда — никто не смеет принять его вызов.</p>',now()-INTERVAL rand()*100 DAY,'post2', 1,  floor(rand()*100)),
  (3, 1, null,'ACCEPTED', '<p>продукта (надежность, безопасность, скорость разработки, стабильность).</p><h3>Каждый берет, то, что ему ближе</h3><p>Когда продукт новый, техдолга у него немного. Из-за этого у нас не было какого-то механизма ранжирования задач, которые попадают в технический долг, как не было механизма для погашения, кроме собственного энтузиазма разработчиков. Принцип бойскаута - это про нас. На самом деле, всё было не столь радужно.&nbsp;</p>',now()-INTERVAL rand()*100 DAY,'post3', 1,  floor(rand()*100)),
  (4, 1, 1, 'ACCEPTED', '<h1>HTML Атрибут высота и ширина</h1><p>Изображения в HTML имеют набор атрибутов размера, который определяет ширину и высоту изображения:</p>',now()-INTERVAL rand()*100 DAY,'post4', 1,  floor(rand()*100)),
  (5, 1,  1,'DECLINED', '<p>продукта (надежность, безопасность, скорость разработки, стабильность).</p><h3>Каждый берет, то, что ему ближе</h3><p>Когда продукт новый, техдолга у него немного. Из-за этого у нас не было какого-то механизма ранжирования задач, которые попадают в технический долг, как не было механизма для погашения, кроме собственного энтузиазма разработчиков. Принцип бойскаута - это про нас. На самом деле, всё было не столь радужно.&nbsp;</p>',now()-INTERVAL rand()*100 DAY,'post5', 2,  floor(rand()*100)),
  (6, 1, null,'ACCEPTED', '<h1>HTML Подчеркнуть заголовок</h1><h2>Это заголовок 1</h2><p>Это какое-то текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p>',now()-INTERVAL rand()*100 DAY,'post6', 1,  floor(rand()*100)),
  (7, 0,  1,'ACCEPTED', '<h1>HTML Списки</h1><h2>Неупорядоченный список HTML</h2><ul><li>Кофе</li><li>Чай</li><li>Молоко</li></ul>  <h2>Упорядоченный список HTML</h2><ol><li>Кофе</li><li>Чай</li><li>Молоко</li></ol> ',now()-INTERVAL rand()*100 DAY,'post7', 2,  floor(rand()*100)),
  (8, 1, null,'ACCEPTED', '<p>Что такое технический долг?&nbsp;Технический долг - работы, если их не сделать, несут ущерб невидимый для пользователя (ручная настройка фичи, нечитаемые/отсутствие логов).</p><p>Весь техдолг был беспорядочно собран на одной доске, где было трудно понять, насколько та или иначе задача важна.&nbsp;</p><p>К сожалению, гасился техдолг не столько в рамках обычных задач, сколько в рамках дежурства - это когда один из разработчиков команды делает не продуктовые задачи, а отвечает на вопросы других команд или делает какие-то срочные задачи, не связанные с развитием продукта.</p><p>Исходя из этих параметров, мы можем посчитать условное TechDebt Value, на основе которого задачи ранжируются (чем больше, тем важнее).</p><figure class="full-width "><img src="https://habrastorage.org/getpro/habr/upload_files/547/731/b4d/547731b4de04e77a8d88da00b462a627" width="1252" height="136"><figcaption></figcaption></figure><p>В формуле можно заметить коэффициенты X, Y и Z. Они нужны для того, чтобы в каждый момент времени мы могли повлиять на финальную оценку - если для текущего состояния продукта нам больше важна скорость доставки, то сделать X больше чем Y и Z и всё в таком духе.</p><p>Таким образом, мы смогли отсортировать задачи на доске технического долга от наиболее важных, до наименее важных.</p><p>Самое сложное в этом процессе - собрать команду и вместе оценить все задачи техдолга по каждому критерию.</p><p>А что же с гашением? Здесь постарались договориться с нашим владельцем продукта, чтобы часть задач технического долга делалась в рамках спринта, на равне с продуктовыми задачами.&nbsp;</p>',now()-INTERVAL rand()*100 DAY,'post8', 1,  floor(rand()*100)),
  (9, 1, null, 'NEW', '<p>продукта (надежность, безопасность, скорость разработки, стабильность).</p><h3>Каждый берет, то, что ему ближе</h3><p>Когда продукт новый, техдолга у него немного. Из-за этого у нас не было какого-то механизма ранжирования задач, которые попадают в технический долг, как не было механизма для погашения, кроме собственного энтузиазма разработчиков. Принцип бойскаута - это про нас. На самом деле, всё было не столь радужно.&nbsp;</p>',now()-INTERVAL rand()*100 DAY,'post9', 2,  floor(rand()*100)),
  (10, 1, null,'ACCEPTED', '<h1>HTML Атрибут высота и ширина</h1><p>Изображения в HTML имеют набор атрибутов размера, который определяет ширину и высоту изображения:</p>',now()-INTERVAL rand()*100 DAY,'post10', 1,  floor(rand()*100)),
  (11, 1, null,'DECLINED', '<h1>HTML Списки</h1><h2>Неупорядоченный список HTML</h2><ul><li>Кофе</li><li>Чай</li><li>Молоко</li></ul>  <h2>Упорядоченный список HTML</h2><ol><li>Кофе</li><li>Чай</li><li>Молоко</li></ol> ',now()-INTERVAL rand()*100 DAY,'post11', 1,  floor(rand()*100)),
  (12, 1, null,'ACCEPTED', '<h1>HTML Списки</h1><h2>Неупорядоченный список HTML</h2><ul><li>Кофе</li><li>Чай</li><li>Молоко</li></ul>  <h2>Упорядоченный список HTML</h2><ol><li>Кофе</li><li>Чай</li><li>Молоко</li></ol> ',now()-INTERVAL rand()*100 DAY,'post12', 1,  floor(rand()*100)),
  (13, 0, null,'NEW', '<p>Из-за отсутствия механизма ранжирования задач, каждый брал то, что интересно ему. А из-за отсутствия процесса, который позволял бы задачам дойти до боевых серверов, такие задачи часто застревали где-то в районе code-review или тестирования (ведь основные задачи всегда более приоритетны).</p><h3>Собственный велосипед для ранжирования</h3><p>Мы не стали брать какую-то готовую систему оценки, которую кроме того, что применить, нужно еще и подстроить для себя, а сделали собственную.</p><p>Каждую из задач технического долга мы оценивали по 4 критериям:</p><ul><li><p>Повторяемость - как часто мы сталкиваемся с этой проблемой. (0 - очень редко, раз в полгода, 5 - каждый день)</p></li><li><p>Скорость доставки - как данная задача повлияет на скорость доставки других фичей. (скорость разработки, время тестирования, время деплоймента, предсказуемость разработки) (0 - не влияет, 5 - экономит очень много времени)</p></li><li><p>Влияние на время оперирования (расходы на эксплуатацию, нагрузка на сервер, безопасность) (0 - не влияет, 5 серьезно снижает расходы и риски оперирования)</p></li><li><p>Уровень технической инвестиции (0 - это не инвестиция, 5 - технический энейблер для большого ряда задач)</p></li></ul><p>Каждую задачу мы оценили в привычных для нас story points, чтобы оценить сложность.</p>',now()-INTERVAL rand()*100 DAY,'post13', 1,  floor(rand()*100)),
  (14, 1, null,'ACCEPTED', '<h1>HTML Подчеркнуть заголовок</h1><h2>Это заголовок 1</h2><p>Это какое-то текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p>',now()-INTERVAL rand()*100 DAY,'post14', 1,  floor(rand()*100)),
  (15, 1, 1, 'ACCEPTED', '<h3>А дальше?</h3><p>Мы находимся на последнем из описанных этапов. Пока рано делать выводы, насколько он успешен, главное, что механизм есть, он работает и приносит пользу команде и продукту.</p>',now()-INTERVAL rand()*100 DAY,'post15', 3,  floor(rand()*100)),
  (16, 1, 1, 'ACCEPTED', '<h1>HTML Атрибут высота и ширина</h1><p>Изображения в HTML имеют набор атрибутов размера, который определяет ширину и высоту изображения:</p>',now()-INTERVAL rand()*100 DAY,'post16',3,  floor(rand()*100)),
  (17, 1, null,'NEW', '<h3>Упрощаем процесс</h3><p>Поняв, что текущая оценка достаточно сложная и долгая, мы решили отказаться от неё.&nbsp;</p><p>Но чем заменить?&nbsp;</p><p>Решили оценить влияние на техническое состояние продукта одним числом, так, как делаем это с оценкой в story point - на основе сравнения с другими задачами. Не важно, влияет это на оперирование или на скорость доставки, если влияют (в сравнении) одинаково, то и оценка будет одна и та же.</p><p>Раз в квартал оцениваем все задачи - учитывая, количество технического долга и количество разработчиков, за час каждый может оценить 10-15 задач (это можно делать независимо). Если кто-то не согласен с оценкой, обсуждаем.&nbsp;</p><p>Процесс погашения тоже претерпел изменения.</p><p>Среди задач, всегда есть какие-то совсем простые. Чтобы не занимать место в спринте (оно ограничено нашим capacity), решили собрать из них новую доску. В рамках любой продуктовой задачи, можно сделать и какую-нибудь маленькую задачу, улучшив продукт.&nbsp;</p><p>Теперь, кроме того, что задачи мы берем в спринт, какие-то небольшие задачи закрываются в рамках других задач.</p>',now()-INTERVAL rand()*100 DAY,'post17', 3,  floor(rand()*100)),
  (18, 0, 1, 'ACCEPTED', '<h1>HTML Подчеркнуть заголовок</h1><h2>Это заголовок 1</h2><p>Это какое-то текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p>',now()-INTERVAL rand()*100 DAY,'post18', 3,  floor(rand()*100)),
  (19, 1, 1, 'DECLINED', '<p>Из-за отсутствия механизма ранжирования задач, каждый брал то, что интересно ему. А из-за отсутствия процесса, который позволял бы задачам дойти до боевых серверов, такие задачи часто застревали где-то в районе code-review или тестирования (ведь основные задачи всегда более приоритетны).</p><h3>Собственный велосипед для ранжирования</h3><p>Мы не стали брать какую-то готовую систему оценки, которую кроме того, что применить, нужно еще и подстроить для себя, а сделали собственную.</p><p>Каждую из задач технического долга мы оценивали по 4 критериям:</p><ul><li><p>Повторяемость - как часто мы сталкиваемся с этой проблемой. (0 - очень редко, раз в полгода, 5 - каждый день)</p></li><li><p>Скорость доставки - как данная задача повлияет на скорость доставки других фичей. (скорость разработки, время тестирования, время деплоймента, предсказуемость разработки) (0 - не влияет, 5 - экономит очень много времени)</p></li><li><p>Влияние на время оперирования (расходы на эксплуатацию, нагрузка на сервер, безопасность) (0 - не влияет, 5 серьезно снижает расходы и риски оперирования)</p></li><li><p>Уровень технической инвестиции (0 - это не инвестиция, 5 - технический энейблер для большого ряда задач)</p></li></ul><p>Каждую задачу мы оценили в привычных для нас story points, чтобы оценить сложность.</p>',now()-INTERVAL rand()*100 DAY,'post19', 3,  floor(rand()*100)),
  (20, 0, 1, 'DECLINED', '<p>продукта (надежность, безопасность, скорость разработки, стабильность).</p><h3>Каждый берет, то, что ему ближе</h3><p>Когда продукт новый, техдолга у него немного. Из-за этого у нас не было какого-то механизма ранжирования задач, которые попадают в технический долг, как не было механизма для погашения, кроме собственного энтузиазма разработчиков. Принцип бойскаута - это про нас. На самом деле, всё было не столь радужно.&nbsp;</p>',now()-INTERVAL rand()*100 DAY,'post20', 3,  floor(rand()*100)),
  (21, 1, 1, 'ACCEPTED', '<h1>HTML Списки</h1><h2>Неупорядоченный список HTML</h2><ul><li>Кофе</li><li>Чай</li><li>Молоко</li></ul>  <h2>Упорядоченный список HTML</h2><ol><li>Кофе</li><li>Чай</li><li>Молоко</li></ol> ',now()-INTERVAL rand()*100 DAY,'post21', 1,  floor(rand()*100)),
  (22, 0, null,'NEW', '<h1>HTML Подчеркнуть заголовок</h1><h2>Это заголовок 1</h2><p>Это какое-то текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p>',now()-INTERVAL rand()*100 DAY,'post22', 1,  floor(rand()*100)),
  (23, 1, null,'ACCEPTED', '<h3>Упрощаем процесс</h3><p>Поняв, что текущая оценка достаточно сложная и долгая, мы решили отказаться от неё.&nbsp;</p><p>Но чем заменить?&nbsp;</p><p>Решили оценить влияние на техническое состояние продукта одним числом, так, как делаем это с оценкой в story point - на основе сравнения с другими задачами. Не важно, влияет это на оперирование или на скорость доставки, если влияют (в сравнении) одинаково, то и оценка будет одна и та же.</p><p>Раз в квартал оцениваем все задачи - учитывая, количество технического долга и количество разработчиков, за час каждый может оценить 10-15 задач (это можно делать независимо). Если кто-то не согласен с оценкой, обсуждаем.&nbsp;</p><p>Процесс погашения тоже претерпел изменения.</p><p>Среди задач, всегда есть какие-то совсем простые. Чтобы не занимать место в спринте (оно ограничено нашим capacity), решили собрать из них новую доску. В рамках любой продуктовой задачи, можно сделать и какую-нибудь маленькую задачу, улучшив продукт.&nbsp;</p><p>Теперь, кроме того, что задачи мы берем в спринт, какие-то небольшие задачи закрываются в рамках других задач.</p>',now()-INTERVAL rand()*100 DAY,'post23', 1,  floor(rand()*100)),
  (24, 1, 1, 'ACCEPTED', '<h1>HTML Атрибут высота и ширина</h1><p>Изображения в HTML имеют набор атрибутов размера, который определяет ширину и высоту изображения:</p>',now()-INTERVAL rand()*100 DAY,'post24', 1,  floor(rand()*100)),
  (25, 1, null,'NEW', '<p>продукта (надежность, безопасность, скорость разработки, стабильность).</p><h3>Каждый берет, то, что ему ближе</h3><p>Когда продукт новый, техдолга у него немного. Из-за этого у нас не было какого-то механизма ранжирования задач, которые попадают в технический долг, как не было механизма для погашения, кроме собственного энтузиазма разработчиков. Принцип бойскаута - это про нас. На самом деле, всё было не столь радужно.&nbsp;</p>',now() + INTERVAL 1 DAY,'post25', 2,  floor(rand()*100)),
  (26, 1, 1, 'ACCEPTED', '<h1>HTML Списки</h1><h2>Неупорядоченный список HTML</h2><ul><li>Кофе</li><li>Чай</li><li>Молоко</li></ul>  <h2>Упорядоченный список HTML</h2><ol><li>Кофе</li><li>Чай</li><li>Молоко</li></ol> ',now()+INTERVAL 2 DAY,'post26', 1,  floor(rand()*100)),
  (27, 0, null,'ACCEPTED', '<h1>HTML Подчеркнуть заголовок</h1><h2>Это заголовок 1</h2><p>Это какое-то текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p><hr><h2>Это заголовок 2</h2><p>Это какой-то другой текст.</p>',now()+INTERVAL 1 DAY,'post27', 2,  floor(rand()*100));

  truncate table blog.users;
  INSERT INTO blog.users(id,code,email,is_moderator,name,password,photo,reg_time)
  VALUES(1,'111','user1@mail.ru',1,'Куцепалов Александр','1111',null, now()-INTERVAL 110  DAY),
  (2,'111','user2@mail.ru',0,'Иванов Иван','1111',null,now()-INTERVAL 90  DAY),
  (3,'111','user3@mail.ru',0,'Петров Александр','1111',null,now()-INTERVAL 105  DAY);

truncate table blog.post_votes;
  INSERT INTO blog.post_votes  (id,post_id,time,user_id,value)
  VALUES
  (1,1, now()-INTERVAL rand()*50 DAY,  2, -1),
  (2,1, now()-INTERVAL rand()*50 DAY,  3, -1),
  (3,1, now()-INTERVAL rand()*50 DAY,  2, 1),
  (4,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (5,2, now()-INTERVAL rand()*50 DAY,  1, 1),
  (6,2, now()-INTERVAL rand()*50 DAY,  2, 1),
  (7,2, now()-INTERVAL rand()*50 DAY,  3, -1),
  (8,2, now()-INTERVAL rand()*50 DAY,  1, 1),
  (9,2, now()-INTERVAL rand()*50 DAY,  2, 1),
  (10,3, now()-INTERVAL rand()*50 DAY,  3, 1),
  (11,3, now()-INTERVAL rand()*50 DAY,  1, -1),
  (12,3, now()-INTERVAL rand()*50 DAY,  2, 1),
  (13,3, now()-INTERVAL rand()*50 DAY,  3, -1),
  (14,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (15,3, now()-INTERVAL rand()*50 DAY,  2, 1),
  (16,4, now()-INTERVAL rand()*50 DAY,  1, 1),
  (17,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (18,5, now()-INTERVAL rand()*50 DAY,  2, -1),
  (19,6, now()-INTERVAL rand()*50 DAY,  1, 1),
  (20,6, now()-INTERVAL rand()*50 DAY,  3, 1),
  (21,1, now()-INTERVAL rand()*50 DAY,  2, 1),
  (22,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (23,1, now()-INTERVAL rand()*50 DAY,  2, -1),
  (24,1, now()-INTERVAL rand()*50 DAY,  1, 1),
  (25,7, now()-INTERVAL rand()*50 DAY,  3, 1),
  (26,7, now()-INTERVAL rand()*50 DAY,  2, 1),
  (27,8, now()-INTERVAL rand()*50 DAY,  3, 1),
  (28,8, now()-INTERVAL rand()*50 DAY,  3, -1),
  (29,8, now()-INTERVAL rand()*50 DAY,  2, 1),
  (30,9, now()-INTERVAL rand()*50 DAY,  1, 1),
  (31,9, now()-INTERVAL rand()*50 DAY,  3, 1),
  (32,10, now()-INTERVAL rand()*50 DAY,  2,-1),
  (33,11, now()-INTERVAL rand()*50 DAY,  3, -1),
  (34,12, now()-INTERVAL rand()*50 DAY,  1, 1),
  (35,13, now()-INTERVAL rand()*50 DAY,  2, 1),
  (36,14, now()-INTERVAL rand()*50 DAY,  3, 1),
  (37,15, now()-INTERVAL rand()*50 DAY,  3, -1),
  (38,16, now()-INTERVAL rand()*50 DAY,  2, -1),
  (39,16, now()-INTERVAL rand()*50 DAY,  3, -1),
  (40,20, now()-INTERVAL rand()*50 DAY,  3, 1),
  (51,21, now()-INTERVAL rand()*50 DAY,  2, 1),
  (52,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (53,1, now()-INTERVAL rand()*50 DAY,  2, -1),
  (54,1, now()-INTERVAL rand()*50 DAY,  3, 1),
  (55,2, now()-INTERVAL rand()*50 DAY,  3, 1),
  (56,2, now()-INTERVAL rand()*50 DAY,  1, -1),
  (57,23, now()-INTERVAL rand()*50 DAY,  3, 1),
  (58,25, now()-INTERVAL rand()*50 DAY,  3, 1),
  (59,22, now()-INTERVAL rand()*50 DAY,  1, -1),
  (60,3, now()-INTERVAL rand()*50 DAY,  3, 1),
  (61,3, now()-INTERVAL rand()*50 DAY,  1, -1),
  (62,3, now()-INTERVAL rand()*50 DAY,  2, 1),
  (63,3, now()-INTERVAL rand()*50 DAY,  3, 1),
  (64,16, now()-INTERVAL rand()*50 DAY,  3, 1),
  (65,3, now()-INTERVAL rand()*50 DAY,  2, -1),
  (66,4, now()-INTERVAL rand()*50 DAY,  3, -1),
  (67,16, now()-INTERVAL rand()*50 DAY,  3, 1),
  (68,5, now()-INTERVAL rand()*50 DAY,  2, 1),
  (69,6, now()-INTERVAL rand()*50 DAY,  1, -1),
  (70,6, now()-INTERVAL rand()*50 DAY,  3, 1),
  (71,11, now()-INTERVAL rand()*50 DAY, 1, -1),
  (72,12, now()-INTERVAL rand()*50 DAY,  3, 1),
  (73,17, now()-INTERVAL rand()*50 DAY,  2, 1),
  (74,15, now()-INTERVAL rand()*50 DAY,  3, -1),
  (75,2, now()-INTERVAL rand()*50 DAY,  3, 1),
  (76,26, now()-INTERVAL rand()*50 DAY,  2, 1),
  (77,22, now()-INTERVAL rand()*50 DAY,  3, 1),
  (78,21, now()-INTERVAL rand()*50 DAY,  3, 1),
  (79,20, now()-INTERVAL rand()*50 DAY,  2, -1),
  (80,3, now()-INTERVAL rand()*50 DAY,  1, 1),
  (81,18, now()-INTERVAL rand()*50 DAY,  2, -1),
  (82,19, now()-INTERVAL rand()*50 DAY,  3, -1),
  (83,12, now()-INTERVAL rand()*50 DAY,  2, -1),
  (84,14, now()-INTERVAL rand()*50 DAY,  3, 1),
  (85,25, now()-INTERVAL rand()*50 DAY,  3, 1),
  (86,22, now()-INTERVAL rand()*50 DAY,  2, 1),
  (87,21, now()-INTERVAL rand()*50 DAY,  3, 1),
  (88,2, now()-INTERVAL rand()*50 DAY,  3, 1),
  (89,23, now()-INTERVAL rand()*50 DAY,  1, -1),
  (90,3, now()-INTERVAL rand()*50 DAY,  3, -1),
  (91,10, now()-INTERVAL rand()*50 DAY,  2, -1),
  (92,13, now()-INTERVAL rand()*50 DAY,  3, 1),
  (93,11, now()-INTERVAL rand()*50 DAY,  2, 1),
  (94,16, now()-INTERVAL rand()*50 DAY,  3, -1),
  (95,27, now()-INTERVAL rand()*50 DAY,  3, -1),
  (96,24, now()-INTERVAL rand()*50 DAY,  1, -1),
  (97,22, now()-INTERVAL rand()*50 DAY,  3, 1),
  (98,2, now()-INTERVAL rand()*50 DAY,  3, 1),
  (99,21, now()-INTERVAL rand()*50 DAY,  2, -1),
  (100,3, now()-INTERVAL rand()*50 DAY,  3, 1);


truncate table blog.tags;
INSERT INTO blog.tags  (id,name)
  VALUES
  (1,'PHP'),
  (2,'Python'),
  (3,'JAVA'),
  (4,'SQL');

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

truncate table blog.post_comments;
INSERT INTO blog.post_comments  (id ,parent_id ,post_id ,user_id,time,text )
 VALUES

  (1,null, 1,1, now()-INTERVAL rand()*50 DAY,'text'),
  (2,null, 2,2, now()-INTERVAL rand()*50 DAY,'text'),
  (3,null, 3,3, now()-INTERVAL rand()*50 DAY,'text'),
  (4,null, 4,1, now()-INTERVAL rand()*50 DAY,'text'),
  (5,null, 5,2, now()-INTERVAL rand()*50 DAY,'text'),
  (6,null, 6,3, now()-INTERVAL rand()*50 DAY,'text'),
  (7,null, 7,1, now()-INTERVAL rand()*50 DAY,'text'),
  (8,null, 8,3, now()-INTERVAL rand()*50 DAY,'text'),
  (9,null,9,1, now()-INTERVAL rand()*50 DAY,'text'),
  (10,null,10,3, now()-INTERVAL rand()*50 DAY,'text'),
  (11,null, 11,1, now()-INTERVAL rand()*50 DAY,'text'),
  (12,null, 12,1, now()-INTERVAL rand()*50 DAY,'text'),
  (13,null, 13,1, now()-INTERVAL rand()*50 DAY,'text'),
  (14,null, 15,3, now()-INTERVAL rand()*50 DAY,'text'),
  (15,null, 17,1, now()-INTERVAL rand()*50 DAY,'text'),
  (16,null, 18,2, now()-INTERVAL rand()*50 DAY,'text'),
  (17,null, 19,1, now()-INTERVAL rand()*50 DAY,'text'),
  (18,null, 20,2, now()-INTERVAL rand()*50 DAY,'text'),
  (19,null, 21,1, now()-INTERVAL rand()*50 DAY,'text'),
  (20,null, 22,3, now()-INTERVAL rand()*50 DAY,'text'),
  (21,null, 23,1, now()-INTERVAL rand()*50 DAY,'text'),
  (22,null, 26,1, now()-INTERVAL rand()*50 DAY,'text') ,
  (23,19, 21,2, now()-INTERVAL rand()*50 DAY,'text'),
  (24,20, 22,1, now()-INTERVAL rand()*50 DAY,'text'),
  (25,21, 23,3, now()-INTERVAL rand()*50 DAY,'text'),
  (26,22, 26,2, now()-INTERVAL rand()*50 DAY,'text') ,
  (27,6, 6,1, now()-INTERVAL rand()*50 DAY,'text'),
  (28,7, 7,2, now()-INTERVAL rand()*50 DAY,'text'),
  (29,8, 8,2, now()-INTERVAL rand()*50 DAY,'text'),
  (30,9,9,3, now()-INTERVAL rand()*50 DAY,'text');

truncate table blog.captcha_codes;
INSERT INTO blog.captcha_codes  (id ,time, code,secret_code)
 VALUES
(1,now()-INTERVAL rand()*10 DAY,'qwer','qwer'),
(2,now()-INTERVAL rand()*10 DAY,'erty','erty'),
(3,now()-INTERVAL rand()*10 DAY,'sdfg','sdfg'),
(4,now()-INTERVAL rand()*10 DAY,'xcvb','xcvb');


truncate table blog.global_settings;
INSERT INTO blog.global_settings  (id ,code, name,value)
 VALUES
(1,'MULTIUSER_MODE','Многопользовательский режим','YES'),
(2,'POST_PREMODERATION','Премодерация постов','YES'),
(3,'STATISTICS_IS_PUBLIC','Показывать всем статистику блога','YES');