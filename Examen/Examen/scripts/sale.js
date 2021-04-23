const cookie = document.cookie;
let category;
let products;
let seller = {};

let productList = {};
let subtotal = 0;
let taxes = 0;
let total = 0;

function initInfo(cookie) {
  try {
    const token = JSON.parse(cookie);
    console.log(token);
    seller = token;

    if (token.type === "admin") {
      document.getElementById("corte").classList.remove("hidden");
    }
    document.getElementById("seller").innerHTML = token.uname;
  } catch (error) {}
  getCategories();
}

function getCategories() {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/Examen/Productos", true);
  xhr.onload = function () {
    const categories = JSON.parse(this.responseText);

    for (index in categories) {
      let i = parseInt(index) + 1;
      document.getElementById("category-" + i).innerHTML =
        categories[parseInt(index)].nombre;
    }
    category = 1;
    getProducts();
  };

  xhr.send();
}

function getProducts() {
  var xhr = new XMLHttpRequest();
  xhr.open(
    "GET",
    `http://localhost:8080/Examen/Productos?category=${category}`,
    true
  );
  xhr.onload = function () {
    products = JSON.parse(this.responseText);

    products.forEach((product, index) => {
      let productHtml = document.getElementById("product-select-" + index);
      productHtml.classList.remove("hidden");
      productHtml.innerHTML = product.nombre;
    });
  };
  xhr.send();
}

function selectCategory() {
  category = document.getElementById("category").value;

  getProducts();
  // for (let index = 0; index < 4; index++) {
  //   let productHtml = document.getElementById("product-select-" + index);
  //   productHtml.classList.remove("hidden");
  //   productHtml.innerHTML = `Producto ${index + 1} - ${category}`;
  // }
}

function addProduct(index) {
  const selectedProduct = products[parseInt(index)];

  const { nombre } = selectedProduct;

  if (productList[nombre]) {
    productList[nombre]["quantity"] = productList[nombre]["quantity"] + 1;
  } else {
    productList[nombre] = {
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
  subtotal = 0;
  Object.keys(productList).forEach((key) => {
    const { precio, quantity: cantidad } = productList[key];
    const precioTotal = precio * cantidad;
    subtotal += Number(precioTotal);
  });

  const subtotalHtml = document.getElementById("subtotal");
  subtotalHtml.innerHTML = `$${subtotal}`;
}
function getTaxes() {
  taxes = 0;
  taxes = subtotal * 0.16;
  taxes = Number(taxes.toFixed(3));

  const taxesHtml = document.getElementById("taxes");
  taxesHtml.innerHTML = `$${taxes}`;
}
function getTotal() {
  total = 0;
  total = Number(subtotal) + Number(taxes);

  const totalHtml = document.getElementById("total");
  totalHtml.innerHTML = `$${total}`;
}

function placeItems() {
  const products = document.getElementById("products");

  removeAllChildNodes(products);

  Object.keys(productList).forEach((key) => {
    const { nombre, precio, quantity } = productList[key];
    const price = precio * quantity;

    const item = document.createElement("p");
    item.classList.add("product-item");

    const quantityHtml = document.createElement("span");
    quantityHtml.classList.add("product-quantity");
    quantityHtml.innerHTML = `${quantity}`;
    item.appendChild(quantityHtml);

    const nameHtml = document.createElement("span");
    nameHtml.classList.add("product-name");
    nameHtml.innerHTML = `${nombre}`;
    item.appendChild(nameHtml);

    const unitHtml = document.createElement("span");
    unitHtml.classList.add("product-price-unit");
    unitHtml.innerHTML = `$${precio}`;
    item.appendChild(unitHtml);

    const priceHtml = document.createElement("span");
    priceHtml.classList.add("product-price");
    priceHtml.innerHTML = `$${price}`;
    item.appendChild(priceHtml);

    products.appendChild(item);
  });
}

function pay() {
  const productListArray = Object.keys(productList).map(
    (key) => productList[key]
  );
  const data = {
    products: productListArray,
    subtotal,
    total,
    seller,
  };

  var xmlhttp = new XMLHttpRequest();
  var url = "http://localhost:8080/Examen/Ventas";
  xmlhttp.open("POST", url);
  xmlhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
  xmlhttp.send(JSON.stringify(data));
  cancel();
}

function cancel() {
  productList = {};
  taxes = 0;
  total = 0;
  subtotal = 0;

  const products = document.getElementById("products");
  removeAllChildNodes(products);

  getSubtotal();
  getTaxes();
  getTotal();
}

initInfo(cookie);
