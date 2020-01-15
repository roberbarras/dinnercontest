function registrar() {

    var inicioErrorVarios = "Los campos";
    var inicioErrorUno = "El campo";
    var mensajeError = [];
    var finErrorVarios = ", son obligatorios.";
    var finErrorUno = " es obligatorio.";

    class User {
        constructor(userName, email, accessName, password) {
            this.userName = userName;
            this.email = email;
            this.accessName = accessName;
            this.password = password;
        }
    }

    var user = new User(document.getElementById("register_name_input").value,
        document.getElementById("register_email_input").value,
        document.getElementById("register_access_input").value,
        document.getElementById("register_password_input").value);

    if (user.userName === "") {
        mensajeError.push(" nombre");
    }

    if (user.accessName === "") {
        mensajeError.push(" usuario");
    }

    if (user.password === "") {
        mensajeError.push(" contraseña");
    }

    if (mensajeError.length > 1) {
        swal("Error ", " ", "error", {
            position: 'top-end',
            title: inicioErrorVarios.concat(mensajeError.join(', ')).concat(finErrorVarios),
        });
    } else if (mensajeError.length === 1) {
        swal("Error ", " ", "error", {
            position: 'top-end',
            title: inicioErrorUno.concat(mensajeError).concat(finErrorUno),
        });
    } else {
        var request = new XMLHttpRequest();
        request.open("POST", "https://dinnercontest.herokuapp.com/api/user");
        request.setRequestHeader("Content-Type", "application/json");
        request.send(JSON.stringify(user));

        swal("cargando", " ", {
            title: 'Cargando',
            position: 'top-end',
            icon: 'loading.svg',
            button: false,
        });

        request.onload = function () {
            if (request.readyState == 4 && request.status == 201) {
                swal("Registro completado", " ", "success", {
                    position: 'top-end',
                    icon: 'success',
                    title: 'Registro completado',
                    button: false,
                    timer: 1500
                });
                setLogin();
            }
        }
    }

}

function exist() {
    var request = new XMLHttpRequest();
    request.open("GET", "https://dinnercontest.herokuapp.com/api/user/exist/" + document.getElementById("register_access_input").value);
    request.send();
    request.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200 && this.responseText == 'true') {
            document.getElementById("register_access_input").style.setProperty("box-shadow", "0 0 10px #CC0000");
            document.getElementById("unavailable").style.display = "block";
            document.getElementById("registarbutton").disabled = true;
        } else {
            document.getElementById("register_access_input").style.setProperty("box-shadow", "none");
            document.getElementById("unavailable").style.display = "none";
            document.getElementById("registarbutton").disabled = false;
        }
    };
}

function login() {

    class Login {
        constructor(accessName, password) {
            this.accessName = accessName;
            this.password = password;
        }

    }

    var login = new Login(document.getElementById("login_access_input").value,
        document.getElementById("login_password_input").value);

    var request = new XMLHttpRequest();


    request.onload = function () {
        if (request.readyState == 4 && request.status == 201) {
            swal.close();
            swal("Bienvenido ", " ", "success", {
                position: 'top-end',
                title: 'Bienvenido ' + login.accessName,
                button: false,
                timer: 1000
            });
        }
        if (request.readyState == 4 && (request.status == 403)) {
            swal.close();
            swal("Datos incorrectos", " ", "error", {
                position: 'top-end',
                title: 'Datos incorrectos'
            });
        }
    };
    request.ontimeout = function () {
        swal.close();
        swal("Ha habido un problema, inténtelo más tarde", " ", "error", {
            position: 'top-end',
            title: 'Ha habido un problema, inténtelo más tarde'
        });
    };
    request.open("POST", "https://dinnercontest.herokuapp.com/api/login", true);
    request.setRequestHeader("Content-Type", "application/json");
    swal("cargando", " ", {
        title: 'Cargando',
        position: 'top-end',
        icon: 'loading.svg',
        button: false,
    });
    request.timeout = 6000;
    request.send(JSON.stringify(login));

}

function setLogin() {
    document.getElementById("register").style.display = "none";
    document.getElementById("login").style.display = "block";
}

function setRegister() {
    document.getElementById("login").style.display = "none";
    document.getElementById("register").style.display = "block";
}