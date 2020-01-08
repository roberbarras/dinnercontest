function validateForm() {

    class User {
        constructor(userName, email, accessName, password) {
            this.userName = userName;
            this.email = email;
            this.accessName = accessName;
            this.password = password;
        }

    }

    var user = new User(document.getElementById("username").value,
        document.getElementById("email").value,
        document.getElementById("accessname").value,
        document.getElementById("password").value);

    console.log("CLASE USER:" + JSON.stringify(user));

    var request = new XMLHttpRequest();
    request.open("POST", "http://localhost:8080/v1/user");
    request.setRequestHeader("Content-Type", "application/json");
    request.send(JSON.stringify(user));
    console.log("RESPONSE: " + request.response);
}