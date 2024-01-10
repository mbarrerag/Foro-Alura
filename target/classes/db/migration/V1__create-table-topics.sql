USE foroalurabd;
CREATE TABLE course (
                        id BIGINT PRIMARY KEY,
                        nombre VARCHAR(255),
                        categoria VARCHAR(255)
);

CREATE TABLE user (
                      id BIGINT PRIMARY KEY,
                      nombre VARCHAR(255),
                      email VARCHAR(255),
                      contrasena VARCHAR(255)
);


CREATE TABLE topic (
                       id SERIAL PRIMARY KEY not null auto_increment,
                       titulo VARCHAR(255) not null,
                       mensaje VARCHAR(255) not null,
                       fecha_creacion TIMESTAMP not null,
                       status VARCHAR(50) not null,
                       autor_id BIGINT,  -- Cambiado a "autor_id" para coincidir con el nombre en la clase Topico
                       curso_id BIGINT,  -- Cambiado a "curso_id" para coincidir con el nombre en la clase Topico
                       FOREIGN KEY (autor_id) REFERENCES user(id),
                       FOREIGN KEY (curso_id) REFERENCES course(id)
);
