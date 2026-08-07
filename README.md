# Domino Game Java

Proyecto modular en Java para un juego de dominó con separación por capas (dominio, DTO, presentación, lógica de negocio y comunicación cliente/servidor).

## Estructura general

- `DominoDominio`: entidades y reglas del dominio.
- `DominoDTO`: objetos de transferencia y mapeos.
- `Presentacion*`: módulos de interfaz gráfica.
- `SolicitudesRespuestas`: contratos de comunicación.
- `DominoClientProxy` / `DominoServerProxy`: proxies de red.
- `DominoBroker`: enrutador/intermediario de mensajes.
- `PartidaServidor`: servidor de partida.
- `DominoNegocios`: orquestación principal del cliente.

## Requisitos

- JDK 22 (recomendado para compilar todos los módulos de presentación).
- Maven 3.9+

## Compilación

Desde la raíz del repositorio `/home/runner/work/domino-game-java/domino-game-java`, ejecutar en este orden:

```bash
cd DominoDominio && mvn clean install
cd ../DominoDTO && mvn clean install
cd ../PresentacionContenedorPantallas && mvn clean install
cd ../PresentacionCrearUsuario && mvn clean install
cd ../PresentacionMenuDomino && mvn clean install
cd ../PresentacionSalaEspera && mvn clean install
cd ../PresentacionBuscarSalas && mvn clean install
cd ../PresentacionPartidaDomino && mvn clean install
cd ../SolicitudesRespuestas && mvn clean install
cd ../DominoClientProxy && mvn clean install
cd ../DominoServerProxy && mvn clean install
cd ../DominoBroker && mvn clean install
cd ../PartidaServidor && mvn clean install
cd ../DominoNegocios && mvn clean install
```

## Ejecución

Iniciar en terminales separadas, respetando este orden:

1. **Broker**
   ```bash
   cd /home/runner/work/domino-game-java/domino-game-java/DominoBroker
   mvn exec:java -Dexec.mainClass="domino.broker.RunBroker"
   ```
2. **Servidor de partida**
   ```bash
   cd /home/runner/work/domino-game-java/domino-game-java/PartidaServidor
   mvn exec:java -Dexec.mainClass="main.ServerRunner"
   ```
3. **Cliente (app principal)**
   ```bash
   cd /home/runner/work/domino-game-java/domino-game-java/DominoNegocios
   mvn exec:java -Dexec.mainClass="main.AppRunner"
   ```

> Si tu configuración de Maven no incluye `exec-maven-plugin`, puedes ejecutar las clases principales desde tu IDE después de compilar los módulos.
