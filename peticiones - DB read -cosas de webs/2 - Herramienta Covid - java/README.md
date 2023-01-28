# HERRAMIENTA COVID

Descarga el .cvs desde el servidor elegido (este proceso demora un rato en este ejemplo), la app lee el archivo descargado o a medio descargar y lo divide en localidades.
Este tambien se puede usar para leer archivos localmente y cargando los emcabezados de los campos.

IMPORTANTE! : Este proyecto se dejo de lado. Es funcional pero el dialogo de carga tiene problemas y nose muestra nntes de iniciar la descarga, en futuras versiones esto se solucionara.
ESTA ECHO PARA ARCHIVOS PEQUEÑOS DE UNAS TRES MIL FILAS. NO UN MILLON. NO SEAS PAYASO!

## Dependencias:

>app\build.gradle 
`
  dependencies {
    ...
    //para almacenar datos en lugar del viejo DBHelper
    
    implementation 'androidx.room:room-runtime:2.4.2'
    annotationProcessor 'androidx.room:room-compiler:2.4.2'
    annotationProcessor 'android.arch.persistence.room:compiler:1.1.1'
  }
`

## Manifiesto:

>app\src\main\AndroidManifest.xml 
` 
<manifest 
xmlns:android="http://schemas.android.com/apk/res/android" 
xmlns:tools="http://schemas.android.com/tools" 
package="tu.app">
    
    /** SE AGREGA ESTE PERMISO **/
    <uses-permission android:name="android.permission.INTERNET"/> 
    <application
    . . .
`
 
## Capturas:
![pantalla Inicial](https://github.com/Mad-Bones/android-java-stuff/blob/main/peticiones%20-%20DB%20read%20-cosas%20de%20webs/2%20-%20Herramienta%20Covid%20-%20java/covidA.webp)
![pantalla lista vacia](https://github.com/Mad-Bones/android-java-stuff/blob/main/peticiones%20-%20DB%20read%20-cosas%20de%20webs/2%20-%20Herramienta%20Covid%20-%20java/covidB.webp)
![pantalla lista](https://github.com/Mad-Bones/android-java-stuff/blob/main/peticiones%20-%20DB%20read%20-cosas%20de%20webs/2%20-%20Herramienta%20Covid%20-%20java/covidC.webp)

## Como probarlo?:

` 1 - ` Crear un proyecto cualquiera en Android Studio.

` 2 - ` Agregar primero lo de Manifiesto* y despues Dependencias* y darle a "sync" que figurara luego de cambiar el `build.graddle`.

` 3 - ` Correr el proyecto o darle a Build para que android studio prepare lo demas. 

` 4 - ` Puedes copiar todo el contenido de >app\src\main\java\com\estaApp\... a la misma direccion de tu proyecto y Android Studio seencarga de renombrar los Accesos. Caso contrario sera necesario usar la logica y cambiar el nombre del paquete de los archivos que pegaste.

` 5 - ` Ahora dependiendo de los recursos que usa la app se copian algunas carpetas y archivos dentro de >app\src\main\res. Estas son: \layout - \drawable - \values\colors.xml - \values\strings.xml

` Opcional - ` El uso de la logica es opcional pero necesaria en este caso. en caso de necesitar algun pedaso de codigo, este se busca y se agrega al mismo archivo. Lo mismo sucede en caso de las carpetas o algoritmos etc... Es mejor fallar por muerte que por falta de neuronas. =)
