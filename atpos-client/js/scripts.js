const form = document.querySelector('form');
const ul = document.querySelector('ul');
const tbody = document.querySelector('tbody');
const button = document.querySelector('button');
const input = document.getElementById('item');
let itemsArray = localStorage.getItem('items') ? JSON.parse(localStorage.getItem('items')) : [];

localStorage.setItem('items', JSON.stringify(itemsArray));
const data = JSON.parse(localStorage.getItem('items'));

const liMaker = (text) => {
    var tr = document.createElement('tr');
    //orden
    var tdOrden = document.createElement('td');
    tdOrden.className = "column1";
    tdOrden.textContent = text;
    tr.appendChild(tdOrden);

    //Cantidad
    var tdCantidad = document.createElement('td');
    tdCantidad.textContent = (Math.round((Math.random() * 8) + 1));
    tr.appendChild(tdCantidad);

    //nombre
    var tdNombre = document.createElement('td');
    tdNombre.className = "column1";
    enlaces = ["Queso Pera", "Chocorramo", "Manzana", "Chocolate", "Leche", "Piña", "Cebolla", "Azucar", "Arroz", "Mandarina"];
    random_num = (Math.round((Math.random() * 8) + 1))
    tdNombre.textContent = enlaces[random_num];
    tr.appendChild(tdNombre);

    //Price
    var tdPrecio = document.createElement('td');
    tdPrecio.className = "column1";
    precios = ["75400", "82415", "9415", "10645", "1234", "2300", "6121", "7440", "7487", "5600"];
    random_numPr = (Math.round((Math.random() * 8) + 1))
    tdPrecio.textContent = precios[random_numPr];
    tr.appendChild(tdPrecio);

    //Total
    var tdTotal = document.createElement('td');
    tdTotal.textContent = '$' + tdPrecio.textContent * tdCantidad.textContent;
    tr.appendChild(tdTotal);


    tbody.appendChild(tr);
}

form.addEventListener('submit', function (e) {
    e.preventDefault();
    itemsArray.push(input.value);
    localStorage.setItem('items', JSON.stringify(itemsArray));
    liMaker(input.value);
    input.value = "";
});
/*
data.forEach(item => {
    liMaker(item);
});*/



$("#btnComprar").click(function () {
    var tableElem = $('#factura');
    var tableObj = tableElem.tableToJSON();
    var tableStr = JSON.stringify(tableObj);
    var items = tableStr;

    var numFact = (Math.round((Math.random() * 5478) + 10));
    localStorage.setItem('factura' + numFact, JSON.stringify(items));

    $("#factura").find("tr:gt(0)").remove();

    $('#modalCon').text('Transacción realizada con éxito. Factura N° ' + numFact);
    modal.style.display = "block";

    localStorage.setItem('items', JSON.stringify(""));
});


$("#btnLimpiar").click(function () {
    $("#factura").find("tr:gt(0)").remove();
    localStorage.setItem('items', JSON.stringify(""));
    itemsArray = [];
});


// jquery .ready
$(document).ready(function () {
  //  addListItem("jquery document ready");
});


setInterval(function () {
    
    $.ajax({
        url: "http://localhost:8090/api/v1/checkStatus"
        
    }).done(function () {    
        $("#iconoVerificador").addClass("circuloOnline");
    }).fail(function (jqXHR, textStatus, errorThrown) {       
        $("#iconoVerificador").addClass("circuloOffline");
    });

}, 4000);







// Get the modal
var modal = document.getElementById("myModal");

// Get the button that opens the modal
var btn = document.getElementById("myBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];



// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}