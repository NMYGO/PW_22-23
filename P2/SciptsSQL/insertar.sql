insert into usuario
values('m@gmail.com','Marcos','Miranda Lozano','2021-06-12','2005-06-14', TRUE);
insert into usuario
values('manuel@gmail.com','Manuel','Rosa Lugo','2022-04-25','1999-04-05', FALSE);
insert into usuario
values('david@gmail.com','David','Gomez','2022-01-30','2000-08-25', TRUE);
insert into usuario
values('a@gmail.com','Antonio','Cano Almeida','2022-10-17','1978-10-13', FALSE);

insert into pista
values('pista1',FALSE,'FAMILIAR',4);
insert into pista
values('pista2',FALSE,'ADULTO',10);
insert into pista
values('pista3',FALSE,'INFANTIL',5);
insert into pista
values('pista4',TRUE,'ADULTO',4);

insert into kart
values(1,TRUE,'RESERVADO','pista1');
insert into kart
values(2,TRUE,'RESERVADO','pista1');
insert into kart
values(3,TRUE,'RESERVADO','pista3');
insert into kart
values(4,TRUE,'RESERVADO','pista3');
insert into kart
values(5,TRUE,'RESERVADO','pista1');
insert into kart
values(6,FALSE,'RESERVADO','pista1');
insert into kart
values(7,FALSE,'RESERVADO','pista2');
insert into kart
values(8,FALSE,'RESERVADO','pista2');
insert into kart
values(9,TRUE,'RESERVADO','pista3');

insert into bono
values(1,2,'2023-10-09','a@gmail.com','INFANTIL');
insert into bono
values(2,2,'2023-10-09','manuel@gmail.com','FAMILIAR');

insert into reserva
values(1,'manuel@gmail.com',90,30,0,'2023-02-02','pista1','FAMILIAR',1,1,NULL);
insert into reserva
values(2,'manuel@gmail.com',120,40,0,'2022-12-28','pista2','ADULTO',3,0,NULL);
insert into reserva
values(3,'a@gmail.com',90,30,0,'2022-10-20','pista2','ADULTO',5,0,NULL);
insert into reserva
values(4,'david@gmail.com',120,40,0,'2023-01-15','pista1','FAMILIAR',1,3,NULL);
insert into reserva
values(5,'manuel@gmail.com',120,40,5,'2023-02-10','pista1','FAMILIAR',1,2,2);
insert into reserva
values(6,'a@gmail.com',60,20,5,'2023-01-01','pista3','INFANTIL',0,2,1);
insert into reserva
values(7,'a@gmail.com',90,30,5,'2023-03-15','pista3','INFANTIL',0,3,1);
insert into reserva
values(8,'manuel@gmail.com',90,30,5,'2023-05-03','pista1','FAMILIAR',3,4,2);
insert into reserva
values(9,'m@gmail.com',90,30,0,'2022-11-15','pista2','ADULTO',2,0,NULL);