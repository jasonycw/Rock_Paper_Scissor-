/**
 * Created by Jason on 11/7/2014.
 */

window.onload = function () {
    document.getElementById("stats-round-button").onmouseover = function () {
        document.getElementById("nameTags").style.marginLeft = "0px";
        document.getElementById("statsNameTag").style.opacity = "1";
        document.getElementById("playNameTag").style.opacity = "0";
        document.getElementById("rankNameTag").style.opacity = "0";
        document.getElementById("logoutNameTag").style.opacity = "0";
    }
    document.getElementById("play-round-button").onmouseover = function () {
        document.getElementById("nameTags").style.marginLeft = "-300px";
        document.getElementById("statsNameTag").style.opacity = "0";
        document.getElementById("playNameTag").style.opacity = "1";
        document.getElementById("rankNameTag").style.opacity = "0";
        document.getElementById("logoutNameTag").style.opacity = "0";
    }
    document.getElementById("rank-round-button").onmouseover = function () {
        document.getElementById("nameTags").style.marginLeft = "-600px";
        document.getElementById("statsNameTag").style.opacity = "0";
        document.getElementById("playNameTag").style.opacity = "0";
        document.getElementById("rankNameTag").style.opacity = "1";
        document.getElementById("logoutNameTag").style.opacity = "0";
    }
    document.getElementById("logout-round-button").onmouseover = function () {
        document.getElementById("nameTags").style.marginLeft = "-900px";
        document.getElementById("statsNameTag").style.opacity = "0";
        document.getElementById("playNameTag").style.opacity = "0";
        document.getElementById("rankNameTag").style.opacity = "0";
        document.getElementById("logoutNameTag").style.opacity = "1";
    }
    document.getElementById("mainContainer").onmouseover = function () {
        document.getElementById("nameTags").style.marginTop = "20px";
        document.getElementById("nameTags").style.opacity = "0.85";
    }
    document.getElementById("mainContainer").onmouseout = function () {
        document.getElementById("nameTags").style.marginLeft = "-450px";
        document.getElementById("nameTags").style.marginTop = "-100px";
        document.getElementById("nameTags").style.opacity = "0";
        document.getElementById("statsNameTag").style.opacity = "0";
        document.getElementById("playNameTag").style.opacity = "0";
        document.getElementById("rankNameTag").style.opacity = "0";
        document.getElementById("logoutNameTag").style.opacity = "0";
    }
}