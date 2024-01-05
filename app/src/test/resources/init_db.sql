delete
from customer;
delete
from events;

ALTER SEQUENCE customer_id_seq RESTART WITH 1;
ALTER SEQUENCE events_id_seq RESTART WITH 1;


insert into customer (name, age)
values ('TestName1', 20);
insert into customer (name, age)
values ('TestName2', 54);

insert into events (name, description, place, coordinates, event_time, creator_id)
values ('Event by testName1', 'test description 14.', 'NY, USA', point(43.2324, 34.333), '2024-06-10T10:00:00Z', 1);
insert into events (name, description, place, coordinates, event_time, creator_id)
values ('Event by testName1', 'test description 123', 'NY, USA', point(43.2324, 34.133), '2024-06-10T10:00:00Z', 1);