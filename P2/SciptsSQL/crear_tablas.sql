create table `pista`
	(`nombrePista` varchar(64) primary key,
    `estado` bool NOT NULL,
    `dificultad` varchar(64) NOT NULL,
    `maxKarts` int NOT NULL); 
    
create table `usuario`
	(`correoUsuario` varchar(64) primary key,
    `nombre` varchar(64) NOT NULL,
    `apellidos` varchar(64) NOT NULL,
    `fechaInscripcion` date NOT NULL,
    `fechaNacimiento` date NOT NULL,
    `administrador` bool NOT NULL);
    
create table `kart`
	(`idKart` int primary key,
    `tipo` bool NOT NULL,
    `estado` varchar(64) NOT NULL,
    `nombrePista` varchar(64) DEFAULT NULL,
constraint fk_kart_pista foreign key (nombrePista) 
	references Pista(nombrePista));
    
create table `bono`
	(`idBono` int primary key,
    `sesion` int NOT NULL,
    `fechaCaducidad` date NOT NULL,
    `correoUsuario` varchar(64) NOT NULL,
    `tipo` varchar(64) default null,
constraint fk_bono_correo foreign key (correoUsuario)
	references Usuario(correoUsuario));
    
create table `reserva`
	(`idReserva` int primary key,
    `correoUsuario` varchar(64) NOT NULL,
    `duracion` int NOT NULL,
    `precio` int NOT NULL,
    `descuento` int default null,
    `fecha` date NOT NULL,
    `nombrePista` varchar(64) NOT NULL,
    `dificultad`varchar(64) NOT NULL,
    `adultos` int DEFAULT NULL,
    `ninos` int DEFAULT NULL,
    `idBono` int DEFAULT NULL,
constraint fk_reserva_correo foreign key (correoUsuario)
	references Usuario(correoUsuario),
constraint fk_bono_reserva foreign key (idBono)
	references Bono(idBono));