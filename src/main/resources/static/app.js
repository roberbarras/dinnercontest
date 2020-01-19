function checkLogin() {

    class UserToken {
        constructor(accessName, token) {
            this.accessName = accessName;
            this.token = token;
        }
    }

    const userToken = new UserToken(localStorage.getItem("user"), localStorage.getItem("token"));

    if (localStorage.getItem("token") != null && localStorage.getItem("user") != null) {
        const request = new XMLHttpRequest();
        request.open("POST", "http://localhost:8080/api/check-token");
        request.setRequestHeader("Content-Type", "application/json");
        request.send(JSON.stringify(userToken));

        request.onload = function () {
            if (request.status !== 200) {
                location.href = "/login.html";
            }
        }
    } else {
        location.href = "/login.html"
    }
}