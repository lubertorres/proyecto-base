create database proyecto_base_db;
use proyecto_base_db;

create table if not exists user
(
	id int auto_increment,
	username varchar(10),
	password varchar(15),
	primary key(id)
	);
drop table user;
insert into user(username, password) values("user1", "contraseña1");

DELIMITER $$

CREATE PROCEDURE sp_insert_user(
    IN e_username VARCHAR(10),
    IN e_password VARCHAR(15)
)
BEGIN
    INSERT INTO user(username, password) VALUES (e_username, e_password);
END$$

DELIMITER ;

DELIMITER $$
CREATE PROCEDURE sp_get_users()
BEGIN
    SELECT username, password FROM user;
END$$
DELIMITER ;

create table curso
(
	id int auto_increment,
	curso varchar(10),
	primary key(id)
);
insert into curso(curso) values("Primero");
select * from curso;

create table estudiante
(
	id int auto_increment,
	nombre_completo varchar(50),
	primary key(id)
);
insert into estudiante(nombre_completo) values("Arturo Rodriguez");

create procedure if not exists spInsertarEstudiante
(
	in e_nombre_completo varchar(50)
)
begin
	insert into estudiante(nombre_completo) values(e_nombre_completo);
end;
call spInsertarEstudiante("NuevoEstudiante");


select * from estudiante;
drop table calificacion;


create table materia
(
	id int auto_increment,
	materia varchar(30),
	primary key(id)
);
insert into materia(materia) values("Fisica");
select * from materia;

drop table calificacion;
create table calificacion
(
	id int auto_increment,
	nom_actividad varchar(50),
	id_estudiante int,
	id_curso int,
	id_materia int,
	calificacion decimal(4,2),
	primary key(id),
	foreign key(id_estudiante) references estudiante(id),
	foreign key(id_curso) references curso(id),
	foreign key(id_curso) references curso(id),
	foreign key(id_materia) references materia(id)
);

select * from calificacion;

insert into calificacion(nom_actividad, id_estudiante, id_curso, id_materia, calificacion)
values("Actividad uno", 1, 1, 1, 10);

create procedure if not exists spInsertarCalificacion(
	in e_nom_actividad varchar(50),
	in e_id_estudiante int,
	in e_id_curso int,
	in e_id_materia int,
	in e_calificacion decimal(4,2)
)
begin
	insert into calificacion(nom_actividad, id_estudiante, id_curso, id_materia, calificacion)
	values(e_nom_actividad, e_id_estudiante, e_id_curso, e_id_materia, e_calificacion);
end;

call spInsertarCalificacion("Actividad 5", 2, 1, 1, 10);


DELIMITER $$

create procedure spCalcularPromedioIndividual
(
	in e_id_estudiante int,
	in e_id_curso int
)
begin
	select
	avg(calificacion) as promedio_estudiante
	from calificacion
	where id_estudiante = e_id_estudiante
	and id_curso = e_id_curso;
end$$

DELIMITER ;

call spCalcularPromedioIndividual(2, 1);

DELIMITER $$
create procedure spCalcularPromedioCurso
(
	in e_id_curso int
)
begin
	select
	avg(calificacion) as promedio_curso
	from calificacion
	where id_curso = e_id_curso;
end$$

DELIMITER ;

call spCalcularPromedioCurso(1);