/**
 * Created by Renee
 */

function changeTheme(theme){
    document.getElementById(1).style.boxShadow = "";
    document.getElementById(1).style.mozBoxShadow = "";
    document.getElementById(2).style.boxShadow = "";
    document.getElementById(2).style.mozBoxShadow = "";
    document.getElementById(3).style.boxShadow = "";
    document.getElementById(3).style.mozBoxShadow = "";
    document.getElementById(theme).style.boxShadow = "0px 0px 12px 4px rgba(250,247,250,1)";
    document.getElementById(theme).style.mozBoxShadow = "0px 0px 12px 4px rgba(250,247,250,1)";
    document.getElementById('theme').value = theme;


}