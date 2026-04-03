window.onload = function () {
    const buttons = document.querySelectorAll("button");
    const button = buttons[2];

    if (!button) {
        alert("Кнопка расчета не найдена");
        return;
    }

    button.onclick = async function (event) {
        event.preventDefault();

        alert("Кнопка расчета нажата");

        try {
            const response = await fetch("/desserts", {
                method: "GET",
                headers: {
                    "Authorization": "Bearer " + localStorage.getItem("jwt")
                }
            });

            const data = await response.json();

            alert("Ответ: " + JSON.stringify(data));
        } catch (error) {
            alert("Ошибка запроса");
            console.error(error);
        }
    };
};