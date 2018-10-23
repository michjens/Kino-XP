$(document).ready(function () {
    $("#add").on('click', function () {
        var total = 0;

        var price = $("#slikDDL").val();
        var amount = $("#amount").val();

        total = price * amount;

        $("#total").text("Total: " + total);
    })
})