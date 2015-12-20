function logout() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (xhttp.status == 200) {
            if (xhttp.readyState == 4) {
                console.log("logout success");
                window.location = xhttp.getResponseHeader("Location");
            }
        }
    };
    xhttp.open("POST", "/api/logout", true);
    xhttp.send();
}