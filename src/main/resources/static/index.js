if (localStorage.getItem("token") != null) {
    //TODO token check
    location.href = "/app.html";
} else {
    location.href = "/login.html";
}