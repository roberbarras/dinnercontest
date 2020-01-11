function register() {

    class User {
        constructor(userName, email, accessName, password) {
            this.userName = userName;
            this.email = email;
            this.accessName = accessName;
            this.password = password;
        }

    }

    var user = new User(document.getElementById("register_nombre_input").value,
        document.getElementById("register_mail_input").value,
        document.getElementById("register_access_input").value,
        document.getElementById("register_password_input").value);

    console.log("CLASE USER:" + JSON.stringify(user));

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/user");
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(user));
    console.log("RESPONSE: " + request.response);

    switchLogin();
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

    console.log("CLASE USER:" + JSON.stringify(login));

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/api/login", false);
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(login));

    console.log("Estado de la peti:" + request.status);

    alert(request.status == 201 ? "Bienvenido " + login.accessName : "Datos incorrectos");
    console.log("RESPONSE: " + request.response);
}

function switchRegister() {
    document.getElementById("buttonLogin").style.display = "none";
    document.getElementById("login").style.display = "none";
    document.getElementById("buttonRegister").style.display = "block";
    document.getElementById("register").style.display = "block";
}

function switchLogin() {
    document.getElementById("buttonRegister").style.display = "none";
    document.getElementById("register").style.display = "none";
    document.getElementById("buttonLogin").style.display = "block";
    document.getElementById("login").style.display = "block";
}