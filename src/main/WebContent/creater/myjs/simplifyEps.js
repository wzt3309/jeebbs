function createTableSlf(tbl, jsonObj) {
	if(!tbl.tagName.trim().toUpperCase() == "TABLE") {
		alert(resMsg['003']);
		return;
	}
	if(!isJson(jsonObj)) {
		alert(resMsg['003']);
		return;
	}
	
	var temp = null;
	var isEnum = false;
	var fieldName = "";
	var fieldDisp = "";
	var fieldType = "";
	var count = 0;
	var getTrs = null;
	var id = null;
	var row = null;
	var h1 = null;
	var m1 = null;
	var m1Str = "";
	var m2 = null;
	var m3 = null;
	var m4 = null;
	var m5 = null;
	var t1 = null;
	var t2 = null;
	
	for(var i = 0; i < jsonObj.length; i++) {
		if(jsonObj[i] == null || jsonObj[i] == 'undefined') {
			continue;
		}
		if(jsonObj[i].fieldname == null || jsonObj[i].fieldname.trim() == "") {
			continue;
		}
		fieldName = jsonObj[i].fieldname.trim();
		fieldType = jsonObj[i].fieldType.trim();
		fieldDisp = fieldName;
		if(jsonObj[i].fieldDisp != null && jsonObj[i].fieldDisp.trim() != "") {
			fieldDisp = jsonObj[i].fieldDisp.trim();
		}
		isEnum = false;
		count = 0;
		
		getTrs = tbl.getElementsByTagName("tr");
		id = getTrs.length;
		row = tbl.insertRow(id);
		row.id = "r" + id;
		h1 = row.insertCell(count++); 
		h1.setAttribute("width",'80px');
		h1.innerHTML= '<td><input name="objType" type="hidden" value="' + fieldType + '" /><input name="obj" type="hidden" value="' + fieldName + '" />' + fieldDisp + '</td>';
		
		temp = jsonObj[i].valueInfo;
		if(temp != null && temp != 'undefined' && temp.length > 0) {
			if(temp[0].valueKind != null && temp[0].valueKind == 0) {
				isEnum = true;
			}
		}
		
		if(isEnum) {
			m1 = row.insertCell(count++);
			m1Str = '<td>取值 <select name="value" style="width:100px">'
			for(var j = 0; j < temp.length; j++) {
				if(temp[j].valueKind != null && temp[j].valueKind == 0) {
					m1Str += '<option value="' + temp[j].valueScope.trim() + '">' + temp[j].valueScope.trim() + '</option>';
				}
			}
			m1Str += '</select>&nbsp;<input type="button" value="确定" onclick="addChoose(' + id + ')" /></td>';
			m1.innerHTML = m1Str;
			m2 = row.insertCell(count++);
        	m3 = row.insertCell(count++);
			m2.setAttribute("width",'70px'); 
			m3.setAttribute("width",'130px'); 
			m2.innerHTML = '<td>当前选择：<input name="valueReal" type="hidden" value="" /></td>';
			//m3.innerHTML = '<td><span id="valueDsp"></span></td>';
			m3.innerHTML = '<td><span></span></td>';
			
			m4 = row.insertCell(count++);
			m4.style.cssText="display:none";
        	m5 = row.insertCell(count++);
			m5.style.cssText="display:none";
			m4.setAttribute("width",'200px')
			m5.colSpan = 2;
			m4.innerHTML= '<td>下界<input name="lessthan" type="text" value="" style="width:150px" /></td>';
			m5.innerHTML= '<td>上界<input name="morethan" type="text" value="" style="width:150px" /></td>';
		}
		else {
			m1 = row.insertCell(count++);
			m1.style.cssText="display:none";
			m1Str = '<td>取值 <select name="value" style="width:100px"></select></td>';
			m1.innerHTML = m1Str;
			m2 = row.insertCell(count++);
        	m3 = row.insertCell(count++);
			m2.style.cssText="display:none";
			m3.style.cssText="display:none";
			m2.setAttribute("width",'70px'); 
			m3.setAttribute("width",'130px'); 
			m2.innerHTML = '<td>当前选择：<input name="valueReal" type="hidden" value="" /></td>';
			//m3.innerHTML = '<td><span id="valueDsp"></span></td>';
			m3.innerHTML = '<td><span></span></td>';
			
			m4 = row.insertCell(count++);
        	m5 = row.insertCell(count++);
			m4.setAttribute("width",'200px');
			m5.colSpan = 2;
			m4.innerHTML= '<td>下界<input name="lessthan" type="text" value="" style="width:150px" /></td>';
			m5.innerHTML= '<td>上界<input name="morethan" type="text" value="" style="width:150px" /></td>';
		}
		t1 = row.insertCell(count++);
        t2 = row.insertCell(count++);
		t1.setAttribute("width",'60px'); 
		t1.innerHTML= '<input type="button" value="清空" onclick="resetCon(' + id + ')" /></td>';
		t2.innerHTML= '<td><input type="hidden" name="isEnum" value="' + isEnum + '" />&nbsp;<br/><br/><br/></td>';
	}
}

