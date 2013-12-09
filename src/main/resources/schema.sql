create table planets
(
	id bigint,
	name varchar(255),
	mass_rel_to_earth decimal(10, 3)
);

create table satellites
(
	id bigint,
	planet_id bigint,
	name varchar(255)
);
