
/* Данные */
INSERT INTO `product_type` (`id`, `name`) VALUES
(1, 'cheese'),
(2, 'milk'),
(3, 'bread'),
(4, 'icecream');

INSERT INTO `product` (`id`, `name`, `type_id`, `expire_date`, `price`, `count`) VALUES
(1, 'Gauda', 1, '2020-03-28', 1, 20),
(2, 'Camamber', 1, '2020-04-30', 2, 1),
(3, 'Milk One', 2, '2020-03-27', 3, 10),
(4, 'Strawberry Ice Cream', 4, '2020-04-02', 5, 3);

/* 1. Написать запрос получение всех продуктов с типом "СЫР"*/
select *
from product
where type_id = 1;

/* 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженное"*/
select *
from product
where name like '%ice cream%';

/* 3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.*/
select *
from product
where month(expire_date) = month(current_timestamp) + 1;

/* 4. Написать запрос, который выводит самый дорогой продукт.*/
select *
from product
where price = (select max(price) from product);

/* 5. Написать запрос, который выводит количество всех продуктов определенного типа.*/
select count(p.id) as count, pt.name
from product as p
inner join product_type as pt on p.type_id = pt.id
group by pt.id;

/* 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"*/
select *
from product
where type_id in (1, 2);

/* 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.*/
SELECT COUNT(id) as count, type_id
FROM `product`
GROUP BY type_id
HAVING count > 10

/* 8. Вывести все продукты и их тип.*/
select p.*, pt.name
from product as p
left join product_type as pt on p.type_id = pt.id

/* Или без джоина */
select p.*, pt.name
from product as p, product_type as pt
where p.type_id = pt.id