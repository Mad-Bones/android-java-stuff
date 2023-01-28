# BUSCADOR DE MATRICULA

Simple buscador de matricula escrito en android java.

## Dependencias:

>app\build.gradle 
`
  dependencies {
    ...
    //llamadas y peticiones http
    implementation 'com.squareup.okhttp:okhttp:2.7.5'
    implementation 'com.android.volley:volley:1.2.1'
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
![pantallaInicial](https://github.com/Mad-Bones/android-java-stuff/blob/main/peticiones%20-%20DB%20read%20-cosas%20de%20webs/buscar%20matricula/buscaMatriA.webp)
![pantallaResultado](https://github.com/Mad-Bones/android-java-stuff/blob/main/peticiones%20-%20DB%20read%20-cosas%20de%20webs/buscar%20matricula/buscaMatriB.webp)

## Como probarlo?:

` 1 - ` Crear un proyecto cualquiera en Android Studio.

` 2 - ` Agregar primero lo de Manifiesto* y despues Dependencias* y darle a "sync" que figurara luego de cambiar el `build.graddle`.

` 3 - ` Correr el proyecto o darle a Build para que android studio prepare lo demas. 

` 4 - ` Puedes copiar todo el contenido de >app\src\main\java\com\estaApp\... a la misma direccion de tu proyecto y Android Studio seencarga de renombrar los Accesos. Caso contrario sera necesario usar la logica y cambiar el nombre del paquete de los archivos que pegaste.

` 5 - ` Ahora dependiendo de los recursos que usa la app se copian algunas carpetas y archivos dentro de >app\src\main\res. Estas son: \layout - \drawable - \values\colors.xml - \values\strings.xml

` Opcional - ` El uso de la logica es opcional pero necesaria en este caso. en caso de necesitar algun pedaso de codigo, este se busca y se agrega al mismo archivo. Lo mismo sucede en caso de las carpetas o algoritmos etc... Es mejor fallar por muerte que por falta de neuronas. =)
