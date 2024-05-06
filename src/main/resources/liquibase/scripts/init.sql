-- liquibase formatted sql
-- changeset bornartem:1
CREATE TABLE public.animal_avatar (
	id int8 NOT NULL,
	"data" oid NULL,
	file_path varchar(255) NULL,
	file_size int8 NOT NULL,
	media_type varchar(255) NULL,
	animal_id_animal int8 NULL,
	CONSTRAINT animal_avatar_pkey PRIMARY KEY (id),
	CONSTRAINT uk_oprgj8d2brr7rnpglmlflcbjd UNIQUE (animal_id_animal)
);

CREATE TABLE public.animals (
	id_animal bigserial NOT NULL,
	age varchar(255) NULL,
	busy_free bool NOT NULL,
	date_take timestamp(6) NULL,
	"name" varchar(255) NULL,
	"type" varchar(255) NULL,
	client_id int8 NULL,
	shelters_id int8 NULL,
	CONSTRAINT animals_pkey PRIMARY KEY (id_animal)
);

CREATE TABLE public.clients (
	id_client bigserial NOT NULL,
	chat_id int8 NOT NULL,
	contact varchar(255) NULL,
	"name" varchar(255) NULL,
	adopted_animal_id_animal int8 NULL,
	id_volunteer int8 NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (id_client),
	CONSTRAINT uk_irs9sx761mntg1xspsit1695f UNIQUE (adopted_animal_id_animal)
);

CREATE TABLE public.conversation_people (
	id bigserial NOT NULL,
	chat_id int8 NOT NULL,
	is_volunteer bool NOT NULL,
	opponent_chat_id int8 NOT NULL,
	CONSTRAINT conversation_people_pkey PRIMARY KEY (id)
);

CREATE TABLE public.daily_report (
	id_daily_report bigserial NOT NULL,
	animal_menu varchar(255) NULL,
	reaction varchar(255) NULL,
	well varchar(255) NULL,
	is_check bool NOT NULL,
	date_time timestamp(6) NOT NULL,
	photo_animal varchar(255) NULL,
	animal_id int8 NULL,
	id_client int8 NULL,
	CONSTRAINT daily_report_pkey PRIMARY KEY (id_daily_report)
);

CREATE TABLE public.shelter (
	id_shelter bigserial NOT NULL,
	address varchar(255) NULL,
	contact varchar(255) NULL,
	"location" varchar(255) NULL,
	"name" varchar(255) NULL,
	security_contact varchar(255) NULL,
	rules varchar(255) NULL,
	working_hours varchar(255) NULL,
	CONSTRAINT shelter_pkey PRIMARY KEY (id_shelter)
);

CREATE TABLE public.volunteers (
	id_volunteer int8 NOT NULL,
	activity bool NULL,
	chat_id int8 NOT NULL,
	contact varchar(255) NULL,
	"name" varchar(255) NULL,
	CONSTRAINT volunteers_pkey PRIMARY KEY (id_volunteer)
);