/**
 * Created by Jason on 19/7/2014.
 */
var personalStateButton;
var personalRankContainer;
window.onload = function () {
    personalStateButton = document.getElementById("personalStateButton");
    personalRankContainer = document.getElementById("personalRankContainer");
    personalStateButton.onmouseover = function(){
        personalStateButton
    }
    personalStateButton.onclick = function () {
        personalRankContainer.style.opacity = 1;
        personalRankContainer.style.marginTop = 0;
    }

    personalRankContainer.onclick = function(){
        personalRankContainer.style.marginTop = "-100%";
    }

    document.getElementById("personalRankTable").onclick= function(){
        personalRankContainer.style.marginTop = 0;
    }
}