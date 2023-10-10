let baseUrl = 'http://localhost:8080/POS/';

getAllItems();
bindRowClickEvents()

$("#btnGetAllItems").click(function () {
    getAllItems();
});

function getAllItems() {
    $("#tblItem").empty();

    $.ajax({
        url: baseUrl + 'item',
        dataType: "json",
        method: "GET",

        success: function (response) {
            let items = response.data;

            for (let i in items) {
                let item = items[i];
                let code = item.code;
                let description = item.description;
                let qtyOnHand = item.qtyOnHand;
                let unitPrice = item.unitPrice;
                let row = `<tr><td>${code}</td><td>${description}</td><td>${qtyOnHand}</td><td>${unitPrice}</td></tr>`;
                $("#tblItem").append(row);
            }
            setTextFields("", "", "", "");
        },
        error: function (error) {
            alert(JSON.parse(error.responseText).message);
            setTextFields("", "", "", "");
        }
    });
}

// bind table row values to text field on click
function bindRowClickEvents() {
    $('#tblItem').on('click', 'tr', function () {
        let code = $(this).find('td:eq(0)').text();
        let description = $(this).find('td:eq(1)').text();
        let qtyOnHand = $(this).find('td:eq(2)').text();
        let unitPrice = $(this).find('td:eq(3)').text();

        setTextFields(code, description, qtyOnHand, unitPrice);
    });
}

// set text fields
function setTextFields(code, description, qtyOnHand, unitPrice) {
    $('#itemCode').val(code);
    $('#itemName').val(description);
    $('#itemQty').val(qtyOnHand);
    $('#itemPrice').val(unitPrice);
}

$("#btnClear").click(function () {
    setTextFields("", "", "", "");
});

// add
$("#btnItem").click(function () {
    let formData = $("#itemForm").serialize();

    $.ajax({
        url: baseUrl + 'item',
        method: "POST",
        data: formData,

        success: function (res) {
            alert(res.message);
            getAllItems();
        },
        error: function (error) {
            alert(error.responseJSON.message);
        }
    });
});

// delete
$("#btnItemDelete").click(function () {
    let code = $('#itemCode').val();

    let b = confirm("Do you want to Delete " + code + " ?");

    if (b) {
        $.ajax({
            url: baseUrl + "item?code=" + code,
            method: "DELETE",

            success: function (res) {
                alert(res.message);
                getAllItems();
            },
            error: function (error) {
                alert(error.responseJSON.message);
            }
        });
    }
});

// update
$("#btnItemUpdate").click(function () {
    let code = $('#itemCode').val();
    let description = $('#itemName').val();
    let qtyOnHand = $('#itemQty').val();
    let unitPrice = $('#itemPrice').val();

    let item = {
        "code": code,
        "description": description,
        "qtyOnHand": qtyOnHand,
        "unitPrice": unitPrice
    }

    let b = confirm("Do you want to Update " + code + " ?");

    if (b) {
        $.ajax({
            url: baseUrl + "item",
            method: "PUT",
            contentType: "application/json",
            data: JSON.stringify(item),

            success: function (res) {
                alert(res.message);
                getAllItems();
            },
            error: function (error) {
                alert(error.responseJSON.message);
            }
        });
    }
});