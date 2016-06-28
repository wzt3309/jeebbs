function createTableSpr(tbl, jsonObj) {
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
	var h2 = null;
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
		h2 = row.insertCell(count++);
		h1.setAttribute("width",'80px'); 
		h2.setAttribute("width",'8%'); 
		h1.innerHTML= '<td><input name="objType" type="hidden" value="' + fieldType + '" /><input name="obj" type="hidden" value="' + fieldName + '" />' + fieldDisp + '</td>';
		h2.innerHTML= '<td><input name="isNot" type="checkbox" />NOT</td>';
		
		temp = jsonObj[i].valueInfo;
		if(temp != null && temp != 'undefined' && temp.length > 0) {
			if(temp[0].valueKind != null && temp[0].valueKind == 0) {
				isEnum = true;
			}
		}
		
		if(isEnum) {
			m1 = row.insertCell(count++);
			m1.setAttribute("width",'180px'); 
			m1Str = '<td>取值 <select name="value" style="width:80px">'
			for(var j = 0; j < temp.length; j++) {
				if(temp[j].valueKind != null && temp[j].valueKind == 0) {
					m1Str += '<option value="' + temp[j].valueScope.trim() + '">' + temp[j].valueScope.trim() + '</option>';
				}
			}
			m1Str += '</select>&nbsp;<input type="button" value="确定" onclick="addChoose(' + id + ')" /></td>';
			m1.innerHTML = m1Str;
			m2 = row.insertCell(count++);
        	m3 = row.insertCell(count++);
			m2.setAttribute("width",'80px'); 
			m3.setAttribute("width",'100px'); 
			m2.innerHTML = '<td>当前选择：<input name="valueReal" type="hidden" value="" /></td>';
			//m3.innerHTML = '<td><span id="valueDsp"></span></td>';
			m3.innerHTML = '<td><span></span></td>';
			
			m4 = row.insertCell(count++);
			m4.setAttribute("width",'180px'); 
			m4.style.cssText="display:none";
        	m5 = row.insertCell(count++);
			m5.style.cssText="display:none";
			m5.colSpan = 2;
			m4.innerHTML= '<td><input name="inclt" type="checkbox" />不含 下界<input name="lessthan" type="text" value="" style="width:80px" /></td>';
			m5.innerHTML= '<td><input name="incmt" type="checkbox" />不含 上界<input name="morethan" type="text" value="" style="width:80px" /></td>';
		}
		else {
			m1 = row.insertCell(count++);
			m1.setAttribute("width",'180px'); 
			m1.style.cssText="display:none";
			m1Str = '<td>取值 <select name="value" style="width:130px"></select></td>';
			m1.innerHTML = m1Str;
			m2 = row.insertCell(count++);
        	m3 = row.insertCell(count++);
			m2.style.cssText="display:none";
			m3.style.cssText="display:none";
			m2.setAttribute("width",'70px'); 
			m3.setAttribute("width",'100px'); 
			m2.innerHTML = '<td>当前选择：<input name="valueReal" type="hidden" value="" /></td>';
			//m3.innerHTML = '<td><span id="valueDsp"></span></td>';
			m3.innerHTML = '<td><span></span></td>';
			
			m4 = row.insertCell(count++);
			m4.setAttribute("width",'180px'); 
        	m5 = row.insertCell(count++);
			m5.colSpan = 2;
			m4.innerHTML= '<td><input name="inclt" type="checkbox" />不含 下界<input name="lessthan" type="text" value="" style="width:80px" /></td>';
			m5.innerHTML= '<td><input name="incmt" type="checkbox" />不含 上界<input name="morethan" type="text" value="" style="width:80px" /></td>';
		}
		t1 = row.insertCell(count++);
        t2 = row.insertCell(count++);
		t2.setAttribute("width",'1px'); 
		t1.innerHTML= '<td>与下条关系<select name="nextrlt"><option value="AND">并且</option><option value="OR">或者</option></select>&nbsp;<input type="button" value="清空" onclick="resetCon(' + id + ')" /></td>';
		t2.innerHTML= '<td><input type="hidden" name="isEnum" value="' + isEnum + '" />&nbsp;<br/><br/><br/></td>';
	}
}

