const baseUrl = "http://localhost:8080/products";

async function loadProducts() {
    const response = await fetch(baseUrl);
    const data = await response.json();

    const table = document.getElementById("productsTable");
    table.innerHTML = "";

    data.forEach(product => {
        const row = `
            <tr>
                <td>${product.name ?? ""}</td>
                <td>${product.defaultUnit ?? ""}</td>
            </tr>
        `;
        table.innerHTML += row;
    });
}

async function createProduct() {
    const name = document.getElementById("productName").value;
    const unit = document.getElementById("productUnit").value;

    await fetch(baseUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            name: name,
            defaultUnit: unit
        })
    });

    document.getElementById("productName").value = "";
    document.getElementById("productUnit").value = "";

    loadProducts();
}

loadProducts();