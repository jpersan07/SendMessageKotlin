# Registro de cambios

Todos los cambios importantes de SendMessage se apuntan en este fichero.

El formato sigue [Keep a Changelog](https://keepachangelog.com/es-ES/1.1.0/) y las versiones siguen [Versionado Semántico](https://semver.org/lang/es/).

## [1.0] - 2026-10-08

Paso de datos completado y documentación generada.

### Añadido

- APK firmado (`app-release.apk`) en la raíz del repositorio.
- Icono de confirmación en la pantalla de recepción, creado como *Vector Asset* (`baseline_check_circle_24.xml`).
- Traducción de los textos de la app al inglés (`values-en/strings.xml`).
- Plugin `kotlin-parcelize` en el catálogo de versiones (`gradle/libs.versions.toml`).
- Workflow de GitHub Actions que genera la documentación con Dokka y la publica con Jekyll en GitHub Pages.
- Capturas de la app, de Logcat y del Device Manager en el README.
- Licencia MIT en el fichero `LICENSE`.
- Manual de usuario (`MANUAL_USUARIO.md`) y este registro de cambios.

### Cambiado

- `Message` y `Person` pasan de `Serializable` a `Parcelable` con `@Parcelize`.
- `SendMessageActivity` envía el mensaje con `bundle.putParcelable()`.
- `ViewMessageActivity` lee el mensaje con `IntentCompat.getParcelableExtra()` en lugar de `getSerializableExtra()`, que está obsoleto.
- README reorganizado: inicio rápido que se puede copiar y pegar, secciones de depuración plegables y enlaces a la documentación publicada en GitHub Pages.

### Corregido

- El título «SMS APP» y el nombre de la app del manifiesto estaban escritos a mano; ahora salen de `strings.xml`.
- Los márgenes y tamaños de las dos pantallas estaban escritos a mano; ahora salen de `dimens.xml`.
- Errata en el texto de ayuda en inglés: «Writte here your message» pasa a «Write your message here».
- El título de la pantalla de envío quedaba debajo de la barra de estado; ahora el layout deja sitio a las barras del sistema.

## [0.1] - 2026-10-02

Configuración inicial y pantallas.

### Añadido

- Proyecto Android en Kotlin con vistas XML, `minSdk` 24 y `targetSdk` 37.
- Pantalla de envío (`SendMessageActivity`): título «SMS APP», campo de texto y botón **Enviar**.
- Pantalla de recepción (`ViewMessageActivity`): muestra el mensaje y el nombre del remitente.
- Modelo de datos `Message` y `Person`, enviados de una pantalla a otra dentro de un `Bundle`.
- Fuentes Super Waffles y Bitcount.
- Mensajes en Logcat para cada método del ciclo de vida, con la etiqueta `LogSendMessageActivity`.
- Primera documentación de la API generada con Dokka (HTML y Javadoc).

### Corregido

- Mensaje de Logcat equivocado en `onStart()`.

### Eliminado

- Carpeta `.idea/` del repositorio.

[1.0]: https://github.com/jpersan07/SendMessageKotlin/releases/tag/v1.0
