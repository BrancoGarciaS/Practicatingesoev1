package com.example.BrancoGarciaTingesoEvaluacion1.services;

public class Apuntesclases {
    // java --version (para java version 17, que es el màs eficiente,y puedes
    /*
    tener problema al tener java 20

    Despliegue tradicional, usando maquina virtuales y de contenedores (hay 3 aplicaciones)

    Es monolìtica la app hasta ahora, si la app està hecha en java 20, puede no funcionar en
    los computadores java 20

    1. DEspliegue tradicional: necesitas un hardware propio para tu aplicacion (despliegas tu aplicacion)
            SI tienes java 20, solo se instalaran aplicaciones con java 20, y no es optimo
            por ello aparecieron los despliegues de virtualizacion

    2. DEspliegue de virtualizacion: tengo que crear maquinas virtuales con mucho disco duro, RAM
        y hardware potente y debo usar un virtualizador como virtual Box

    Cada maquina virtual es como un computador independiente, pero para ella debes instalar un Sistema
    Operativo como linux, ubuntu, aca se optimiza el hardware, pero tiene la desventaja de gastar recursos
    porque debes instalar todo el sistema operativo

    3. TEcnologìa de los contenedores; similares a las maquinas virtuales pero son livianos, ya no
    instalo un supervisor como virtual box, uso un gestor de contenedor: docker, de esos contenedores
    no necesitan un sistema operativo y almacena la aplicacion con las librerias

    -> docker.com
    -> hub.docker.com (repositorio de imagenes que podemos usar, como repositorio de github)
       tratar de en la busqueda usar docker official image

       Digamos que quiero buscar mysql, en dockerhub no instalas mysql, solo su imagen

       Para poder usar la imagen mysql, necesito tener en el computador docker desktop
       (para instalar practicamente toda la tecnologia, necesito ir a docker desktop en google

        Ver los reqerimientos para instalar windows, como wsl windows que te requiere, es como
        un linux en windows

        -> COn docker desktop instalado, debo pegar el docker pull mysql de official image mysql dockerhub
        -> entonces pongo ese comando en la cmd en la carpeta de la app creo (y descarga la imagen
        -> a mi docker)

        -> y uso docker images en la cmd para ver si rewsulto con pèxito, voy a images en docker desktop
        y coloco run en la imagen para correrla

        -< Bajo a how to use this image y copio el codigo

        docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql;tag
        Esto tengo que cambairlo segun mis datos y el nombre de la variable de entorno
        docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=abc123 -d mysql;latest

        Y pongo enter para que corra (compruebo con comando docker ps)

        Y deberia salir algo como IMAGE mysql:tastest docker
        Y en contenedores deberia salir corriendo mysql5, ponemeos open in terminal (Figura al lado
        de las dos ralllistas)

        sh-4.4# mysql -u root -p, todo lo que haga ahi esta dentro del contendor (esto es lueg de
         presionar la figurita, el compu no le pasaria nada

        Puedo borrar la imagen y contenedor (con tres puntos)

        Debemos subir la app a dockerhub

        El profe tiene una app que no usa base de datos

        Uso un controaldor con la ruta localhost:8080/mensaje

        Usamos la tecnologia de contenedores,
        ojo con la carpeta target que tiene archivos .jw que es la aplicacion empaquetada que te lo
        genera


   <finalName> </finalName>
   pongo package y lo corro con la flecha verde para generar el java, tengo que empaquetar las pruebas
   unitarias usar el que parece disco de bbloqueo de uno, ese debemos presionarlo para saltarse las
   pruebas unitarias

   tendriamos el mywebapp1.jw -> ya tenemos el jar pero no està como imagen, y para crearla, en la misma carpeta
   del proyecto debemosusar dockerfile sin extensiones
   en ese docker file generado en la carpeta junto a pom.xml (en la misma carpeta)

   DEbemos tener

   FROM openjdk:17
   ARG JAR_FILE = target/MyWebApp1.jar
   COPY ${JAR_FILE} MyWebApp1.jar // Ahi copio la imagen
   ENTRYPOINT ["java", "-jar", "/MyWebApp1.jar"]

   // para correr un archivo java -> java -jar /archivo.jar

   Debemos correr este docker file para poder corer la imagen

   cme ubico en la carpeta del docker file

   docker build -t mtisw/first-app:latest -> estos comandos podemos traerlos en un block de notas el dia de
                                             la prueba
   mtisw -> es la cuenta del docker file (mi propia cuenta)

   docker build -t mtism/first -app:latest . -> va a crear la imagen y se quedaria en el docker desktop
   en la parte de images

   docker images -> para comprobar la imagen mtisw/first-app (Repository)

   para correr la imagen deberia usar (en el caso de usar el puerto 8005
   docker run -d -p 8001:8005 mtisw/first-app
   Si la app corre en el 8090 usaria
   docker run -d -p 8001:8090 mtisw/first-app

   SI la quiero en el exterior conectarme con el 8007
   docker run -d -p 8007:8090 mtisw/first-app

   DOcker ps para comprobar el contenedor correcto

   para corer la app ahora seria:


   localhost:8007/mensaje
   AHora la aplicacion estaria dentro del contenedor
   ENtonces ahora debo subirlo a dockerhub

   hago docker login (en la carpeta del proyecto

   docker login
   -> puede pedir credenciales (ojo)

   luego colocamos (para subir la imagen)
   docker push mtisw/first-app:latest

   Una vez de estar en docker hub lo puede usar cualquiera

   te metes a hub.docker.com/Repositories/mtisw

   podemos buscarla como mtisw/first-app

   Para comprobar borro imagenes, y containers

   -> pego
   docker pull mtisw/first-app en la consola
   se ve en images la de dockerhub

   debe correrlo por consola y deberia funcionar


   docker compose -> permite desplegar imagenes en un computador, escribes un script y en este
   seteas todo lo necesario para el script

   -docker compose -> para levantar toda la aplicacion, que es otro script como el docker
   file pero se llama compose y estipo jar, lo abro en visual estudio y deberia salir algo como

   services:
    web:
        image: mtisw/fist-app:latest
        ports:
            - "8007:8090"
    bd:   // ahi colocar el nombre de las imagenes de mysql o la base de datos usada

    todo lo que està en este sript sirve para correrlo de forma mas facil

    para correrlo usamos (compose es el nombre del archivo ojo)
    docker compose up

    y ahi deberia aparecer lo que deberia aparecer en intellij cuando corress la app

    y asi aceddes al localhost:8007/mensaje rapidamente con solo ese comando y es mucho
    màs limpio
     */

