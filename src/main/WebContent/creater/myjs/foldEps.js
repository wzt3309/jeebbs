function createSelect(obj, jsonObj) {
	if(!obj.type.trim().toLowerCase().startsWith("select")) {
		alert(resMsg['003']);
		return;
	}
	if(!isJson(jsonObj)) {
		alert(resMsg['003']);
		return;
	}
	obj.options.length = 0;
	var count = 0;
	var fieldName = "";
	var fieldDisp = "";
	for(var i = 0; i < jsonObj.length; i++) {
		if(jsonObj[i].fieldname != null && jsonObj[i].fieldname.trim() != "") {
			fieldName = jsonObj[i].fieldname.trim();
			fieldDisp = fieldName;
			if(jsonObj[i].fieldDisp != null && jsonObj[i].fieldDisp.trim() != "") {
				fieldDisp = jsonObj[i].fieldDisp.trim();
			}
			obj.options[count++] = new Option(fieldDisp, fieldName);
		}
	}
	if(count == 0) {
		alert(resMsg['013']);
		return false;
	}
	
	return true;
}

function createSeled(seled, seledReal, resDis, resReal, jsonObj) {
	if(!seled.type.trim().toLowerCase().startsWith("select")) {
		alert(resMsg['005']);
		return -3;
	}
	if(!seledReal.type.trim().toLowerCase().startsWith("select")) {
		alert(resMsg['005']);
		return -3;
	}
	if((resDis == null || resDis == 'undefined' || resDis.trim() == "") || 
		(resReal == null || resReal == 'undefined' || resReal.trim() == "")) {
		alert(resMsg['005']);
		return -3;
	}
	
	var oldCon = null;
	var oldCons = null;
	
	var newCon = "";
	var newCons = resReal.trim().split(",");
	for(var y = 0; y < newCons.length - 2; y++) {
		newCon += newCons[y] + ",";
	}
	newCon += newCons[y];
	
	for(var i = 0; i < seledReal.options.length; i++){
		oldCon = "";
		oldCons = seledReal.options[i].text.trim().split(",");
		for(var x = 0; x < oldCons.length - 2; x++) {
			oldCon += oldCons[x] + ",";
		}
		oldCon += oldCons[x];
		
		if(newCon.trim() == oldCon.trim()){
			alert(resMsg['011']);
			return 0;
       	}   
		
    	//if(resReal.trim() == seledReal.options[i].text.trim()){
		//	alert(resMsg['011']);
		//	return;
       	//}    
    }
	
	//var isValid = checkRecValid(resReal, jsonObj);
	//if(isValid > 0) {
		var itemDis = new Option(resDis, "1");
    	itemDis.setAttribute("title",resDis);
    	seled.options.add(itemDis);
	
		var itemReal = new Option(resReal, "1");
    	itemReal.setAttribute("title",resReal);
    	seledReal.options.add(itemReal);
	//}
	return 1;//isValid;
}

function removeSelItem(seled, seledReal) {
	if(!seled.type.trim().toLowerCase().startsWith("select")) {
		alert(resMsg['008']);
		return;
	}
	if(!seledReal.type.trim().toLowerCase().startsWith("select")) {
		alert(resMsg['008']);
		return;
	}
	
	var length = seled.options.length - 1; 
	var value = null;
	var notHas = true;
	try {
    	for(var i = length; i >= 0; i--){  
    		if(seled[i].selected == true){
				notHas = false;
				//value = seled.options[i].value.trim();
            	seled.options[i] = null; 
				seledReal.options[i] = null;
       	 	}    
    	}
		if(notHas) {
			alert(resMsg['009']);
			return;
		}
		//else {
		//	alert(resMsg['010']);
		//}
	} catch(e) {
		alert(resMsg['008']);
		return;
	}
}

function createValue(selObjValue, valueObj, jsonObj) {
	var selValue = null;
	var inpValue = null;
	for(var x = 0; x < valueObj.length; x++) {
		valueObj[x].style.display = "none";
		if(valueObj[x].type.trim().toLowerCase().startsWith("select")) {
			selValue = valueObj[x];
			selValue.options.length = 0;
			selValue.style.display = "none";
		}
		else {
			inpValue = valueObj[x];
			inpValue.value = "";
			inpValue.style.display = "";
		}
	}
	var temp = null;
	var count = 0;
	for(var i = 0; i < jsonObj.length; i++) {
		if(jsonObj[i].fieldname != null && jsonObj[i].fieldname.trim() != "") {
			if(selObjValue == jsonObj[i].fieldname.trim()) {
				temp = jsonObj[i].valueInfo;
				if(temp != null && temp != 'undefined' && temp.length > 0) {
					if(temp[0].valueKind != null && temp[0].valueKind == 0) {
						for(var j = 0; j < temp.length; j++) {
							if(temp[j].valueKind != null && temp[j].valueKind == 0) {
								selValue.options[count++] = new Option(temp[j].valueScope, temp[j].valueScope);
							}
						}
						if(selValue != null) {
							selValue.style.display = "";
						}
						if(inpValue != null) {
							inpValue.style.display = "none";
						}
					}
				}
				break;
			}
		}
	}
	
	if(selValue.style.display == "" && count == 0) {
		alert(resMsg['014']);
		return false;
	}
	
	return true;
}

function createfoldArrayCon(seledReal) {
	alert("q")
	var arrayCon = [];
	alert(seledReal.options.length);
	for(var i = 0; i < seledReal.options.length; i++){
		arrayCon[i] = seledReal.options[i].text.trim() + ",false";
    }
	return arrayCon;
}