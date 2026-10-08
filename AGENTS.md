# SendMessage

Práctica de 2º DAM (Unidad 2): app Android en Kotlin con vistas XML. `SendMessageActivity` (launcher) envía un `Message` a `ViewMessageActivity` dentro de un `Bundle`.

Responde siempre en español.

## Comandos

```bash
./gradlew :app:compileDebugKotlin   # comprobación rápida de que compila
./gradlew assembleDebug             # APK de depuración
./gradlew installDebug              # instalar en emulador o dispositivo conectado
./gradlew dokkaGenerate             # regenera la documentación en documentation/html y documentation/javadoc
```

Después de cambiar código o recursos, comprueba que compila antes de dar el trabajo por terminado.

## Estructura

- `app/src/main/java/com/example/sendmessage/`: `SendMessageActivity`, `ViewMessageActivity`, `SendMessageApplication` y `model/` (`Message`, `Person`).
- `app/src/main/res/`: layouts, drawables, fuentes y `values/` (`strings.xml`, `colors.xml`, `dimens.xml`).
- `gradle/libs.versions.toml`: catálogo de versiones. Los plugins (Dokka, Parcelize) se declaran ahí y se aplican con `alias(libs.plugins...)`.
- `documentation/`: documentación generada por Dokka, publicada con Jekyll en GitHub Pages (`.github/workflows/`).
- `screenshots/`: capturas usadas en el `README.md`.

## Convenciones

- **Paso de datos:** los modelos son `Parcelable` con `@Parcelize`. Se envían con `bundle.putParcelable(...)` y se leen con `IntentCompat.getParcelableExtra(intent, KEY, Clase::class.java)`. No uses las APIs obsoletas `getParcelableExtra(String)` ni `getSerializableExtra`.
- **Claves de los extras:** constantes en el `companion object` de la actividad que las lee (`ViewMessageActivity.KEY_MESSAGE`, `KEY_SENDER`).
- **Recursos:** nada de textos, colores ni tamaños escritos a mano en los layouts. Van en `strings.xml`, `colors.xml` y `dimens.xml`.
- **Imágenes de la segunda pantalla:** tienen que ser *Vector Assets* (XML en `res/drawable/`), no PNG.
- **Ciclo de vida:** los métodos `onStart`, `onResume`, `onPause`, `onStop` y `onDestroy` van dentro de `//region Ciclo de vida de una Actividad` y cada uno escribe `Log.d(TAG, "<Actividad> -> <método>()")`. La etiqueta es `LogSendMessageActivity`.
- **Comentarios:** KDoc en español, con etiquetas HTML (`<ol>`, `<li>`, `<b>`, `<code>`) y `@author Jorge Peralta`, `@version`, `@param`, `@see`. Los ficheros XML también llevan comentarios.

## Requisitos de la práctica

El enunciado completo está en `SendMessage.pdf` (no se sube al repositorio). Además del código, pide:

- `README.md` con capturas de la app en el emulador, de Logcat y de `/data/data/` de la app, la explicación de la estructura y de las decisiones de diseño, y enlaces a Android Developers.
- `CHANGELOG.md` con al menos las versiones v0.1 y v1.0.
- `MANUAL_USUARIO.md` en lenguaje sencillo: cómo escribir y enviar un mensaje en 3 pasos.
- `app-release.apk` firmado en la raíz del proyecto.
