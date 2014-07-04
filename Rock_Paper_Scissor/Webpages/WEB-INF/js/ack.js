function ackError(dom, text){
	dom.innerHTML+='<div class="alert-box error"><span>error: </span>'+text+'</div>';
}
function ackSuccess(dom, text){
	dom.innerHTML+='<div class="alert-box success"><span>success: </span>'+text+'</div>';

}
function ackWarning(dom, text){
	dom.innerHTML+='<div class="alert-box warning"><span>warning: </span>'+text+'</div>';

}
function ackNotice(dom, text){
	dom.innerHTML+='<div class="alert-box notice"><span>notice: </span>'+text+'</div>';
}		
