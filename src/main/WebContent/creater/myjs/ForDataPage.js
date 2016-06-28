 function flodEpsinit() {
    	var resJsonStr = parent.document.getElementById("resJsonStr").value;
    	jsonObj = str2json(resJsonStr);
    	if(checkSandV(jsonObj)) {
    		var obj = document.getElementById("obj");
    		var res = createSelect(obj, jsonObj);
    		if(res) {
    			obj.fireEvent('onchange');
    		}
    	}
    	else {
    		alert(resMsg['003']);
    	}
    }

    function selectObj(obj) {
    	var selObjValue = obj.options[obj.selectedIndex].value;
    	var valueObj = document.getElementsByName("value");
    	createValue(selObjValue, valueObj, jsonObj);
    }

    function addSel() {
    	var res = "";
    	
    	var valueObj = document.getElementsByName("value");
    	var value = null;
    	if(valueObj != null && valueObj != 'undefined') {
    		for(var i = 0; i < valueObj.length; i++) {
    			if(valueObj[i].style.display != "none") {
    				if(valueObj[i].type.trim().startsWith("select")) {
    					value = valueObj[i].options[valueObj[i].selectedIndex].value.trim();
    				}
    				else {
    					value = valueObj[i].value.trim();
    					if(value == "") {
    						alert(resMsg['004']);
    						return;
    					}
    				}
    			}
    		}
    	}

    	var obj = document.getElementById("obj");
    	var objValue = null;
    	var objText = null;
    	if(obj != null && obj != 'undefined') {
    		objValue = obj.options[obj.selectedIndex].id.trim();
    		objText = obj.options[obj.selectedIndex].text.trim();
    	}
    	
    	var rlt = document.getElementById("rlt");
    	var rltValue = null;
    	if(rlt != null && rlt != 'undefined') {
    		rltValue = rlt.options[rlt.selectedIndex].value.trim();
    	}
    	
    	var nextrlt = document.getElementById("nextrlt");
    	var nextrltValue = null;
    	var nextrltText = null;
    	if(nextrlt != null && nextrlt != 'undefined') {
    		nextrltValue = nextrlt.options[nextrlt.selectedIndex].value.trim();
    		nextrltText = nextrlt.options[nextrlt.selectedIndex].text.trim();
    	}
    	var resDis = null;
    	var resReal = null;
    	if(!((objValue != null && objValue.trim() != "") &&
    		(objText != null && objText.trim() != "") && 
    	   	(rltValue != null && rltValue.trim() != "") && 
    	   	(value != null && value.trim() != "") && 
    	   	(nextrltValue != null && nextrltValue.trim() != "") && 
    		(nextrltText != null && nextrltText.trim() != ""))) {
    		alert(resMsg['005'] + resMsg['007']);
    		return;
    	}
    	resDis = objText.trim() + rltValue.trim() +  value.trim() + " " + nextrltText.trim();
    	resReal = objValue.trim() +" "+ rltValue.trim() +" "+ value.trim() +" "+ nextrltValue.trim();
    	
    	var seled = document.getElementById("seled");
    	var seledReal = document.getElementById("seledReal");
    	if(seled != null && seledReal != null) {
    		createSeled(seled, seledReal, resDis, resReal, jsonObj);
    	}
    	else {
    		alert(resMsg['005'] + resMsg['006']);
    		return;
    	}
    }

    function resetCon() {
    	document.getElementById("obj").selectedIndex = 0;
    	obj.fireEvent('onchange');g
    	document.getElementById("rlt").selectedIndex = 0;
    	document.getElementById("nextrlt").selectedIndex = 0;
    }

    function rmvSel() {
    	var seled = document.getElementById("seled");
    	var seledReal = document.getElementById("seledReal");
    	//if(seled.length == 0) {
    	if(seled.options.length == 0) {
    		alert(resMsg['012']);
    		return;
    	}
    	removeSelItem(seled, seledReal);
    }

    function clearSelAll() {
    	if(seled.options.length == 0) {
    		alert(resMsg['012']);
    		//document.getElementById("count").value = '0';
    		return;
    	}
    	if(confirm("确定清空所有条件项？")) {
    		document.getElementById("seled").options.length = 0;
    		document.getElementById("seledReal").options.length = 0;
    		//document.getElementById("count").value = '0';
    	}
    }

    function createEps() {
    	var seledReal = document.getElementById("seledReal");
    	if(seledReal.options.length == 0) {
    		alert(resMsg['012']);
    		return;
    	}
    	var arrayCon = createfoldArrayCon(seledReal);
    	if(arrayCon.length == 0) {
    		alert(resMsg['015']);
    		return;
    	}
    	var showRes = document.getElementById("showRes");
    	var res = createEpsRes(arrayCon, showRes, jsonObj);
    	//if(showRes.innerHTML != null && showRes.innerHTML.trim() != "") {
    	if(res > 0) {
    		parent.document.getElementById("lastRes").value = showRes.innerHTML.trim().replaceAll("&gt;", ">").replaceAll("&lt;", "<");
    	}
    }

    function cancel() {
    	parent.dialog1.hide();
    }
    function setreturn(){
    	
    var seledReal=document.getElementById("seledReal");
    if(seledReal.options.length>0){
    if(!seledReal.options[seledReal.options.length-1].text.endsWith("1=1")){
    	alert("请定义最后条件");
    	return;
    }
    var i;
    var retvalue="";
    
    for(i=0;i<seledReal.options.length;i++){
    	retvalue+=seledReal.options[i].text.trim()+";";	
    }
    
    document.getElementById("return").value=retvalue;
    inputreturn=document.getElementById("return");
    }
    
    if(document.getElementById("tb_12").className=="current"){document.getElementById("show").value="submit";}
    
    document.getElementById("submit").click();
    	//alert(document.getElementsByName("return").value);

    	//document.getElementsByName("return").innerText=returnvalue;
    }
    
    var judge=0;
    
    function showchose(){

    	if(document.getElementById("tb_12").className!="current"){
    	document.getElementById("tb_12").className="current";
    	document.getElementById("creater1").style.display="";
    	
    	document.getElementById("creater2").style.display="";
    	judge=1;
    	document.getElementById("show").value="submit";
    	}
    	else{
    		document.getElementById("tb_12").className=" ";
        	document.getElementById("creater1").style.display="none";
        	
        	document.getElementById("creater2").style.display="none";
        	judge=0;
        	document.getElementById("show").value=null;
    	}
    	
    }
    
   function seledstock(id)
   {

   
	   document.getElementById("gpdm").value=id;
   }
   
   function dbseledstock(id){
	   seledstock(id);
	   setreturn();
   }