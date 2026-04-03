const API_URL = "http://localhost:8080";
const DEFAULT_USER_ID = "11111111-1111-1111-1111-111111111111";

const headers = {
    "Content-Type": "application/json",
    "X-User-Id": DEFAULT_USER_ID
};

async function loadDesserts() {
    try {
        const response = await fetch(`${API_URL}/desserts`, { headers });
        const desserts = await response.json();
        
        const list = document.getElementById("dessertList");
        list.innerHTML = "";

        if (desserts.length === 0) {
            list.innerHTML = '<p style="color: var(--text-muted); font-size: 14px; text-align: center; padding: 20px;">No recipes registered yet.</p>';
            return;
        }

        desserts.forEach(dessert => {
            const card = document.createElement("div");
            card.className = "card";
            card.style.display = "flex";
            card.style.justifyContent = "space-between";
            card.style.alignItems = "center";
            card.style.padding = "16px";
            card.style.marginBottom = "8px";
            
            card.innerHTML = `
                <div>
                    <h4 style="margin: 0; color: var(--primary); font-size: 16px;">${dessert.name}</h4>
                    <div style="font-size: 12px; color: var(--text-muted); display: flex; gap: 12px; margin-top: 4px;">
                        <span><b style="color: #6366f1">W:</b> ${dessert.baseWeight || "—"}kg</span>
                        <span><b style="color: #6366f1">D:</b> ${dessert.baseDiameter || "—"}cm</span>
                        <span><b style="color: #6366f1">S:</b> ${dessert.servingsDefault || "—"}</span>
                    </div>
                </div>
                <button class="secondary-btn" style="padding: 6px 12px; font-size: 12px;" onclick="deleteDessert('${dessert.id}')">Delete</button>
            `;
            list.appendChild(card);
        });
    } catch (error) {
        showMessage("Failed to load recipes.", "error");
    }
}

async function createDessert() {
    const name = document.getElementById("dessertName").value;
    const baseWeight = document.getElementById("baseWeight").value;
    const baseDiameter = document.getElementById("baseDiameter").value;
    const baseServings = document.getElementById("baseServings").value;

    if (!name) {
        showMessage("Please enter a recipe name.", "error");
        return;
    }

    try {
        const response = await fetch(`${API_URL}/desserts`, {
            method: "POST",
            headers: headers,
            body: JSON.stringify({
                name: name,
                baseWeight: baseWeight ? Number(baseWeight) : null,
                baseDiameter: baseDiameter ? Number(baseDiameter) : null,
                servingsDefault: baseServings ? Number(baseServings) : null
            })
        });

        if (!response.ok) {
            throw new Error("Failed to create recipe meta.");
        }

        document.getElementById("dessertName").value = "";
        document.getElementById("baseWeight").value = "";
        document.getElementById("baseDiameter").value = "";
        document.getElementById("baseServings").value = "";

        showMessage("Recipe meta created successfully!", "success");
        await loadDesserts();
    } catch (error) {
        showMessage(error.message, "error");
    }
}

async function deleteDessert(id) {
    if (!confirm("Are you sure you want to delete this recipe meta?")) return;

    try {
        const response = await fetch(`${API_URL}/desserts/${id}`, {
            method: "DELETE",
            headers: headers
        });

        if (response.ok) {
            showMessage("Recipe meta deleted.", "success");
            await loadDesserts();
        }
    } catch (error) {
        showMessage("Failed to delete.", "error");
    }
}

function showMessage(text, type) {
    const box = document.getElementById("messageBox");
    if (!box) return;

    box.textContent = text;
    if (type === "error") {
        box.style.backgroundColor = "#FEF2F2";
        box.style.color = "#991B1B";
        box.style.borderColor = "#FEE2E2";
    } else {
        box.style.backgroundColor = "#ECFDF5";
        box.style.color = "#065F46";
        box.style.borderColor = "#A7F3D0";
    }
    box.style.display = "block";
}

window.onload = loadDesserts;