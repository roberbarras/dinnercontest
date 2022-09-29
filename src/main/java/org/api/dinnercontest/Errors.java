package org.api.dinnercontest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum Errors {

    DUPLICATED("001", "DUPLICATE_ENTRY", "Registro duplicado"),
    NOT_FOUND("002", "Index: 0, Size: 0", "Elemento no encontrado"),
    USER_ALREADY_EXISTS("003", "insert into users (user_name, access_name, creation_date, email, last_login, local_privacy, global_privacy, password) values (?, ?, ?, ?, ?, ?, ?, ?)", "Ya existe un usuario con ese nombre"),
    GROUP_ALREADY_EXISTS("004", "insert into groups (group_name, creation_date) values (?, ?)", "Ya existe un grupo con ese nombre"),
    USER_ALREADY_BELONGS_GROUP("005", "insert into user_group (\"user\", \"group\", creation_date, valid) VALUES (?, ?, ?, true)", "El usuario ya pertenece al grupo"),
    ERROR_CREATING_DINNER("006", "insert into restaurants (name, host, id_group, date, address, photo, visible, creation_date, visible_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?)", "Error al crear la cita"),
    ERROR_CREATING_CATEGORY("007", "insert into categories (group_id, category_name, weighing, user_id, creation_date, removal_date) values (?, ?, ?, ?, ?, null)", "Ya existe una categoría con ese nombre en el grupo");

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String code;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private final String error;
    private final String message;

    Errors(String code, String error, String message) {
        this.code = code;
        this.error = error;
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    private static final Map<String, Errors> errors = new HashMap<>();

    static {
        Arrays.stream(Errors.values()).forEach(elem -> errors.put(elem.getError(), elem));
    }

    public static Errors get(String error) {
        return errors.get(error.substring(error.indexOf('[')+1, error.indexOf(']')));
    }
}