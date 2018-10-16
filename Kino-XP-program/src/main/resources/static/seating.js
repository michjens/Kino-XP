$(document).ready(function () {

    var searchParams = new URLSearchParams(window.location.search);
    var sal = searchParams.get('sal');


    if (sal == 1) {
        for (var i = 1; i <= 25; i++){

            var rowToChar = String.fromCharCode(64 + i);
            var rowNumber = "<div class='rowNumber' id='rowNumber" + i + "'>"+i+"</div>";
            var row = "<div class='row' id='row" + i + "'></div>";
            $("#seatOverview").append(row);

            for(var j = 1; j <= 16; j++) {
                var seat = "<div class='seat1' id='seat" + j +"'>" + rowToChar + j + "</div>";
                $("#row" + i).append(seat);
            }
        }
    }

    if (sal == 2) {
        for (var i = 1; i<= 20; i++) {

            var rowToChar = String.fromCharCode(64 + i);
            var row = "<div class='row2' id='row2" + i + "'></div>";
            $("#seatOverview").append(row);

            for(var j = 1; j <= 12; j++) {

                var seat = "<div class='seat2' id='seat" + j +"'>" + rowToChar + j + "</div>";
                $("#row2" + i).append(seat);

            }
        }
    }

    $('.seat1').on('click', function () {
        $(this).toggleClass('active');

    })
    $('.seat2').on('click', function () {
        $(this).toggleClass('active');

    })
});