let baseUrl = 'http://localhost:8080/POS/';

getAllItems();

//Button events
// add Item
$("#btnItem").click(function () {
    let formData = $("#ItemForm").serialize();
    $.ajax({
        url: baseUrl+"item",
        method: "post",
        data: formData,
        dataType: "json",
        success: function (res) {
            console.log("success Method Invoked");
            console.log(res);
            alert(res.message);
            getAllItems();
        },
        error: function (error) {
            console.log("Error Method Invoked");
            console.log(JSON.parse(error.responseText));
            alert(JSON.parse(error.responseText).message);
        }
    });
});

//delete Item
$("#btnDelete").click(function () {
    let code = $("#itemCode").val();
    $.ajax({
        url: baseUrl+"item?code=" + code,
        method: "delete",
        success: function (resp) {
            getAllItems();
            alert(resp.message);
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert(message);
        }
    });
});

//update Item
$("#btnUpdate").click(function () {
    let code = $('#itemCode').val();
    let description = $('#itemName').val();
    let qtyOnHand = $('#itemQty').val();
    let unitPrice = $('#itemPrice').val();
    var ItemOb = {
        code: code,
        description: description,
        qtyOnHand: qtyOnHand,
        unitPrice: unitPrice
    }
    $.ajax({
        url: baseUrl+"item",
        method: "put",
        contentType: "application/json",
        data: JSON.stringify(ItemOb),
        dataType: "json",
        success: function (resp) {
            getAllItems();
            alert(resp.message);
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert(message);
        }
    });
});

//getAll Item
$("#btnGetAll").click(function () {
    getAllItems();
});

//getAll Item function
function getAllItems() {
    $("#tblItem").empty();
    $.ajax({
        url: baseUrl+"item",
        success: function (res) {
            for (let c of res.data) {
                let code = c.code;
                let description = c.description;
                let qtyOnHand = c.qtyOnHand;
                let unitPrice = c.unitPrice;

                let row = "<tr><td>" + code + "</td><td>" + description + "</td><td>" + qtyOnHand + "</td><td>" + unitPrice + "</td></tr>";
                $("#tblItem").append(row);
            }
            bindRowClickEvents();
            setTextFieldValues("", "", "", "");
        },
        error: function (error) {
            let message = JSON.parse(error.responseText).message;
            alert(message);
        }
    });
}

//bind events for the table rows function
function bindRowClickEvents() {
    $("#tblItem>tr").click(function () {
        let code = $(this).children(":eq(0)").text();
        let description = $(this).children(":eq(1)").text();
        let qtyOnHand = $(this).children(":eq(2)").text();
        let unitPrice = $(this).children(":eq(3)").text();

        $('#itemCode').val(code);
        $('#itemName').val(description);
        $('#itemQty').val(qtyOnHand);
        $('#itemPrice').val(unitPrice);
    });
}

//set text fields values function
function setTextFieldValues(code, description, qtyOnHand, unitPrice) {
    $('#itemCode').val(code);
    $('#itemName').val(description);
    $('#itemQty').val(qtyOnHand);
    $('#itemPrice').val(unitPrice);
}
