$(document).ready(function () {


    var sal;

    //if (sal == 1) {
        for (var i= 1; i <= 25; i++){

            var row = "<div class='row' id='row" + i + "'></div>";
            $("#seatOverview").append(row);

            for(var j = 1; j <= 16; j++) {
                var seat = "<div class='seat' id='seat" + j +"'></div>";
                $("#row" + i).append(seat);
            }
        }
    //}

    if (sal == 2) {
        for (var i = 1; i<= 20; i++) {

            for(var j = 1; j <= 12; j++) {


            }
        }
    }

    $('.seat').on('click', function () {
        $(this).toggleClass('active');

    })
});