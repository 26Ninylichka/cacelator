const API = "/components";

function getToken() {
    return localStorage.getItem("token");
}

// LOAD COMPONENTS
async function loadComponents() {
    const tbody = document.getElementById("componentsTableBody");
    tbody.innerHTML = "";

    try {
        const response = await fetch(API, {
            headers: {
                "Authorization": "Bearer " + getToken()
            }
        });

        const data = await response.json();

        if (!response.ok) {
            tbody.innerHTML = `<tr><td class="empty">Error loading</td></tr>`;
            return;
        }

        if (data.length === 0) {
            tbody.innerHTML = `<tr><td class="empty">No components yet</td></tr>`;
            return;
        }

        data.forEach(c => {
            const row = `
                <tr>
                    <td>${c.name}</td>
                    <td>${c.description ?? ""}</td>
                    <td>
                        <button class="delete-btn" onclick="deleteComponent('${c.id}')">
                            ❌
                        </button>
                    </td>
                </tr>
            `;
            tbody.innerHTML += row;
        });

    } catch (e) {
        console.error(e);
    }
}

// CREATE
async function createComponent() {
    const name = document.getElementById("name").value;
    const description = document.getElementById("description").value;

    if (!name) {
        alert("Enter name");
        return;
    }

    await fetch(API, {
        method: "POST",
        headers: {
            "Authorization": "Bearer " + getToken(),
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name,
            description
        })
    });

    document.getElementById("name").value = "";
    document.getElementById("description").value = "";

    loadComponents();
}

// DELETE
async function deleteComponent(id) {
    await fetch(API + "/" + id, {
        method: "DELETE",
        headers: {
            "Authorization": "Bearer " + getToken()
        }
    });

    loadComponents();
}

// INIT
document.addEventListener("DOMContentLoaded", loadComponents);