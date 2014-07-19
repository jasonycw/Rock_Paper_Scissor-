/**
 * Created by Renee
 */

function changeTheme(theme) {
    for (var i = 1; i < 4; i++) {

        if(document.getElementById(i.toString()).className.indexOf("selected") != -1){
            if (i != theme){
                document.getElementById(i.toString()).className = "image background" + i;
            }
        }else{
            if (i == theme){
                document.getElementById(i.toString()).className +=  " selected";
            }


        }

    }
    //document.getElementById(theme).style.boxShadow = "0px 0px 12px 4px rgba(250,247,250,1)";
    //document.getElementById(theme).style.mozBoxShadow = "0px 0px 12px 4px rgba(250,247,250,1)";
    document.getElementById("theme").value = theme;


}