function createSpreadArrayCon() {
	var arrayCon = [];
	var tempCon = null;
	var isNots = document.getElementsByName("isNot");
	var objs = document.getElementsByName("obj");
	var objTypes = document.getElementsByName("objType");
	var isEnums = document.getElementsByName("isEnum");
	var valueReals = document.getElementsByName("valueReal");
	var values = null;
	var inclts = document.getElementsByName("inclt");
	var lessthans = document.getElementsByName("lessthan");
	var incmts = document.getElementsByName("incmt");
	var morethans = document.getElementsByName("morethan");
	var nextrlts = document.getElementsByName("nextrlt");
	var count = 0;
	var i = 0;
	var j = 0;
	var syb1 = null;
	var syb2 = null;
	
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
						arrayCon[count++] = objs[i].value.trim() + ",=," + tempCon + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();
					}
				}
			}
			else if(isEnums[i].value.trim() == "false") {
				if(objTypes[i].value.trim().toUpperCase() == "STRING") {
					if(lessthans[i] != null && 
						lessthans[i].value.trim() != "" && 
						(morethans[i] == null || morethans[i].value.trim() == "")) {
						//if(inclts[i] != null && inclts[i].checked == true) {
						//}
						arrayCon[count++] = objs[i].value.trim() + ",=," + lessthans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();;
					}
					else if(morethans[i] != null && 
						morethans[i].value.trim() != "" && 
						(lessthans[i] == null || lessthans[i].value.trim() == "")) {
						//if(incmts[i] != null && incmts[i].checked == true) {
						//}
						arrayCon[count++] = objs[i].value.trim() + ",=," + morethans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();;
					}
					else if(lessthans[i] != null && 
						lessthans[i].value.trim() != "" && 
						morethans[i] != null && 
						morethans[i].value.trim() != "") {
						//if(inclts[i] != null && inclts[i].checked == true) {
						//}
						//if(incmts[i] != null && incmts[i].checked == true) {
						//}
						arrayCon[count++] = objs[i].value.trim() + ",=," + lessthans[i].value.trim() + "@**@" + morethans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();;
					}
				}
				else {
					if(lessthans[i] != null && lessthans[i].value.trim() != "" && 
						(morethans[i] == null || morethans[i].value.trim() == "")) {
						syb1 = "<=";
						if(inclts[i] != null && inclts[i].checked == true) {
							syb1 = "<";
						}
						arrayCon[count++] = objs[i].value.trim() + "," + syb1 + "," + lessthans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();;
					}
					else if(morethans[i] != null && morethans[i].value.trim() != "" && 
						(lessthans[i] == null || lessthans[i].value.trim() == "")) {
						syb2 = ">=";
						if(incmts[i] != null && incmts[i].checked == true) {
							syb2 = ">";
						}
						arrayCon[count++] = objs[i].value.trim() + "," + syb2 + "," + morethans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();;
					}
					else if(lessthans[i] != null && lessthans[i].value.trim() != "" && 
						morethans[i] != null && morethans[i].value.trim() != "") {
						syb1 = "<=";
						syb2 = ">=";
						if(inclts[i] != null && inclts[i].checked == true) {
							syb1 = "<";
						}
						if(incmts[i] != null && incmts[i].checked == true) {
							syb2 = ">";
						}
						arrayCon[count++] = objs[i].value.trim() + "," + syb2 + "," + morethans[i].value.trim() + "," + syb1 + "," + lessthans[i].value.trim() + "," + nextrlts[i].value.trim() + "," + isNots[i].checked.toString();
					}
				}
			}
		}
	}
	
	return arrayCon;
}