function createsimplifyArrayCon() {
	var arrayCon = [];
	var tempCon = null;
	var objs = document.getElementsByName("obj");
	var objTypes = document.getElementsByName("objType");
	var isEnums = document.getElementsByName("isEnum");
	var valueReals = document.getElementsByName("valueReal");
	var values = null;
	var lessthans = document.getElementsByName("lessthan");
	var morethans = document.getElementsByName("morethan");
	var count = 0;
	var i = 0;
	var j = 0;
	
	for(i = 0; i < objs.length; i++) {
		if(objs[i] != null && objs[i].value.trim() != "") {
			tempCon = "";
			if(isEnums[i].value.trim() == "true") {
				if(valueReals[i] != null && valueReals[i].value.trim() != "") {
					values = valueReals[i].value.trim().split(",");
					for(j = 0; j < values.length; j++) {
						if(values[j] != null && values[j].trim() != "") {
							tempCon += values[j].trim();
							if(j != values.length - 1) {
								tempCon += "@**@";
							}
						}
					}
					if(tempCon != null && tempCon.trim() != "") {
						arrayCon[count++] = objs[i].value.trim() + ",=," + tempCon + ",AND,false";
					}
				}
			}
			else if(isEnums[i].value.trim() == "false") {
				if(objTypes[i].value.trim().toUpperCase() == "STRING") {
					if(lessthans[i] != null && 
						lessthans[i].value.trim() != "" && 
						(morethans[i] == null || morethans[i].value.trim() == "")) {
						arrayCon[count++] = objs[i].value.trim() + ",=," + lessthans[i].value.trim() + ",AND,false";
					}
					else if(morethans[i] != null && 
						morethans[i].value.trim() != "" && 
						(lessthans[i] == null || lessthans[i].value.trim() == "")) {
						arrayCon[count++] = objs[i].value.trim() + ",=," + morethans[i].value.trim() + ",AND,false";
					}
					else if(lessthans[i] != null && 
						lessthans[i].value.trim() != "" && 
						morethans[i] != null && 
						morethans[i].value.trim() != "") {
						arrayCon[count++] = objs[i].value.trim() + ",=," + lessthans[i].value.trim() + "@**@" + morethans[i].value.trim() + ",AND,false";
					}
				}
				else {
					if(lessthans[i] != null && lessthans[i].value.trim() != "" && 
						(morethans[i] == null || morethans[i].value.trim() == "")) {
						arrayCon[count++] = objs[i].value.trim() + ",<=," + lessthans[i].value.trim() + ",AND,false";
					}
					else if(morethans[i] != null && morethans[i].value.trim() != "" && 
						(lessthans[i] == null || lessthans[i].value.trim() == "")) {
						arrayCon[count++] = objs[i].value.trim() + ",>=," + morethans[i].value.trim() + ",AND,false";
					}
					else if(lessthans[i] != null && lessthans[i].value.trim() != "" && 
						morethans[i] != null && morethans[i].value.trim() != "") {
						arrayCon[count++] = objs[i].value.trim() + ",>=," + morethans[i].value.trim() + ",<=," + lessthans[i].value.trim() + ",AND,false";
					}
				}
			}
		}
	}
	
	return arrayCon;
}