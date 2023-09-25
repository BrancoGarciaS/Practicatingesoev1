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

}
