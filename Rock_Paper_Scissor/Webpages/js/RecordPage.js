/**
 * Created by Jason on 19/7/2014.
 */
var personalStateButton;
var personalRankContainer;
window.onload = function () {
    // Set the personal state functions
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

    document.getElementById("personalRankTable").onclick = function(e){
        e.stopPropagation();
    }

    // Set the rank table functions
    document.getElementById("ratioTag").style.opacity = 1;
    document.getElementById("totalGamesTag").style.opacity = 0.4;
    document.getElementById("totalWinTag").style.opacity = 0.4;
    document.getElementById("totalLoseTag").style.opacity = 0.4;
    document.getElementById("gameTimeTag").style.opacity = 0.4;
    document.getElementById("ratioTag").onclick = function () {
        document.getElementById("allTableContainer").style.marginLeft = "0px";
        document.getElementById("ratioTag").style.opacity = 1;
        document.getElementById("totalGamesTag").style.opacity = 0.4;
        document.getElementById("totalWinTag").style.opacity = 0.4;
        document.getElementById("totalLoseTag").style.opacity = 0.4;
        document.getElementById("gameTimeTag").style.opacity = 0.4;
    }
    document.getElementById("totalGamesTag").onclick = function () {
        document.getElementById("allTableContainer").style.marginLeft = "-700px";
        document.getElementById("ratioTag").style.opacity = 0.4;
        document.getElementById("totalGamesTag").style.opacity = 1;
        document.getElementById("totalWinTag").style.opacity = 0.4;
        document.getElementById("totalLoseTag").style.opacity = 0.4;
        document.getElementById("gameTimeTag").style.opacity = 0.4;
    }
    document.getElementById("totalWinTag").onclick = function () {
        document.getElementById("allTableContainer").style.marginLeft = "-1400px";
        document.getElementById("ratioTag").style.opacity = 0.4;
        document.getElementById("totalGamesTag").style.opacity = 0.4;
        document.getElementById("totalWinTag").style.opacity = 1;
        document.getElementById("totalLoseTag").style.opacity = 0.4;
        document.getElementById("gameTimeTag").style.opacity = 0.4;
    }
    document.getElementById("totalLoseTag").onclick = function () {
        document.getElementById("allTableContainer").style.marginLeft = "-2100px";
        document.getElementById("ratioTag").style.opacity = 0.4;
        document.getElementById("totalGamesTag").style.opacity = 0.4;
        document.getElementById("totalWinTag").style.opacity = 0.4;
        document.getElementById("totalLoseTag").style.opacity = 1;
        document.getElementById("gameTimeTag").style.opacity = 0.4;
    }
    document.getElementById("gameTimeTag").onclick = function () {
        document.getElementById("allTableContainer").style.marginLeft = "-2800px";
        document.getElementById("ratioTag").style.opacity = 0.4;
        document.getElementById("totalGamesTag").style.opacity = 0.4;
        document.getElementById("totalWinTag").style.opacity = 0.4;
        document.getElementById("totalLoseTag").style.opacity = 0.4;
        document.getElementById("gameTimeTag").style.opacity = 1;
    }
}