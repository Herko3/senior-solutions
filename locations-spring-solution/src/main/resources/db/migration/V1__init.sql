create table locations
(
    id       bigint auto_increment,
    loc_name varchar(255),
    lat      double,
    lon      double,
    primary key (id)
);

insert into locations(loc_name, lat, lon)
values ('London', 51.5368, -0.1308);
insert into locations(loc_name, lat, lon)
values ('Copenhagen', 55.6998, 12.5477);
insert into locations(loc_name, lat, lon)
values ('Budapest', 47.5161, 19.5477);