window.onload = function () {

    const originalFetch = window.fetch;

    window.fetch = async (...args) => {
        const response = await originalFetch(...args);

        // если это login
        if (args[0].includes('/auth/login')) {
            const clone = response.clone();
            const data = await clone.json();

            if (data.token) {
                console.log("JWT saved:", data.token);

                // сохраняем токен
                localStorage.setItem("jwt", data.token);

                // авто-вставка в Swagger
                window.ui.preauthorizeApiKey("bearerAuth", "Bearer " + data.token);
            }
        }

        return response;
    };
};