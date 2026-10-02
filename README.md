# SendMessage

> App Android en Kotlin para aprender a pasar datos entre dos `Activity`: escribes un mensaje en una pantalla y lo ves recibido en la otra.

![Kotlin](https://img.shields.io/badge/Kotlin-Android-7F52FF?logo=kotlin&logoColor=white)
![minSdk](https://img.shields.io/badge/minSdk-24-3DDC84?logo=android&logoColor=white)
![targetSdk](https://img.shields.io/badge/targetSdk-37-3DDC84?logo=android&logoColor=white)
![Versión](https://img.shields.io/badge/versión-1.0.0-blue)

## ¿Por qué este proyecto?

Es un ejercicio didáctico pequeño. Cubre lo básico de una app Android con vistas XML en un código fácil de leer:

- Crear un `Intent` explícito para abrir una segunda `Activity`.
- Mandar un objeto entero (`Message`, que es `Serializable`) dentro de un `Bundle`, en vez de pasar los datos uno a uno.
- Ver el ciclo de vida de una `Activity` en Logcat.

## Funcionalidades

- **Pantalla de envío** (`SendMessageActivity`, la que se abre al lanzar la app): un campo de texto y un botón **Enviar**.
- **Pantalla de recepción** (`ViewMessageActivity`): muestra «MENSAJE RECIBIDO», el texto que se envió y el nombre del remitente. Usa edge-to-edge con ajuste a las barras del sistema.
- **Modelo de datos**: `Message(id, content, sender, receiver)` y `Person(dni, name, surname)`.
- **Logs del ciclo de vida**: las dos actividades escriben en Logcat con la etiqueta `LogSendMessageActivity`.
- **Fuentes propias**: Super Waffles y Bitcount.
- **Documentación generada con Dokka**, en formato HTML y Javadoc.

## Inicio rápido

```bash
git clone https://github.com/jpersan07/SendMessageKotlin.git
cd SendMessageKotlin
./gradlew installDebug   # con un emulador o un dispositivo conectado
```

También puedes abrir la carpeta en Android Studio y ejecutar la configuración `app`.

**Resultado esperado:** se abre la pantalla «SMS APP». Escribe un texto y pulsa **Enviar**. En la segunda pantalla aparecen tu mensaje y el remitente («Jorge Peralta»).

## Cómo funciona

`SendMessageActivity.sendMessage()` crea un `Message` y lo mete en un `Bundle` junto con el nombre del remitente:

```kotlin
val message = Message(1, etMessageText.text.toString(), sender, receiver)
bundle.putSerializable(ViewMessageActivity.KEY_MESSAGE, message)
bundle.putString(ViewMessageActivity.KEY_SENDER, "${sender.name} ${sender.surname}")
intent.putExtras(bundle)
startActivity(intent)
```

`ViewMessageActivity` lo recupera en `onCreate()`:

```kotlin
val message = intent.getSerializableExtra(KEY_MESSAGE) as? Message
tvMessage.text = message?.content
tvSender.text = intent.getStringExtra(KEY_SENDER)
```

> Las personas remitente y destinataria están escritas a mano en el código (`Person("12345678A", "Jorge", "Peralta")`). La app no envía SMS reales ni necesita permisos.

Para ver el ciclo de vida, filtra Logcat por `LogSendMessageActivity` y navega entre las dos pantallas.

## Requisitos e instalación

- Android Studio reciente, compatible con AGP 9.4.1
- Android SDK 37 (`compileSdk` y `targetSdk`)
- Un dispositivo o emulador con Android 7.0 o superior (API 24 o más)

Comandos útiles:

```bash
./gradlew assembleDebug          # genera el APK de depuración
./gradlew test                   # tests unitarios locales
./gradlew connectedAndroidTest   # tests instrumentados (necesitan un dispositivo)
./gradlew dokkaGenerate          # regenera la documentación en documentation/
```

## Estructura del proyecto

```
SendMessage/
├── app/src/main/
│   ├── java/com/example/sendmessage/
│   │   ├── SendMessageActivity.kt     # pantalla de envío (launcher)
│   │   ├── ViewMessageActivity.kt     # pantalla de recepción
│   │   ├── SendMessageApplication.kt
│   │   └── model/                     # Message, Person
│   ├── res/                           # layouts, fuentes, drawables, strings
│   └── AndroidManifest.xml
├── documentation/                     # documentación generada (html/ y javadoc/)
├── recursos/                          # recursos originales (fuentes, imágenes, SVG)
└── gradle/libs.versions.toml          # catálogo de versiones
```

## Documentación

La referencia de la API ya está generada en el repo:

- HTML: [`documentation/html/index.html`](documentation/html/index.html)
- Javadoc: [`documentation/javadoc/index.html`](documentation/javadoc/index.html)

## Tecnologías

Kotlin · AppCompat · ConstraintLayout · Material Components · Core/Activity KTX · Dokka 2.2.0 · JUnit 4 · Espresso

## Contribuir

Es un proyecto de aprendizaje, pero las sugerencias son bienvenidas. Abre un *issue* o un *pull request* en [GitHub](https://github.com/jpersan07/SendMessageKotlin).

## Autor

Jorge Peralta

## Licencia

Todavía no tiene licencia. Mientras no se añada un archivo `LICENSE`, se aplican los derechos de autor por defecto.
