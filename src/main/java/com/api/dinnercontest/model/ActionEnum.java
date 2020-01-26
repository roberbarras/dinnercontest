package com.api.dinnercontest.model;

public enum ActionEnum {

    GROUP_CREATED("El grupo :grupo ha sido creado"),
    USER_JOINED("El usuario :usuario se ha unido al grupo :grupo"),
    USER_VOTED("El usuario :usuario ha votado el restaurante :restaurante"),
    NEW_DINNER("El usuario :usuario ha publicado su restaurante"),
    WINNER("El usuario :usuario ha ganado la ronda"),
    NEW_SEQUENCE("Se ha pubilcado el órden de la próxima ronda"),
    NEW_WEIGHING("Se ha modificado la ponderación"),
    NEW_CATEGORY("Se ha añadido una categoría nueva"),
    NEW_GUEST("El usuario :usuario se ha unido como invitado al grupo :grupo"),
    EMPTY_NOTIFICATION("Aún no perteneces a ninǵún grupo");

    private final String message;

    ActionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
