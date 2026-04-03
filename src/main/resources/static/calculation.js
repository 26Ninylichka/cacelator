const dessertUrl = "http://localhost:8080/desserts";
const calcRunUrl = "http://localhost:8080/calc-runs";
const calculationUrl = "http://localhost:8080/calculations";

// Default userId for simulation until full auth flow is integrated
const DEFAULT_USER_ID = "11111111-1111-1111-1111-111111111111";

let currentCalcRunId = null;
let desserts = [];

const headers = {
    "Content-Type": "application/json",
    "X-User-Id": DEFAULT_USER_ID
};

async function loadDesserts() {
    try {
        const res = await fetch(dessertUrl, { headers });
        const data = await res.json();
        desserts = data;

        const select = document.getElementById("dessertSelect");
        select.innerHTML = '<option value="" disabled selected>Select a dessert...</option>';

        data.forEach(dessert => {
            const option = document.createElement("option");
            option.value = dessert.id;
            option.textContent = dessert.name;
            select.appendChild(option);
        });
    } catch (error) {
        showMessage("Failed to load recipes. Check server connection.", "error");
    }
}

function handleModeChange() {
    const mode = document.getElementById("inputMode").value;

    document.getElementById("weightBlock").classList.add("hidden-block");
    document.getElementById("diameterBlock").classList.add("hidden-block");
    document.getElementById("servingsBlock").classList.add("hidden-block");

    if (mode === "BY_WEIGHT") document.getElementById("weightBlock").classList.remove("hidden-block");
    if (mode === "BY_DIAMETER") document.getElementById("diameterBlock").classList.remove("hidden-block");
    if (mode === "BY_SERVINGS") document.getElementById("servingsBlock").classList.remove("hidden-block");
}

async function createCalculation() {
    const dessertId = document.getElementById("dessertSelect").value;
    const inputMode = document.getElementById("inputMode").value;
    
    if (!dessertId) {
        showMessage("Please select a dessert recipe.", "error");
        return;
    }

    const requestBody = {
        dessertId: dessertId,
        inputMode: inputMode
    };

    if (inputMode === "BY_WEIGHT") requestBody.targetWeight = document.getElementById("targetWeight").value;
    if (inputMode === "BY_DIAMETER") requestBody.targetDiameter = document.getElementById("targetDiameter").value;
    if (inputMode === "BY_SERVINGS") requestBody.targetServings = document.getElementById("targetServings").value;

    try {
        const res = await fetch(calcRunUrl, {
            method: "POST",
            headers: headers,
            body: JSON.stringify(requestBody)
        });

        if (!res.ok) {
            const err = await res.json();
            throw new Error(err.message || "Calculation failed");
        }

        const data = await res.json();
        currentCalcRunId = data.id;
        document.getElementById("calcRunResult").textContent = currentCalcRunId.toUpperCase();

        showMessage("Configuration computed successfully!", "success");

        await loadIngredients();
        await loadShoppingList();
    } catch (error) {
        showMessage(error.message, "error");
    }
}

async function loadIngredients() {
    if (!currentCalcRunId) {
        showMessage("No active calculation session.", "error");
        return;
    }

    try {
        const res = await fetch(`${calculationUrl}/${currentCalcRunId}/ingredients`, { headers });
        const data = await res.json();

        const table = document.getElementById("ingredientsTable");
        table.innerHTML = "";

        if (data.length === 0) {
            table.innerHTML = "<tr><td colspan='5' style='text-align: center; padding: 20px;'>No ingredients found.</td></tr>";
            return;
        }

        data.forEach(item => {
            const row = `
                <tr>
                    <td style="font-weight: 600;">${item.componentName ?? "—"}</td>
                    <td>${item.productName ?? "—"}</td>
                    <td><span style="color: var(--text-muted); font-size: 12px; font-weight: 700;">${item.unit ?? "—"}</span></td>
                    <td style="color: var(--text-muted);">${item.originalQuantity ?? "0"}</td>
                    <td style="font-weight: 700; color: var(--primary);">${item.scaledQuantity ?? "0"}</td>
                </tr>
            `;
            table.innerHTML += row;
        });
    } catch (error) {
        showMessage("Failed to fetch ingredient ratios.", "error");
    }
}

async function loadShoppingList() {
    if (!currentCalcRunId) return;

    try {
        const res = await fetch(`${calculationUrl}/${currentCalcRunId}/shopping-list`, { headers });
        const data = await res.json();

        const table = document.getElementById("shoppingTable");
        table.innerHTML = "";

        if (data.length === 0) {
            table.innerHTML = "<tr><td colspan='3' style='text-align: center; padding: 20px;'>Shopping list empty.</td></tr>";
            return;
        }

        data.forEach(item => {
            const row = `
                <tr>
                    <td style="font-weight: 600;">${item.productName ?? "—"}</td>
                    <td><span style="color: var(--text-muted); font-size: 12px; font-weight: 700;">${item.unit ?? "—"}</span></td>
                    <td style="font-weight: 700; color: var(--accent);">${item.totalQuantity ?? "0"}</td>
                </tr>
            `;
            table.innerHTML += row;
        });
    } catch (error) {
        showMessage("Failed to generate shopping totals.", "error");
    }
}

function showMessage(text, type) {
    const box = document.getElementById("messageBox");
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

handleModeChange();
loadDesserts();