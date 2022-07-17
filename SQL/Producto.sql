Create database Producto;

use Producto;

Create table producto(
    id int PRIMARY KEY,
    nombre varchar(200),
    codigo varchar(10),
    precio decimal(12,4),
    cantidad int,
    fechaVencimiento date
);

insert into producto values(1, 'Hola', 'Codigo', 10,1,curdate());

UPDATE producto SET nombre = 'test', codigo = 'test2' WHERE id = 1;