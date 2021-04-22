const cookie = document.cookie;
let category;
const productList = {};
let subtotal = 0;
let taxes = 0;
let total = 0;

function initInfo(cookie) {
    const token = JSON.parse(cookie);
    console.log(token);

    document.getElementById("seller").innerHTML = token.uname;
    getCategories();
}

function getCategories() {
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "http://localhost:8080/Examen/Productos", true);
    xhr.onload = function () {
        const categories = JSON.parse(this.responseText);

        for (index in categories) {
            let i = parseInt(index) + 1;
            document.getElementById("category-" + i)
                .innerHTML = categories[parseInt(index)].nombre;
        }
    };
    xhr.send();
}

function selectCategory() {
  category = document.getElementById("category").value;

  for (let index = 0; index < 4; index++) {
    let productHtml = document.getElementById("product-select-" + index);
    productHtml.classList.remove("hidden");
    productHtml.innerHTML = `Producto ${index + 1} - ${category}`;
  }
}

function addProduct(index) {
  const selectedProduct = {
    name: document.getElementById("product-select-" + index).innerHTML,
    unit: 45,
  };
  const { name, unit } = selectedProduct;

  if (productList[name]) {
    productList[name]["quantity"] = productList[name]["quantity"] + 1;
  } else {
    productList[name] = {
      ...selectedProduct,
      quantity: 1,
    };
  }

  placeItems();
  getSubtotal();
  getTaxes();
  getTotal();
}

function removeAllChildNodes(parent) {
  while (parent.firstChild) {
    parent.removeChild(parent.firstChild);
  }
}

function getSubtotal() {
  Object.keys(productList).forEach((key) => {
    const { unit, quantity } = productList[key];
    const price = unit * quantity;
    subtotal += Number(price);
  });

  const subtotalHtml = document.getElementById("subtotal");
  subtotalHtml.innerHTML = `$${subtotal}`;
}
function getTaxes() {
  taxes = subtotal * 0.16;
  taxes = taxes;

  const taxesHtml = document.getElementById("taxes");
  taxesHtml.innerHTML = `$${taxes}`;
}
function getTotal() {
  total = Number(subtotal) + Number(taxes);

  const totalHtml = document.getElementById("total");
  totalHtml.innerHTML = `$${total}`;
}

function placeItems() {
  const products = document.getElementById("products");

  removeAllChildNodes(products);

  Object.keys(productList).forEach((key) => {
    const { name, unit, quantity } = productList[key];
    const price = unit * quantity;

    const item = document.createElement("p");
    item.classList.add("product-item");

    const quantityHtml = document.createElement("span");
    quantityHtml.classList.add("product-quantity");
    quantityHtml.innerHTML = `${quantity}`;
    item.appendChild(quantityHtml);

    const nameHtml = document.createElement("span");
    nameHtml.classList.add("product-name");
    nameHtml.innerHTML = `${name}`;
    item.appendChild(nameHtml);

    const unitHtml = document.createElement("span");
    unitHtml.classList.add("product-price-unit");
    unitHtml.innerHTML = `$${unit}`;
    item.appendChild(unitHtml);

    const priceHtml = document.createElement("span");
    priceHtml.classList.add("product-price");
    priceHtml.innerHTML = `$${price}`;
    item.appendChild(priceHtml);

    products.appendChild(item);
  });
}

initInfo(cookie);