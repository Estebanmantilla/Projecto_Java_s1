# 📌 Sistema de Ventas en Java (Projecto_Java_s1)

Este proyecto es un **sistema de ventas desarrollado en Java**, usando arquitectura MVC y conectado a una base de datos **MySQL**. Permite gestionar **clientes**, **celulares**, **ventas**, **detalle de ventas** y estadísticas como *ventas totales por mes* 🗓️ o *top 3 celulares más vendidos* 📊.

Está pensado para ser ejecutado desde consola (NetBeans / IDE Java).

---

## 📂 Estructura del proyecto

El proyecto está dividido en tres capas principales:

## 📁 CONTROLADOR → lógica de negocio y conexión con la base de datos
## 📁 MODELO → clases que representan entidades (Cliente, Ventas, Detalle_venta, Celular)
## 📁 VISTA → menús de interacción con el usuario
## Database.sql → Script SQL para crear las tablas
## pom.xml → Configuración Maven para dependencias del proyecto



---

## 🧱 Descripción de clases principales

### 📍 MODELO

- **Cliente** → Representa datos de clientes (id, nombre, identificación, teléfono, correo).  
- **Ventas** → Representa ventas realizadas (cliente, fecha, total).  
- **Detalle_venta** → Almacena línea de venta (venta, celular, cantidad, subtotal).  
- **Celular / Marca** → Gestiona celulares disponibles y su información (precio, stock).

### 📍 CONTROLADOR

- **Gestionarcliente** → CRUD de clientes.  
- **Gestionarventampl** → Inserta, actualiza, elimina y lista ventas, incluyendo recuperación automática de ID.  
- **Gestión de reportes** → Métodos para estadísticas con **Stream API** (ventas por mes, top 3 celulares vendidos).

### 📍 VISTA

Menús de consola para interacción con el usuario:


---


![](Screenshoots/Captura%20de%20pantalla%202026-02-11%20a%20la(s)%2010.10.51 p. m..png)
![](Screenshoots/Captura%20de%20pantalla%202026-02-11%20a%20la(s)%2010.11.03 p. m..png)
![](Screenshoots/Captura%20de%20pantalla%202026-02-11%20a%20la(s)%2010.11.13 p. m..png)
![](Screenshoots/Captura%20de%20pantalla%202026-02-11%20a%20la(s)%2010.11.32 p. m..png)

## 🔄 Ejemplo de ejecución

1. Clonar el proyecto:
```bash
git clone https://github.com/Estebanmantilla/Projecto_Java_s1.git


1. Registrar cliente  
2. Registrar venta  
3. Listar ventas  
4. Mostrar ventas por mes  
5. Top 3 celulares más vendidos  


java 
Map<String, Double> ventasPorMes = lista.stream()
    .collect(Collectors.groupingBy(
        v -> v.getFECHA().substring(0, 7), 
        Collectors.summingDouble(ventas::getTotal)
    ));


'''
public Connection conexion() {
    String url = "jdbc:mysql://localhost:3306/tecnostore_db";
    String user = "tu_usuario";
    String pass = "tu_contraseña";
    return DriverManager.getConnection(url, user, pass);
}
'''

