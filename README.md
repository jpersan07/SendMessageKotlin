# SendMessage

> App Android de ejemplo, en Kotlin y vistas XML, para estudiantes que aprenden a pasar datos entre dos `Activity` con un `Intent` y un objeto `Parcelable`.

[![Documentación](https://github.com/jpersan07/SendMessageKotlin/actions/workflows/desplegar_dokka.yml/badge.svg)](https://github.com/jpersan07/SendMessageKotlin/actions/workflows/desplegar_dokka.yml)
![Versión](https://img.shields.io/badge/versi%C3%B3n-1.0-blue)
![minSdk](https://img.shields.io/badge/minSdk-24-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin&logoColor=white)
[![Licencia: MIT](https://img.shields.io/badge/licencia-MIT-green)](LICENSE)

**Estado:** práctica terminada (v1.0) de la Unidad 2 de 2º DAM. Es un proyecto de aprendizaje y no se mantiene de forma activa.

<table>
  <tr>
    <td align="center"><img src="screenshots/EnvioDeMensaje.png" alt="Pantalla de envío con el texto «Hola caracola» escrito" width="260"></td>
    <td align="center"><img src="screenshots/MensajeEnviado.png" alt="Pantalla de recepción con el mensaje «Hola caracola», el remitente Jorge Peralta y un icono de confirmación" width="260"></td>
  </tr>
  <tr>
    <td align="center"><b>Pantalla de envío</b> (<code>SendMessageActivity</code>)</td>
    <td align="center"><b>Pantalla de recepción</b> (<code>ViewMessageActivity</code>)</td>
  </tr>
</table>

## ¿Por qué SendMessage?

Pasar datos de una pantalla a otra es de lo primero que hay que aprender en Android, y los ejemplos que circulan siguen usando APIs obsoletas. Este proyecto lo resuelve en dos pantallas y con la forma recomendada hoy:

- Envía un objeto completo (`Message`) con `@Parcelize`, sin escribir el código de `Parcelable` a mano.
- Lo lee con `IntentCompat.getParcelableExtra()`, que no está obsoleto en ninguna versión de Android.
- Deja en Logcat la secuencia del ciclo de vida de las dos `Activity`, para ver qué pasa al cambiar de pantalla.

Lo que **no** hace: no envía SMS de verdad ni pide permisos. El remitente y el destinatario están escritos en el código.

## Características

- **Pantalla de envío** (`SendMessageActivity`): campo de texto y botón **Enviar**.
- **Pantalla de recepción** (`ViewMessageActivity`): muestra el mensaje, el remitente en negrita y un icono vectorial de confirmación.
- **Modelo `Parcelable`**: `Message` y `Person` con `@Parcelize`.
- **Ciclo de vida en Logcat** con la etiqueta `LogSendMessageActivity`.
- **Documentación de la API** con Dokka, publicada en [GitHub Pages](https://jpersan07.github.io/SendMessageKotlin/).

## Inicio rápido

Necesitas Android Studio con el SDK 37 y un emulador abierto o un móvil conectado por USB (ver [Instalación](#instalación)).

```bash
git clone https://github.com/jpersan07/SendMessageKotlin.git
cd SendMessageKotlin
adb devices                  # el emulador o el móvil debe salir con el estado "device"
./gradlew installDebug       # compila e instala la app
adb shell am start -n com.example.sendmessage/.SendMessageActivity   # la abre
```

**Resultado esperado:** se abre la pantalla «SMS APP». Escribe un texto, pulsa **Enviar** y en la segunda pantalla aparece tu mensaje firmado por «Jorge Peralta».

## Uso

Todo el paso de datos ocurre en `SendMessageActivity.sendMessage()`, que crea el `Message`, lo guarda en un `Bundle` junto con el nombre del remitente y abre la segunda pantalla:

```kotlin
val sender = Person("12345678A", "Jorge", "Peralta")
val receiver = Person("87654321B", "Juan", "Perez")
val message = Message(1, etMessageText.text.toString(), sender, receiver)

bundle.putParcelable(ViewMessageActivity.KEY_MESSAGE, message)
bundle.putString(ViewMessageActivity.KEY_SENDER, "${sender.name} ${sender.surname}")
intent.putExtras(bundle)
startActivity(intent)
```

`ViewMessageActivity` lo lee en `onCreate()`:

```kotlin
val message = IntentCompat.getParcelableExtra(intent, KEY_MESSAGE, Message::class.java)
tvMessage.text = message?.content
tvSender.text = intent.getStringExtra(KEY_SENDER)
```

Si solo quieres usar la app, sin mirar el código, consulta el [manual de usuario](MANUAL_USUARIO.md).

## Decisiones de diseño

- **`Parcelable` en vez de `Serializable`.** `Parcelable` es el mecanismo propio de Android para pasar objetos entre componentes y es más rápido que `Serializable`, que usa reflexión. El plugin `kotlin-parcelize` genera todo el código a partir de la anotación `@Parcelize`, así que las clases del modelo siguen ocupando una línea.
- **`IntentCompat.getParcelableExtra()` para leer el extra.** `Intent.getParcelableExtra(String)` está obsoleto desde Android 13 y la versión con tipo solo existe a partir de esa API. `IntentCompat` llama a la que corresponda según la versión del dispositivo.
- **Un objeto en vez de varios extras.** Se envía el `Message` entero y no el texto, el remitente y el destinatario por separado. Si el modelo crece, el código que envía y recibe no cambia.
- **Claves en constantes.** `KEY_MESSAGE` y `KEY_SENDER` viven en el `companion object` de `ViewMessageActivity`, que es quien las lee. Así las dos pantallas usan siempre la misma clave.
- **Imagen vectorial en la segunda pantalla.** El icono de confirmación es un XML de `res/drawable/` creado con *New > Vector Asset*. Se ve nítido en cualquier densidad de pantalla y no hace falta guardar un PNG por cada una.

## Instalación

### Requisitos

- Android Studio reciente, compatible con AGP 9.4.1.
- Android SDK 37 (`compileSdk` y `targetSdk`) y las *Platform Tools*, que incluyen `adb`.
- Un emulador o un móvil con Android 7.0 o superior (API 24).
- Conexión a internet la primera vez: Gradle 9.6 descarga por su cuenta el JDK 25 que usa su *daemon*.

### Otras formas de instalarla

- **Desde Android Studio:** abre la carpeta del proyecto y ejecuta la configuración `app`.
- **Con el APK firmado:** sin compilar nada, `adb install app-release.apk` desde la raíz del repositorio.

### Comandos útiles

```bash
./gradlew :app:compileDebugKotlin   # comprueba que el código compila
./gradlew assembleDebug             # genera el APK de depuración
./gradlew test                      # tests unitarios (solo el de ejemplo de la plantilla)
./gradlew connectedAndroidTest      # tests instrumentados (necesitan un dispositivo)
./gradlew dokkaGenerate             # regenera la documentación en documentation/
```

## Arquitectura y tecnologías

```
SendMessage/
├── app/src/main/
│   ├── java/com/example/sendmessage/
│   │   ├── SendMessageActivity.kt     # pantalla de envío (launcher)
│   │   ├── ViewMessageActivity.kt     # pantalla de recepción
│   │   ├── SendMessageApplication.kt  # clase Application registrada en el manifiesto
│   │   └── model/                     # Message y Person (Parcelable)
│   ├── res/
│   │   ├── layout/                    # activity_send_message.xml y activity_view_message.xml
│   │   ├── drawable/                  # vectores (icono de confirmación, icono de la app)
│   │   ├── font/                      # Super Waffles y Bitcount
│   │   └── values/                    # strings.xml, colors.xml, dimens.xml, themes.xml
│   └── AndroidManifest.xml
├── documentation/                     # documentación generada por Dokka (html/ y javadoc/)
├── recursos/                          # recursos originales (fuentes, imágenes, SVG)
├── screenshots/                       # capturas de este README
├── .github/workflows/                 # despliegue de la documentación con Jekyll en GitHub Pages
└── gradle/libs.versions.toml          # catálogo de versiones (Dokka y Parcelize incluidos)
```

**Tecnologías:** Kotlin · vistas XML (`LinearLayout`, `ConstraintLayout`) · Kotlin Parcelize · AppCompat · Material Components · Core y Activity KTX · Dokka 2.2.0 · JUnit 4 · Espresso

## Depuración

Capturas y comandos para seguir la app por dentro. Despliega cada apartado para verlo.

<details>
<summary><b>Ciclo de vida en Logcat</b></summary>

Cada método del ciclo de vida escribe un mensaje con `Log.d(TAG, ...)`. Los métodos están dentro de `//region Ciclo de vida de una Actividad` para poder plegarlos en el IDE. Para verlos, filtra Logcat por `LogSendMessageActivity` y pasa de una pantalla a otra.

![Logcat filtrado por LogSendMessageActivity con la secuencia del ciclo de vida](screenshots/Logcat.jpeg)

La captura muestra el orden real de los eventos:

1. Al abrir la app, `SendMessageActivity` pasa por `onCreate()`, `onStart()` y `onResume()`.
2. Al pulsar **Enviar**, `SendMessageActivity` llama a `onPause()`. Luego `ViewMessageActivity` ejecuta `onStart()` y `onResume()`. Solo cuando la segunda pantalla ya se ve, `SendMessageActivity` llama a `onStop()`.
3. Al relanzar la app (proceso nuevo, con otro PID), `SendMessageActivity` vuelve a pasar por `onCreate()`, `onStart()` y `onResume()`.

</details>

<details>
<summary><b>Dispositivos y ADB</b></summary>

Las capturas se hicieron en un emulador **Pixel 5** (Android 17, API 37.2). En el Device Manager también aparece un móvil físico **OPPO CPH2659** (Android 16). Para comprobar la conexión:

```bash
adb devices -l   # cada dispositivo conectado aparece con el estado "device"
adb shell        # abre una consola dentro del dispositivo
```

![Device Manager de Android Studio con el emulador Pixel 5 y el móvil OPPO conectados](screenshots/DeviceManager.jpeg)

</details>

<details>
<summary><b>Carpeta <code>/data/data/</code> de la app</b></summary>

Cada app guarda sus datos privados en `/data/data/<paquete>/`. Para entrar en la de esta app desde `adb shell` (funciona con la versión de depuración, sin necesidad de root):

```bash
adb shell
run-as com.example.sendmessage
cd /data/data/com.example.sendmessage
ls -la
```

También se puede ver desde Android Studio en **View > Tool Windows > Device Explorer**.

<!-- Pendiente: cuando exista screenshots/DataData.png, quitar este comentario y dejar la línea siguiente:
![Contenido de /data/data/com.example.sendmessage visto con run-as](screenshots/DataData.png)
-->

</details>

## Documentación

- **Referencia de la API (Dokka):** [web en GitHub Pages](https://jpersan07.github.io/SendMessageKotlin/), con versión [HTML](https://jpersan07.github.io/SendMessageKotlin/html/) y [Javadoc](https://jpersan07.github.io/SendMessageKotlin/javadoc/). Se regenera sola en cada push a `main`.
- **[Manual de usuario](MANUAL_USUARIO.md):** cómo enviar un mensaje en 3 pasos.
- **[Registro de cambios](CHANGELOG.md):** qué cambió en cada versión.

Documentación oficial de Android Developers que se ha usado:

- [Intents y filtros de intents](https://developer.android.com/guide/components/intents-filters)
- [El ciclo de vida de una actividad](https://developer.android.com/guide/components/activities/activity-lifecycle)
- [Generador de implementación Parcelable (`@Parcelize`)](https://developer.android.com/kotlin/parcelize)
- [Agregar gráficos vectoriales multidensidad (Vector Asset Studio)](https://developer.android.com/studio/write/vector-asset-studio)
- [Cómo ver registros con Logcat](https://developer.android.com/studio/debug/logcat)
- [Android Debug Bridge (ADB)](https://developer.android.com/tools/adb)
- [Documentar código Kotlin con KDoc y Dokka](https://kotlinlang.org/docs/kotlin-doc.html)

## Contribuir

Es un proyecto de clase, pero cualquier mejora es bienvenida, sobre todo si ayuda a entender mejor el paso de datos entre `Activity`:

1. Abre un [*issue*](https://github.com/jpersan07/SendMessageKotlin/issues) contando el error o la idea.
2. Haz un *fork*, crea una rama y comprueba que compila con `./gradlew :app:compileDebugKotlin`.
3. Envía un *pull request* explicando el cambio.

Los comentarios del código van en español y en formato KDoc.

## Autor

Jorge Peralta · [@jpersan07](https://github.com/jpersan07)

## Licencia

Distribuido con licencia [MIT](LICENSE). Puedes usar, copiar y modificar el código siempre que mantengas el aviso de copyright.
