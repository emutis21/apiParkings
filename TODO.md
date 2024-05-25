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

- [x] Crear entidades JPA para `Parqueadero`
- [x] Crear repositorio `ParqueaderoRepository`
- [x] Crear servicio `ParqueaderoService`
- [x] Crear controlador `ParqueaderoController`
- [x] Crear DTOs para `Parqueadero`
- [x] Implementar CRUD para `Parqueadero`

## CRUD para Áreas

- [x] Crear entidades JPA para `Area`
- [x] Crear repositorio `AreaRepository`
- [x] Crear servicio `AreaService`
- [x] Crear controlador `AreaController`
- [x] Crear DTOs para `Area`
- [x] Implementar CRUD para `Area`

## CRUD para Espacios

- [x] Crear entidades JPA para `Espacio`
- [x] Crear repositorio `EspacioRepository`
- [x] Crear servicio `EspacioService`
- [x] Crear controlador `EspacioController`
- [x] Crear DTOs para `Espacio`
- [x] Implementar CRUD para `Espacio`

## CRUD para Vigilantes

- [x] Crear entidades JPA para `Vigilante`
- [x] Crear repositorio `VigilanteRepository`
- [x] Crear servicio `VigilanteService`
- [x] Crear controlador `VigilanteController`
- [x] Crear DTOs para `Vigilante`
- [x] Implementar CRUD para `Vigilante`

## Gestión de Registros de Vehículos

- [x] Crear entidades JPA para `Registro`
- [x] Crear repositorio `RegistroRepository`
- [x] Crear servicio `RegistroService`
- [x] Crear controlador `RegistroController`
- [x] Crear DTOs para `Registro`
- [x] Implementar registro de entrada de vehículos
- [x] Implementar registro de salida de vehículos

## Gestión de Pagos

- [x] Crear entidades JPA para `Pago`
- [x] Crear repositorio `PagoRepository`
- [x] Crear servicio `PagoService`
- [x] Crear controlador `PagoController`
- [x] Crear DTOs para `Pago`
- [x] Implementar registro de pagos
- [x] Implementar confirmación de pagos

## CRUD para tarifas

- [x] Crear entidades JPA para `Tarifa`
- [x] Crear repositorio `TarifaRepository`
- [x] Crear servicio `TarifaService`
- [x] Crear controlador `TarifaController`
- [x] Crear DTOs para `Tarifa`
- [x] Implementar CRUD para `Tarifa`

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
- [x] CRUD para Parqueaderos
- [x] CRUD para Áreas
- [x] CRUD para Espacios
- [x] CRUD para Vigilantes
- [x] Gestión de Registros de Vehículos
- [x] Gestión de Pagos
- [x] CRUD para Tarifas