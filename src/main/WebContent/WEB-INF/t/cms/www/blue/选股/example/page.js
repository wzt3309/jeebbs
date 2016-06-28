var preIcon = document.getElementById("pan_pre_icon");
var nextIcon = document.getElementById("pan_next_icon");
var panConfig = document.getElementById("pan_config");
var lis = new Array();
for(var n = 0; ; n++){
  var tempLi  = document.getElementById('pan_li' + n);
  if(tempLi){
      lis.push(tempLi);
  }else{
      break;
  }
}
var iconNumber = lis.length;
var setWid = 31;
var logoWid = 80;
var nextWid = 29;
var liWidth = 200;
var realWidth = getRealStyle(lis[0],"width");
realWidth = realWidth.substring(0,realWidth.length - 2);
var imgs = new Array();
var availableWid = clientWid - setWid - logoWid - nextWid - nextWid;
if(realWidth*iconNumber -30 > availableWid){
	    nextIcon.onclick = nextClick;
	    nextIcon.className = "rec-page-btn rec-next";
}



function showConfig(){
    if(panConfig.style.display === "none"){
       panConfig.style.display = "block";
    }else if(panConfig.style.display === "block"){
        panConfig.display = "none";
    }
}
function countNone(){
	var noneCount = 0;
	for(var i = 0; i < iconNumber; i ++){
		if(lis[i].style.display === "none"){
			noneCount ++;
		}
	}
	return noneCount;
}
//var lastLi = document.getElementById("pan_li" + (iconNumber - 1));//判断是否最后一个了
function preClick(){
  for(var n = 0 ; n < iconNumber; n ++){
      if(lis[n].style.display === "none" && lis[n + 1].style.display === "block"){
          if(n === 0){
             // preIcon.style.backgroundPosition = "-70px";
              preIcon.className = "rec-page-btn rec-prev rec-disable";
              preIcon.onclick = null;
          }
          lis[n].style.display = "block";
          clientWid = document.body.clientWidth;
          availableWid = clientWid - setWid - logoWid - nextWid - nextWid;
          if((iconNumber - countNone()) * realWidth > availableWid){
              //nextIcon.style.backgroundPosition = "10px";
              nextIcon.className = "rec-page-btn rec-next";
              nextIcon.onclick = nextClick;
          }
          break;
      }
  }
}
function nextClick(){
 // preIcon.style.backgroundPosition = "10px";
  preIcon.className = "rec-page-btn rec-prev";
  preIcon.onclick = preClick;
  for(var n = 0 ; n < iconNumber; n ++){

      if(n == 0 && lis[n].style.display === "block"){
          //preIcon.backgroundPosition = "-70px";
          //preIcon.className = "rec-page-btn rec-prev-img rec-prev1";
          //preIcon.onclick = null;
          preIcon.className = "rec-page-btn rec-prev";
          preIcon.onclick = preClick;
          lis[n].style.display = "none";

      }else{
          if(lis[n].style.display === "none" && lis[n + 1].style.display === "block"){
              lis[n + 1].style.display = "none";
              break;
          }
      }
  }
  clientWid = document.body.clientWidth;
  availableWid = clientWid - setWid - logoWid - nextWid - nextWid;
  if((iconNumber - countNone()) * realWidth - 30 < availableWid){
      //nextIcon.style.backgroundPosition = "-70px";
      nextIcon.className = "rec-page-btn rec-next rec-disable";
      nextIcon.onclick = null;
      return null;
      
  }
  
  

}