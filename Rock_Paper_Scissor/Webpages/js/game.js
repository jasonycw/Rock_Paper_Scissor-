/**
 * Created by Renee
 */

var images = ["./img/RockPaperScissor/Rock-Paper-Scissors-Rock-icon.png", "./img/RockPaperScissor/Rock-Paper-Scissors-Paper-icon.png", "./img/RockPaperScissor/Rock-Paper-Scissors-Scissors-icon.png"];
var x = 0;

function changeImage() {
    var img = document.getElementById("img");
    img.src = images[x];

    x++;

    if (x >= images.length) {
        x = 0;
    }

    setTimeout("changeImage()", 100);
}