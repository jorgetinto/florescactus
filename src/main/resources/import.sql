/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  jpino
 * Created: 28-jun-2019
 */

INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, Correo, password) VALUES('Jorge', 'Pino', 'Garay', 'jorg.pinog@alumnos.duoc.cl', '1234');
INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, Correo, password) VALUES('Ricardo', 'Galleguillos', 'Solis', 'ri.galleguillo@alumnos.duoc.cl', '1234');
INSERT INTO Usuarios (nombre, apellido_paterno, apellido_materno, Correo, password) VALUES('Felipe', 'Inostroza', 'Meneses', 'f.inostrozam@alumnos.duoc.cl', '1234');

INSERT INTO clientes(nombre, razon_social, comuna, estado) VALUES ('Falabella', 'Falabella S.A.', 'Santiago', 1);
INSERT INTO clientes(nombre, razon_social, comuna, estado) VALUES ('Soprole', 'Soprole S.A.', 'Santiago', 1);
INSERT INTO clientes(nombre, razon_social, comuna, estado) VALUES ('Mall Plaza Oeste', 'Mall Plaza S.A.', 'Santiago', 1);
INSERT INTO clientes(nombre, razon_social, comuna, estado) VALUES ('Mall Plaza Norte', 'Mall Plaza S.A.', 'Santiago', 1);

INSERT INTO muro(nombre, descripcion, ciudad, tipo_intalacion, estado, fecha_creacion, usuarios_id, clientes_Id) VALUES('Muro Sorpole 01', 'Muro puerta sur', 'Santiago', 1,1, '2019-06-29', 1, 1);

INSERT INTO horarios_riego (horario, estado,muro_id) VALUES('08:00',1,1);
INSERT INTO horarios_riego (horario, estado,muro_id) VALUES('15:00',1,1);
INSERT INTO horarios_riego (horario, estado,muro_id) VALUES('23:00',1,1);

INSERT INTO decisiones (humedad,temperatura,temporada,pronostico,fecha_creacion, muro_Id, estado) VALUES (65.10, 18.00, 'Invierno', '21.00 - nublado', '2019-06-29 08:01:00',1, 1);
INSERT INTO decisiones (humedad,temperatura,temporada,pronostico,fecha_creacion, muro_Id, estado) VALUES ( 80.40, 14.00, 'Invierno', '21.00 - nublado', '2019-06-29 15:01:00',1, 1);
