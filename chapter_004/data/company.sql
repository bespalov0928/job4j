/*CREATE TABLE company
(
id integer NOT NULL,
name character varying,
CONSTRAINT company_pkey PRIMARY KEY (id)
);

CREATE TABLE person
(
id integer NOT NULL,
name character varying,
company_id integer,
CONSTRAINT person_pkey PRIMARY KEY (id)
);*/

/*1. Names of all persons that are NOT in the company with id = 5*/
/*   Company name for each person*/

select p.name as person, c.name as company
from person as p
left join company as c on p.company_id = c.id
where p.company_id != 5;

/*3. Select the name of the company with the maximum number of persons + number of persons in this company*/
select personCnt, name
from (  select count(p.id) as personCnt, c.name
        from person as p
        left join company as c on c.id = p.company_id
        group by p.company_id) as cnt
having max(personCnt);



