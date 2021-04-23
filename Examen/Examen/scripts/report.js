function getReport() {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/Examen/Ventas", true);
  xhr.onload = function () {
    const report = JSON.parse(this.responseText);
    paintReport(report);
  };

  xhr.send();
  //   TODO: Descomentar para probar
  //   const report = {
  //     total: 100,
  //     vendedores: [
  //       { id: 12, total: 30 },
  //       { id: 15, total: 70 },
  //     ],
  //   };
  //   paintReport(report);
}

function paintReport({ total, vendedores }) {
  const salesHtml = document.getElementById("sales");
  removeAllChildNodes(salesHtml);
  const totalHtml = document.getElementById("total");
  totalHtml.innerHTML = `$${total || 0}`;

  vendedores.forEach((vendedor) => {
    const saleItemHtml = document.createElement("p");
    saleItemHtml.classList.add("sale-item");

    const idHtml = document.createElement("span");
    idHtml.innerHTML = vendedor.id;
    const totalSellerHtml = document.createElement("span");
    totalSellerHtml.innerHTML = `$${vendedor.total || 0}`;

    saleItemHtml.appendChild(idHtml);
    saleItemHtml.appendChild(totalSellerHtml);
    salesHtml.appendChild(saleItemHtml);
  });
}

function removeAllChildNodes(parent) {
  while (parent.firstChild) {
    parent.removeChild(parent.firstChild);
  }
}

getReport();
