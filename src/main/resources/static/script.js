function registrar() {

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

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/user");
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(user));

    swal("Cargando", {
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
    request.open("POST", "http://localhost:8080/api/login", true);
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