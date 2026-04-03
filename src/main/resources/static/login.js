async function login() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("/auth/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: email,
                password: password
            })
        });

        const data = await response.json();

        console.log("Login response:", data);

        if (!response.ok) {
            alert("Ошибка логина");
            return;
        }

        localStorage.setItem("token", data.token);

        alert("Успешный вход");
        window.location.href = "/components.html";

    } catch (error) {
        console.error("Login error:", error);
        alert("Ошибка запроса");
    }
}