    /*
    Clase 26 septiembre:
    - SLA: el sistema a desarrolllar debe ser 99.5% de SLA
    - como llego a esto?
    - se relaciona con la disponibilidad, uno de los aspectos de la SLA es la disponibilidad
    - si te piden una disponibilidad del 99.5% que significa que al año la aplicación puede estar
    caída 1.83 días (en el peor de los casos cayó 2 días), 10.98 horas al mes, 7.20 minutos al día
    (ver estos datos en high abailability en wikipedia, están en una tabla percentage calculation)
    - el 99.9% es mucho más complejo, al dia puede estar caida en 1.44 minutos

    - en un friday no se puede caer la pagina en más 8.64 segundos, para ello se usa 99.99% con four nines
    (cuatro nueves)
    - quizas en el futuro de la carrera nos enfrentemos a 99.99% con cuatro nueves, esto depende de la
    empresa a la que trabajemos y la importancia del proyecto
    - Un SLA de 90% de un nueve
    - SIEMPRE PREGUNTAR POR LA DISPONIBILIDAD (ES IMPORTANTE SI ES QUE NO TE LO MENCIONAN EN EL CONTRATO
    RECORDARLO)
    - Entre más 9, mejores pagos pero más dificultades (es muy peligroso estar en proyectos con SLA muy altos)
    - Cuando desplieguemos una aplicación web, tira 3 commponentes: el servidor web, la aplicación web y la base
    de datos
    La capa del servidor web es una capa donde hay una pagina web estatica como la de la usach, no
    hay html, javascript (se debe haber hecho con una herramienta), generalmente están de cara al usuario

    Servidor de la aplicación: se conecta con la base de datos, y es anclado mediante enlace a la capa de
    servidor web

    La capa de servidor tiene disponibilidad de 90%, app server del 90& y db server 99% coomo sql
    Dado que es un sistema lineal, la disponibilidad total sería

    disponibilidad_Total = 0.9 * 0.9 * 0.99 = 0.8019
    pero da 80.19% < 99.5%, que es menor al SLA
    Construir software es más que hacer código

    Para ello redundamos

    Ponemos dos servidores web de 90% (web server), donde obtenemos más disponibilidad total

    Ponemos más redundancia a las app server igual, y obtenemos más porcentaje con
    3 web server, y 3 app server, logrando disponibilidad de 98.8%, pero
    un servidor HP cuesta 5.000.000 de pesos, con 7 servidores sería 35.000.000

    Hay que comprar más servidores para llegar a tener un SLA alto
    Por eso es mejor tener en claro los gastos y presupuesto de la empresa para comprar servidores


    Web server (90%) -> App server (90%) -> DB server (99%)

    Y al replicar la base de datos lograríamos 99.8% pero se necesita más espacio y dinero

    El balanzador de carga se encarga para distribuir las cargas (porcentajes)
    ej: la aplicación 1 está con un 60% de carga, ya que tiene un mejor servidor
    la aplicación 2 en cambio está con 40% de carga, ya que tiene un servidor más o menos

    El balanceador de carga puede ser por hardware o software


    flask -> para crear aplicaciones livianas

    app1/Dockerfile (ver esta informacion en towardsdatascience.com/sample-load-balancing-solution-with-docker-and-nginx

    usamos un balanceador de carga, donde un servidor por puertos distintos, vamos balanceando la carga

    En el día de la evaluación explicar los porcentajes de carga, es importante
    Y tambien explicar la cantidad de servicios

    No necesitas mucha RAM, hay que tener cuidado con balanceador de carga, se puede realizar con 1 en
    caso que el computador no soporte ni 2 ni 1

    El balanceador de carga es el encargador de distribuir las tareas

    Igual se desperdician recursos,
    cuando hayan picos de uso

    Donde voy a desplegar: en servidores fisicos, o tecnologias en contenedores?

    Hoy debe hecharse manos en la nube, y el proovedor más grande ahora es en amazon (Aws)
    Si quieres ir a la nube del proveedor es amazon.com/es/

    Para desplegar la aplicación:
    1er lugar -> amazon
    3er lugar -> cloud compute engine google

    Digital ocean da servicios en la nube: es más barato que amazon y compute engine

    Uso de servicio en la nube (amazon)

    - Nosotros queremos desplegar la aplicación en la nube
    Capa gratuita de AWS -> se puede pagar con tarjeta de credito (no te cobra)

    Buscar una tarjeta de crédito e ingresar a amazon precios->capa gratuita de AWS

    Creamos nueva estimacion en amazon
    Usamos servicio: buscar servicio: ec2
    configuramos amazon EC2

    Descripcion: Server 1
    Region: Sao Paulo, (pues es la región más cercana a santiago)

    Tenencia: instancias compartidas (para que otra persona pueda usar ese servidor, osea compartirlo)
              pero al usar instancias dedicadas que es propio, es mucho más caro (no recomendable)

    Sistema operativo: Linux (windows es más caro)

    Crgas de trabajo: uso constante

    Numero de instancias: 1

    t4g.nano    categoria de instancia: general purpose    cpu 2       memoria: 0.5 gib

    usar t4g.small


    La nube no es cara, pero esto es para el server, hay que ver la base de datos y el resto

    - DigitalOcean ->

    - Empezar a probar servidores
    y subir la aplicación ahí
    debe estar en la nube

    docker compose app -> para bajar y levantar todo
    -> el profe pedirá bajar todo y debes levantarlo
    el enlace que le pases debe funcionar

    Configurar amazon ec2
    Usar so linux
    cargas de trabajo: uso constante
    número de instancias: 3


    -> infra as code -> terraform: para crear infraestructura en la nube
    -> asi como escribes la infraestructura de dockerfile
    -> corres el script y todo está configurado en amazon

    -> terraform:
    AWS -> build

    crea una carpeta y script, que es similar al dockerfile

    terraform{

    }

    provider "aws"{
             region = "sao paulo"
    }
    resource "aws_instance" "app_server"{
        ami
        instance_type

    }

    inicializamos el script

    terraform init


    terraform apply (para correr el servidor)


    debemos escribir el script en terraform
    y debemos explicar como crear el servidor

    --------------- clase 27 de septiembre ---------------

    DevOps -> conjunto de buenas prácticas, para reducir el tiempo entre hacer un commit
    de un cambio de un sistema y que ese cambio quede automaticamente en producción, generando
    cálidad

    3 desarrolladores hacen cada uno 2 historias de usuario y cada vez que hacen
    cambios se almacenan en el repositorio central de github

    Para ello necesitamos un servidor especializado, y en ese servidor se debe instalar
    jenkings para hacer integración continua, y este tiene la capacidad de que cuando se de
    cuenta que en el repositorio central llega codigo nuevo (Github), y empieza a tomar
    configuraciones que tu haz realizado, empieza a ensamblar, corre pruebas unitarias y muestra
    resultados de cobertura

    Hasta ayer todo es manual, escribir comandos por linea de comandos, y esto cansa al hacerlo
    continuamente, por eso jenkins se encarga de automatizar este proceso

    Primero se debe tener un repositorio de código fuente
    (git flow sirve para crear brunchs y sirve más para el trabajo en equipo)

    en la imagen de desarrolllo github jenkings spring, la linea cafe es pipeline

    Producción -> cuando la página ya funciona para el uso de clientes
               -> cuando todo está funcionando
    Staging -> espejo de producción, copia casi fiel de producción

    Si logro despegar en staging, debo ajustar errores, y luego al solucionarlos y subirlo a producción
    La probabilidad de cometer errores es baja

    El jenkings te pregunta hasta donde quieres llegar (hasta desarrollo, staging o producción)
    Con el pipeline quiero llegar al


    En el script de jenkings escribimos el script, en el describimos las etapas que queremos tener

    En ella debemos escribir el build de la aplicación


    En la etapa de despliegue bajamos la imagen relacionada
    Tratar de hacer los proyectos automatizados, para ello sirve jenkins

    Ingenieros de box -> especialistas en hacer pipelines






    DevOps -> logra hacer integración continua, integración llega hasta hacer los tests manualmente

    Si logramos integración continua en un proyecto, tenemos que lograr entrega continua, luego
    despliegue continuo, luego la compañía tiene despliegue continuo

    La integración continua no tira nada en producción llega hasta la producción

    El paso a producción se hace manualmente

    En la evaluación se pide hacer un pipeline, y dejar la imagen lista en el docker

    En el docker quedaría la imagen, y desde allí bajan manualmente la imagen

    Estaríamos haciendo despliegue continuo

    // ir a jenkins.io y descargar
    jenkins.io/doc/book/pipeline

    vamos a declarative pipeline fundamentals

    pipeline{
        agent any
        stages {
            stage
        }
    }

    Que jankings haga automaticamente el trabajo sin  boton al hacer un commit
    eso se solicita


     */
}
