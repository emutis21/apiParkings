# TODO

## Estructura del Proyecto

- [x] Crear estructura básica del proyecto con Spring Boot
- [x] Configurar conexión a la base de datos PostgreSQL

## CRUD para Localidades

- [x] Crear entidades JPA para `Localidad`
- [x] Crear repositorio `LocalidadRepository`
- [x] Crear servicio `LocalidadService`
- [x] Crear controlador `LocalidadController`
- [x] Crear DTOs para `Localidad`
- [x] Implementar CRUD para `Localidad`

## CRUD para Parqueaderos

- [ ] Crear entidades JPA para `Parqueadero`
- [ ] Crear repositorio `ParqueaderoRepository`
- [ ] Crear servicio `ParqueaderoService`
- [ ] Crear controlador `ParqueaderoController`
- [ ] Crear DTOs para `Parqueadero`
- [ ] Implementar CRUD para `Parqueadero`

## CRUD para Áreas

- [ ] Crear entidades JPA para `Area`
- [ ] Crear repositorio `AreaRepository`
- [ ] Crear servicio `AreaService`
- [ ] Crear controlador `AreaController`
- [ ] Crear DTOs para `Area`
- [ ] Implementar CRUD para `Area`

## CRUD para Espacios

- [ ] Crear entidades JPA para `Espacio`
- [ ] Crear repositorio `EspacioRepository`
- [ ] Crear servicio `EspacioService`
- [ ] Crear controlador `EspacioController`
- [ ] Crear DTOs para `Espacio`
- [ ] Implementar CRUD para `Espacio`

## CRUD para Vigilantes

- [ ] Crear entidades JPA para `Vigilante`
- [ ] Crear repositorio `VigilanteRepository`
- [ ] Crear servicio `VigilanteService`
- [ ] Crear controlador `VigilanteController`
- [ ] Crear DTOs para `Vigilante`
- [ ] Implementar CRUD para `Vigilante`

## Gestión de Registros de Vehículos

- [ ] Crear entidades JPA para `Registro`
- [ ] Crear repositorio `RegistroRepository`
- [ ] Crear servicio `RegistroService`
- [ ] Crear controlador `RegistroController`
- [ ] Crear DTOs para `Registro`
- [ ] Implementar registro de entrada de vehículos
- [ ] Implementar registro de salida de vehículos

## Gestión de Pagos

- [ ] Crear entidades JPA para `Pago`
- [ ] Crear repositorio `PagoRepository`
- [ ] Crear servicio `PagoService`
- [ ] Crear controlador `PagoController`
- [ ] Crear DTOs para `Pago`
- [ ] Implementar registro de pagos
- [ ] Implementar confirmación de pagos

## Cálculo de Tarifas

- [ ] Implementar lógica para calcular tarifas basadas en el tiempo de entrada y salida de los vehículos

## Generación de Estadísticas

- [ ] Implementar endpoint para estadísticas de entrada y salida de vehículos por día
- [ ] Implementar endpoint para estadísticas de entrada y salida de vehículos por mes
- [ ] Implementar endpoint para estadísticas de entrada y salida de vehículos por año

## Seguridad y Autenticación

- [ ] Configurar Spring Security
- [ ] Implementar autenticación JWT para usuarios y trabajadores
- [ ] Proteger endpoints con roles adecuados

## Documentación y Pruebas

- [ ] Documentar API con Swagger
- [ ] Crear pruebas unitarias para servicios
- [ ] Crear pruebas de integración para controladores
- [ ] Asegurarse de que todos los endpoints están adecuadamente cubiertos por pruebas

## Mejoras y Optimización

- [ ] Revisar y optimizar consultas a la base de datos
- [ ] Implementar caché donde sea necesario
- [ ] Mejorar la gestión de errores y excepciones

## Despliegue

- [ ] Preparar configuración para despliegue en entorno de producción
- [ ] Configurar CI/CD (e.g., GitHub Actions, Jenkins)
- [ ] Desplegar aplicación en un servidor (e.g., Heroku, AWS, GCP)

## Tareas Completadas

- [x] Configuración inicial del proyecto
- [x] Conexión a la base de datos
- [x] CRUD para Localidades