ATPOS
================
Reto 2 - arti4109-Arquitectura-de-Software



                          
## Experimento 1: Verificación integridad de la data y bloqueo de acceso temporal a máquinas con fallas recurrentes                          
###  Propósito
Verificar la Integridad del cuerpo de la petición y limitar el acceso temporal a peticiones provenientes de IPs que generan fallas de forma repetida. 

###  Resultados esperados
Se procesan solo las peticiones que poseen una carga útil con una suma de verificación igual a la enviada desde el cliente. Y a su vez se bloquean aquellas IPs que realicen más de 3 intentos fallidos de envío de transacciones.

###  Recursos Requeridos
SO:  Ubuntu 19.4 64 bits. IDE desarrollo: IntelliJ IDEA Community 2019.2.1

### Descripción
La implementación del experimento se desarrolló bajo el lenguaje de Java, mediante el uso del framework de Spring Boot.

Se construyó una pequeña API de Servicios Web REST para exponer unos cuantos servicios a usar para sincronizar transacciones de ventas y revisar el estado del servidor.

Se implementaron las siguientes tácticas: 

* **Verificación Integridad de Mensajes:** Mediante un Supervisor de peticiones se interceptaban cada Request con un body , específicamente tipo Post. Luego se calculaba el Checksum del body y se comparaba con el valor enviado por el cliente en la cabecera de la petición. Se aceptaron aquellas peticiones cuya validación fue exitosa.

* **Bloqueo de máquinas con fallos repetidos:** Haciendo uso de la validación de integridad de la data, se usó el resultado para agregar la IP a una lista negra donde tenia máximo 3 intentos para poder realizar la petición de forma exitosa sin ser bloqueado el acceso de la máquina remota correspondiente a dicha IP. El bloqueo de la máquina tenía una duración de 1 Hora.

*  **Detección y Manejo de Errores:** Una vez se detectara una IP bloqueada intentando realizar una petición o una transacción con data alterada se generó una excepción relacionada al evento. Las excepciones eran capturadas por un Supervisor que cubría todos los servicios y le daba una manejo a las excepciones para retornar un mensaje claro al cliente del servicio web.

### Hipótesis - Punto crítico
Tener la capacidad de procesar sólo aquellas transacciones cuya integridad de su data seguía intacta y a la vez bloquear el acceso a máquinas que enviaban peticiones con alteraciones en la data.

### Diagrama

![alt text](https://github.com/arti4109-arquitectura-de-software/g3-reto2-2019-02-mati-g3/blob/master/Diagrama%20Modulo%20Seguridad%20Transacciones.png)

## Experimento 2: Verificación de disponibilidad en la facturación sin conexión al servidor ATpos
                         
###  Propósito
Verificar la la disponibilidad de la funcionalidad de facturación aún en la situación en que no se presenta conexión con el servidor. 

###  Resultados esperados
Se continúan realizando el registro de facturas aun cuando la conexión con el servidor remoto no se realice.

###  Recursos Requeridos
SO: Windows 10 64 bits. IDE desarrollo: Eclipse 201906


