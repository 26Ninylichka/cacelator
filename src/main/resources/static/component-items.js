const componentUrl = "http://localhost:8080/components";
const productUrl = "http://localhost:8080/products";

let components = [];
let products = [];

async function loadComponents() {
    const res = await fetch(componentUrl);
    components = await res.json();

    const select = document.getElementById("componentSelect");
    select.innerHTML = "";

    components.forEach(component => {
        const option = `<option value="${component.id}">${component.name}</option>`;
        select.innerHTML += option;
    });

    if (components.length > 0) {
        loadItems();
    }
}

async function loadProducts() {
    const res = await fetch(productUrl);
    products = await res.json();

    const select = document.getElementById("productSelect");
    select.innerHTML = "";

    products.forEach(product => {
        const option = `<option value="${product.id}">${product.name}</option>`;
        select.innerHTML += option;
    });
}

async function addItem() {
    const componentId = document.getElementById("componentSelect").value;
    const productId = document.getElementById("productSelect").value;
    const quantity = document.getElementById("quantity").value;
    const unit = document.getElementById("unit").value;

    await fetch(`http://localhost:8080/components/${componentId}/items`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            productId: productId,
            quantity: quantity,
            unit: unit,
            sortOrder: 1
        })
    });

    document.getElementById("quantity").value = "";
    document.getElementById("unit").value = "";

    loadItems();
}

async function loadItems() {
    const componentId = document.getElementById("componentSelect").value;

    if (!componentId) {
        return;
    }

    const res = await fetch(`http://localhost:8080/components/${componentId}/items`);
    const data = await res.json();

    const table = document.getElementById("itemsTable");
    table.innerHTML = "";

    data.forEach(item => {
        const component = components.find(c => c.id === componentId);
        const product = products.find(p => p.id === item.productId);

        const row = `
            <tr>
                <td>${component ? component.name : ""}</td>
                <td>${product ? product.name : ""}</td>
                <td>${item.quantity ?? ""}</td>
                <td>${item.unit ?? ""}</td>
            </tr>
        `;

        table.innerHTML += row;
    });
}

document.addEventListener("DOMContentLoaded", async () => {
    await loadComponents();
    await loadProducts();

    const componentSelect = document.getElementById("componentSelect");
    componentSelect.addEventListener("change", loadItems);
});