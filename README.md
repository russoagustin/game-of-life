# Juego de la vida de Conway

![Juego de La Vida ejecución](https://i.giphy.com/media/v1.Y2lkPTc5MGI3NjExc3gxZ2s1bnE3djFjNjBidzZ2bWVocWFybmNzYjBxNmY5dGNrYTZ3byZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/ahwvZedNMGCKAqwKb6/giphy.gif)


## Descripción
Implementación en Java de el **Juego de la Vida**, un autómata celular creado por el matemático John Conway.

## Funcionamiento
* Una célula viva con 2 o 3 vecinas vivas permanecerá viva en el siguiente turno.
* Una célula viva con menos de 2 vecinas vivas muere.
* Una célula viva con mas de 3 vecinas vivas muere.
* Una célula muerta con exactamente 3 vecinas vivas revive.

## Requisitos
- JDK 8 o superior.

## Controles
* "Clic" cambia el estado de una célula.
* "space" pausa.
* "g" cambia el grid.
* "c" cambia el color de las células-
* "l" limpia el tablero
* "a" distribución aleatoria de celulas vivas-
* resto de las teclas funcionan como pausa.
