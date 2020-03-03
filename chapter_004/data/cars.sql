/* Нужно написать SQL скрипты:

Создать структур данных в базе.
Таблицы.
   Кузов. Двигатель, Коробка передач.
Создать структуру Машина. Машина не может существовать без данных из п.1.
Заполнить таблицы через insert.
*/

INSERT INTO body (id, title) VALUES
(1, 'Sedan'),
(2, 'Hatchback');

INSERT INTO car (id, name, engine_id, body_id, gear_id) VALUES
(1, 'Honda Accord S Type', 4, 1, 4),
(2, 'Honda Civic', 1, 2, 4);

INSERT INTO engine (id, title) VALUES
(1, 'Gasoline 1.8L'),
(2, 'Gasoline 2L'),
(3, 'Diesel 3L'),
(4, 'Gasoline 2.4L');

INSERT INTO gear (id, title) VALUES
(1, 'Manual 5'),
(2, 'Manual 6'),
(3, 'Automatic 4'),
(4, 'Automatic 5');

/*1. Вывести список всех машин и все привязанные к ним детали.*/
select c.name as car, e.title as engine, b.title as body, g.title as gear
from car as c
left join engine as e on c.engine_id = e.id
left join body as b on c.body_id = b.id
left join gear as g on c.gear_id = g.id;

/*2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.*/
select e.title as engine, b.title as body, g.title as gear
from car as c
right join engine as e on c.engine_id = e.id
right join body as b on c.body_id = b.id
right join gear as g on c.gear_id = g.id
where c.id is null;