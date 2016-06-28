function AS2RJStr(recStruct, recValue) {
	if(recStruct.trim() == "") {
		recStruct = "[]";
		alert(resMsg['001']);
		return;
	}
	if(recValue.trim() == "") {
		recValue = "[]";
	}
	try {
		var recStructObj = str2json(recStruct);
		var recValueObj = str2json(recValue);
		var resStr = "[";
		var tempAll = "";
		var itemTemp = "";
		var temp = "";
		for(var i = 0; i < recStructObj.length; i++){
			temp = "";
			tempAll = "";
			
			itemTemp = json2str(recStructObj[i]);
			tempAll = itemTemp.substring(0, itemTemp.length - 1) + ",'valueInfo':[";
			
			for(var j = 0; j < recValueObj.length; j++){
				if(recStructObj[i].fieldname.trim() == recValueObj[j].fieldname.trim()) {
					temp += json2str(recValueObj[j]) + ",";
				}
			}
			if(temp.trim() != "") {
				temp = temp.substring(0, temp.length - 1);
			}
			tempAll += temp + "]},";
			resStr += tempAll;
		}
		resStr = resStr.substring(0, resStr.length - 1);
		resStr += "]";
		
		return resStr;
		//return str2json(resStr);
	}catch(e){
		alert(resMsg['002']);
	}
}

function checkSandV(jsonObj) {
	if(!isJson(jsonObj)) {
		alert(resMsg['002']);
		return false;
	}
	var count1 = 0;
	var count2 = 0;
	var tt = null;
	var fDsp = null;
	var sizeNum = null;
	for(var i = 0; i < jsonObj.length; i++) {
		if(jsonObj[i].fieldname != null && jsonObj[i].fieldname.trim() != "" && 
			jsonObj[i].fieldType != null && jsonObj[i].fieldType.trim() != "") {
			count1++;
			temp = jsonObj[i].valueInfo;
			fDsp = jsonObj[i].fieldname.trim();
			if(jsonObj[i].fieldDisp != null && jsonObj[i].fieldDisp.trim() != "") {
				fDsp = jsonObj[i].fieldDisp.trim();
			}
			if(temp != null && temp != 'undefined' && temp.length > 0) {
				if(temp[0].valueKind != null && temp[0].valueKind == 0) {
					count2 = 0;
					for(var j = 0; j < temp.length; j++) {
						if(temp[j].valueKind != null && temp[j].valueKind == 0) {
							if(jsonObj[i].fieldType.trim().toUpperCase() != "STRING") {
								if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
									if(jsonObj[i].fieldSize == null || jsonObj[i].fieldSize.toString().trim() == "") {
										switch(jsonObj[i].fieldType.trim().toUpperCase()){
            							case "INTEGER" :
											if(!checkInt(temp[j].valueScope.trim())) {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为整数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为整数类型！");
												return false;
											}
											if(parseInt(temp[j].valueScope.trim()) == null || parseInt(temp[j].valueScope.trim()) == 'undefined' || parseInt(temp[j].valueScope.trim()) == 'NaN') {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为整数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为整数类型！");
												return false;
											}
											if(parseInt(temp[j].valueScope.trim()) > 32767 || parseInt(temp[j].valueScope.trim()) < -32768) {
												//alert(resMsg['021'] + fDsp + "字段枚举值应为-32768～32767范围的整数值！");
												alert(resMsg['014'] + fDsp + "字段枚举值应为-32768～32767范围的整数值！");
												return false;
											}
											count2++;
              								break;
            							case "FLOAT" :
											if(!checkFloat(temp[j].valueScope.trim())) {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为浮点数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为浮点数类型！");
												return false;
											}
											if(parseFloat(temp[j].valueScope.trim()) == null || parseFloat(temp[j].valueScope.trim()) == 'undefined' || parseFloat(temp[j].valueScope.trim()) == 'NaN') {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为浮点数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为浮点数类型！");
												return false;
											}
											if(parseFloat(temp[j].valueScope.trim()) > toThePowerOf(2, 128) || parseFloat(temp[j].valueScope.trim()) < (0 - toThePowerOf(2, 128))) {
												//alert(resMsg['021'] + fDsp + "字段枚举值应为" + (0 - toThePowerOf(2, 128)).toString() + "～" + toThePowerOf(2, 128).toString() + "范围的值！");
												alert(resMsg['014'] + fDsp + "字段枚举值应为" + (0 - toThePowerOf(2, 128)).toString() + "～" + toThePowerOf(2, 128).toString() + "范围的值！");
												return false;
											}
											count2++;
              								break;
										}
									}
									else{
										switch(jsonObj[i].fieldType.trim().toUpperCase()){
            							case "INTEGER" :
											if(!checkInt(temp[j].valueScope.trim())) {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为整数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为整数类型！");
												return false;
											}
											if(parseInt(temp[j].valueScope.trim()) == null || parseInt(temp[j].valueScope.trim()) == 'undefined' || parseInt(temp[j].valueScope.trim()) == 'NaN') {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为整数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为整数类型！");
												return false;
											}
											sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
											if(sizeNum > 32767) {
												sizeNum = 32767;
											}
											if(parseInt(temp[j].valueScope.trim()) > sizeNum || parseInt(temp[j].valueScope.trim()) < -32768) {
												//alert(resMsg['021'] + fDsp + "字段枚举值应为不大于" + sizeNum.toString() + "的整数值！");
												alert(resMsg['014'] + fDsp + resMsg['018']);
												return false;
											}
											count2++;
              								break;
            							case "FLOAT" :
											if(!checkFloat(temp[j].valueScope.trim())) {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为浮点数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为浮点数类型！");
												return false;
											}
											if(parseFloat(temp[j].valueScope.trim()) == null || parseFloat(temp[j].valueScope.trim()) == 'undefined' || parseFloat(temp[j].valueScope.trim()) == 'NaN') {
												//alert(resMsg['021'] + fDsp + "字段枚举值不为浮点数类型！");
												alert(resMsg['014'] + fDsp + "字段枚举值不为浮点数类型！");
												return false;
											}
											sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
											if(sizeNum > toThePowerOf(2, 128)) {
												sizeNum = toThePowerOf(2, 128);
											}
											if(parseFloat(temp[j].valueScope.trim()) > sizeNum || parseFloat(temp[j].valueScope.trim()) < (0 - toThePowerOf(2, 128))) {
												//alert(resMsg['021'] + fDsp + "字段枚举值应为不大于" + sizeNum.toString() + "的值！");
												alert(resMsg['014'] + fDsp + resMsg['018']);
												return false;
											}
											count2++;
              								break;
										}
									}
								}
							}
							else {
								if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
									count2++;
									if(jsonObj[i].fieldSize != null && jsonObj[i].fieldSize.toString().trim() != "") {
										if(temp[j].valueScope.trim().length > jsonObj[i].fieldSize) {
											alert(resMsg['014'] + fDsp + resMsg['018']);
											return false;
										}
									}
								}
							}
						}
					}
					if(count2 == 0) {
						alert(resMsg['014']);
						return false;
					}
				}
			}
		}
	}
	if(count1 == 0) {
		alert(resMsg['013']);
		return false;
	}
	
	return true;
}

function checkRecValid(resReal, jsonObj) {
	//if(!isJson(jsonObj)) {
	//	alert(resMsg['015']+"json");
	//	return -3;
	//}
	var cols = resReal.trim().split(",");
	if(cols == null || cols == 'undefined' || cols.length == 0) {
		alert(resMsg['015']+"cols");
		return -3;
	}
	var value = cols[2].trim();
	if(value == null || value.trim() == "") {
		alert(resMsg['015']+"value");
		return -3;
	}
	var fDsp = null;
	var sizeNum = null;
	var count = 0;
	var Min = null;
	var Max = null;
	var hasMin = true;
	var hasMax = true;
	//var values = cols[2].trim().split(' ');
	for(var i = 0; i < jsonObj.length; i++) {
		if(jsonObj[i].fieldname != null && jsonObj[i].fieldname.trim() != "") {
			if(cols[0].trim() == jsonObj[i].fieldname.trim()) {
				temp = jsonObj[i].valueInfo;
				fDsp = jsonObj[i].fieldname.trim();
				if(jsonObj[i].fieldDisp != null && jsonObj[i].fieldDisp.trim() != "") {
					fDsp = jsonObj[i].fieldDisp.trim();
				}
				if(temp != null && temp != 'undefined' && temp.length > 0) {
					if(jsonObj[i].fieldType == null || jsonObj[i].fieldType.trim() == "") {
						alert(resMsg['013'] + fDsp + resMsg['016']);
						//alert(resMsg['013']);
						//return -4;
						return -1;
					}
					if(jsonObj[i].fieldType.trim().toUpperCase() == "STRING") {
						if(temp[0].valueKind != null && temp[0].valueKind == 0) {
							count = 0;
							for(var j = 0; j < temp.length; j++) {
								if(temp[j].valueKind != null && temp[j].valueKind == 0) {
									if(temp[j].valueScope != null && temp[j].valueScope.trim() != "" && value == temp[j].valueScope.trim()) {
										count++;
										break;
									}
								}
							}
							if(count == 0) {
								alert(resMsg['015'] + fDsp + resMsg['017']);
								return -3;
							}
						}
						if(jsonObj[i].fieldSize != null && jsonObj[i].fieldSize.toString().trim() != "") {
							if(value.length > jsonObj[i].fieldSize) {
								alert(resMsg['015'] + fDsp + resMsg['018']);
								return -3;
							}
						}
						if(temp[0].valueKind != null && temp[0].valueKind.toString().trim() != "" && temp[0].valueKind.toString().trim() != "0") {
							alert(resMsg['014'] + fDsp + resMsg['019'] + "忽略上下界！");
						}
					}
					else {
						if(temp[0].valueKind != null && temp[0].valueKind == 0) {
							count = 0;
							for(var j = 0; j < temp.length; j++) {
								if(temp[j].valueKind != null && temp[j].valueKind == 0) {
									if(temp[j].valueScope != null && temp[j].valueScope.trim() != "" && value == temp[j].valueScope.trim()) {
										count++;
										break;
									}
								}
							}
							if(count == 0) {
								alert(resMsg['015'] + fDsp + resMsg['017']);
								return -3;
							}
							if(jsonObj[i].fieldSize == null || jsonObj[i].fieldSize.toString().trim() == "") {
								switch(jsonObj[i].fieldType.trim().toUpperCase()){
            					case "INTEGER" :
									if(!checkInt(value)) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为整数类型的值！");
										return -3;
									}
									if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
										alert(resMsg['015'] + fDsp + "字段枚举值应为整数类型的值！");
										return -3;
									}
									if(parseInt(value) > 32767 || parseInt(value) < -32768) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为-32768～32767范围的整数值！");
										return -3;
									}
              						break;
            					case "FLOAT" :
									if(!checkFloat(value)) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为浮点数类型的值！");
										return -3;
									}
									if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
										alert(resMsg['015'] + fDsp + "字段枚举值应为浮点数类型的值！");
										return -3;
									}
									if(parseFloat(value) > toThePowerOf(2, 128) || parseFloat(value) < (0 - toThePowerOf(2, 128))) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为" + (0 - toThePowerOf(2, 128)).toString() + "～" + toThePowerOf(2, 128).toString() + "范围的值！");
										return -3;
									}
              						break;
								}
							}
							else{
								switch(jsonObj[i].fieldType.trim().toUpperCase()){
            					case "INTEGER" :
									if(!checkInt(value)) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为整数类型的值！");
										return -3;
									}
									if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
										alert(resMsg['015'] + fDsp + "字段枚举值应为整数类型的值！");
										return -3;
									}
									sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
									if(sizeNum > 32767) {
										sizeNum = 32767;
									}
									if(parseInt(value) > sizeNum || parseInt(value) < -32768) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为-32768～" + sizeNum.toString() + "范围的整数值！");
										return -3;
									}
              						break;
            					case "FLOAT" :
									if(!checkFloat(value)) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为浮点数类型的值！");
										return -3;
									}
									if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
										alert(resMsg['015'] + fDsp + "字段枚举值应为浮点数类型的值！");
										return -3;
									}
									sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
									if(sizeNum > toThePowerOf(2, 128)) {
										sizeNum = toThePowerOf(2, 128);
									}
									if(parseFloat(value) > sizeNum || parseFloat(value) < (0 - toThePowerOf(2, 128))) {
										alert(resMsg['015'] + fDsp + "字段枚举值应为" + (0 - toThePowerOf(2, 128)).toString() + "～" + sizeNum.toString() + "范围的值！");
										return -3;
									}
              						break;
								}
							}
						}
						else if(temp[0].valueKind != null && temp[0].valueKind.toString().trim() != "" && temp[0].valueKind.toString().trim() != "0") {
							if(jsonObj[i].fieldSize == null || jsonObj[i].fieldSize.toString().trim() == "") {
								switch(jsonObj[i].fieldType.trim().toUpperCase()){
            					case "INTEGER" :
									//for(var x = 0; x < values.length; x++) {}
									if(!checkInt(value)) {
										alert(resMsg['022'] + fDsp + "字段的取值请输入整数类型的值！");
										return -3;
									}
									if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
										alert(resMsg['022'] + fDsp + "字段的取值请输入整数类型的值！");
										return -3;
									}
									Max = 32767;
									Min = -32768;
									for(var j = 0; j < temp.length; j++) {
										if(temp[j].valueKind != null && temp[j].valueKind != 0) {
											if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
												if(parseInt(temp[j].valueScope.trim()) == null || 
													parseInt(temp[j].valueScope.trim()) == 'undefined' || 
													parseInt(temp[j].valueScope.trim()) == 'NaN') {
													alert(resMsg['014'] + fDsp + resMsg['020'] + "忽略上下界！");
												}
												else if(checkInt(temp[j].valueScope.trim()) && 
													parseInt(temp[j].valueScope.trim()) != null && 
													parseInt(temp[j].valueScope.trim()) != 'undefined' && 
													parseInt(temp[j].valueScope.trim()) != 'NaN') { 
													switch(temp[j].valueKind){
														case "1":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Min = parseInt(temp[j].valueScope.trim());
															}
															hasMin = true;
															break;
														case "2":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Max = parseInt(temp[j].valueScope.trim());
															}
															hasMax = true;
															break;
														case "-1":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Min = parseInt(temp[j].valueScope.trim());
															}
															hasMin = false;
															break;
														case "-2":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Max = parseInt(temp[j].valueScope.trim());
															}
															hasMax = false;
															break
													}
												}
											}
										}
									}
									if(hasMin == true && hasMax == true) {
										if(parseInt(value) > Max || parseInt(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == false) {
										if(parseInt(value) >= Max || parseInt(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(不含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == true && hasMax == false) {
										if(parseInt(value) >= Max || parseInt(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(不含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == true) {
										if(parseInt(value) > Max || parseInt(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(含该值)范围的整数值！");
											return -3;
										}
									}
              						break;
            					case "FLOAT" :
									if(!checkFloat(value)) {
										alert(resMsg['022'] + fDsp + "字段的取值请输入浮点数类型的值！");
										return -3;
									}
									if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
										alert(resMsg['022'] + fDsp + "字段的取值请输入浮点数类型的值！");
										return -3;
									}
									Max = toThePowerOf(2, 128);
									Min = 0 - toThePowerOf(2, 128);
									for(var j = 0; j < temp.length; j++) {
										if(temp[j].valueKind != null && temp[j].valueKind != 0) {
											if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
												if(parseFloat(temp[j].valueScope.trim()) == null || 
													parseFloat(temp[j].valueScope.trim()) == 'undefined' || 
													parseFloat(temp[j].valueScope.trim()) == 'NaN') {
													alert(resMsg['014'] + fDsp + resMsg['020'] + "忽略上下界！");
												}
												else if(checkFloat(temp[j].valueScope.trim()) && 
													parseFloat(temp[j].valueScope.trim()) != null && 
													parseFloat(temp[j].valueScope.trim()) != 'undefined' && 
													parseFloat(temp[j].valueScope.trim()) != 'NaN') { 
													switch(temp[j].valueKind){
														case "1":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Min = parseFloat(temp[j].valueScope.trim());
															}
															hasMin = true;
															break;
														case "2":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Max = parseFloat(temp[j].valueScope.trim());
															}
															hasMax = true;
															break;
														case "-1":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Min = parseFloat(temp[j].valueScope.trim());
															}
															hasMin = false;
															break;
														case "-2":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Max = parseFloat(temp[j].valueScope.trim());
															}
															hasMax = false;
															break
													}
												}
											}
										}
									}
									if(hasMin == true && hasMax == true) {
										if(parseFloat(value) > Max || parseFloat(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == false) {
										if(parseFloat(value) >= Max || parseFloat(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(不含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == true && hasMax == false) {
										if(parseFloat(value) >= Max || parseFloat(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(不含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == true) {
										if(parseFloat(value) > Max || parseFloat(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(含该值)范围的值！");
											return -3;
										}
									}
              						break;
								}
							}
							else{
								switch(jsonObj[i].fieldType.trim().toUpperCase()){
            					case "INTEGER" :
									if(!checkInt(value)) {
										alert(resMsg['022'] + fDsp + "字段的取值请输入整数类型的值！");
										return -3;
									}
									if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
										alert(resMsg['022'] + fDsp + "字段的取值请输入整数类型的值！");
										return -3;
									}
									Max = 32767;
									Min = -32768;
									sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
									if(sizeNum < Max && sizeNum > Min) {
										Max = sizeNum;
									}
									for(var j = 0; j < temp.length; j++) {
										if(temp[j].valueKind != null && temp[j].valueKind != 0) {
											if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
												if(parseInt(temp[j].valueScope.trim()) == null || 
													parseInt(temp[j].valueScope.trim()) == 'undefined' || 
													parseInt(temp[j].valueScope.trim()) == 'NaN') {
													alert(resMsg['014'] + fDsp + resMsg['020'] + "忽略上下界！");
												}
												else if(checkInt(temp[j].valueScope.trim()) && 
													parseInt(temp[j].valueScope.trim()) != null && 
													parseInt(temp[j].valueScope.trim()) != 'undefined' && 
													parseInt(temp[j].valueScope.trim()) != 'NaN') { 
													switch(temp[j].valueKind){
														case "1":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Min = parseInt(temp[j].valueScope.trim());
															}
															hasMin = true;
															break;
														case "2":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Max = parseInt(temp[j].valueScope.trim());
															}
															hasMax = true;
															break;
														case "-1":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Min = parseInt(temp[j].valueScope.trim());
															}
															hasMin = false;
															break;
														case "-2":
															if(parseInt(temp[j].valueScope.trim()) > Min && parseInt(temp[j].valueScope.trim()) < Max) {
																Max = parseInt(temp[j].valueScope.trim());
															}
															hasMax = false;
															break
													}
												}
											}
										}
									}
									if(hasMin == true && hasMax == true) {
										if(parseInt(value) > Max || parseInt(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == false) {
										if(parseInt(value) >= Max || parseInt(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(不含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == true && hasMax == false) {
										if(parseInt(value) >= Max || parseInt(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(不含该值)范围的整数值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == true) {
										if(parseInt(value) > Max || parseInt(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(含该值)范围的整数值！");
											return -3;
										}
									}
              						break;
            					case "FLOAT" :
									if(!checkFloat(value)) {
										alert(resMsg['022'] + fDsp + "字段的取值请输入浮点数类型的值！");
										return -3;
									}
									if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
										alert(resMsg['022'] + fDsp + "字段的取值请输入浮点数类型的值！");
										return -3;
									}
									Max = toThePowerOf(2, 128);
									Min = 0 - toThePowerOf(2, 128);
									sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
									if(sizeNum < Max && sizeNum > Min) {
										Max = sizeNum;
									}
									for(var j = 0; j < temp.length; j++) {
										if(temp[j].valueKind != null && temp[j].valueKind != 0) {
											if(temp[j].valueScope != null && temp[j].valueScope.trim() != "") {
												if(parseFloat(temp[j].valueScope.trim()) == null || 
													parseFloat(temp[j].valueScope.trim()) == 'undefined' || 
													parseFloat(temp[j].valueScope.trim()) == 'NaN') {
													alert(resMsg['014'] + fDsp + resMsg['020'] + "忽略上下界！");
												}
												else if(checkFloat(temp[j].valueScope.trim()) && 
													parseFloat(temp[j].valueScope.trim()) != null && 
													parseFloat(temp[j].valueScope.trim()) != 'undefined' && 
													parseFloat(temp[j].valueScope.trim()) != 'NaN') { 
													switch(temp[j].valueKind){
														case "1":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Min = parseFloat(temp[j].valueScope.trim());
															}
															hasMin = true;
															break;
														case "2":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Max = parseFloat(temp[j].valueScope.trim());
															}
															hasMax = true;
															break;
														case "-1":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Min = parseFloat(temp[j].valueScope.trim());
															}
															hasMin = false;
															break;
														case "-2":
															if(parseFloat(temp[j].valueScope.trim()) > Min && parseFloat(temp[j].valueScope.trim()) < Max) {
																Max = parseFloat(temp[j].valueScope.trim());
															}
															hasMax = false;
															break
													}
												}
											}
										}
									}
									if(hasMin == true && hasMax == true) {
										if(parseFloat(value) > Max || parseFloat(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == false) {
										if(parseFloat(value) >= Max || parseFloat(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(不含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == true && hasMax == false) {
										if(parseFloat(value) >= Max || parseFloat(value) < Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(含该值)～" + Max.toString() + "(不含该值)范围的值！");
											return -3;
										}
									}
									else if(hasMin == false && hasMax == true) {
										if(parseFloat(value) > Max || parseFloat(value) <= Min) {
											alert(resMsg['022'] + fDsp + "字段的取值请输入" + Min.toString() + "(不含该值)～" + Max.toString() + "(含该值)范围的值！");
											return -3;
										}
									}
              						break;
								}
							}
						}
					}
				}
				else {
					if(jsonObj[i].fieldType == null || jsonObj[i].fieldType.trim() == "") {
						alert(resMsg['013'] + fDsp + resMsg['016']);
						//alert(resMsg['013']);
						//return -4;
						return -1;
					}
					if(jsonObj[i].fieldSize == null || jsonObj[i].fieldSize.toString().trim() == "") {
						switch(jsonObj[i].fieldType.trim().toUpperCase()){
            			case "INTEGER" :
							//for(var x = 0; x < values.length; x++) {}
							if(!checkInt(value)) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入整数类型的值！");
								//return -5;
								return 0;
							}
							if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入整数类型的值！");
								//return -5;
								return 0;
							}
							if(parseInt(value) > 32767 || parseInt(value) < -32768) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入-32768～32767范围的整数值！");
								//return -6;
								return 0;
							}
              				break;
            			case "FLOAT" :
							//for(var x = 0; x < values.length; x++) {}
							if(!checkFloat(value)) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入浮点数类型的值！");
								//return -7;
								return 0;
							}
							if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入浮点数类型的值！");
								//return -7;
								return 0;
							}
							if(parseFloat(value) > toThePowerOf(2, 128) || parseFloat(value) < (0 - toThePowerOf(2, 128))) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入" + (0 - toThePowerOf(2, 128)).toString() + "～" + toThePowerOf(2, 128).toString() + "范围的值！");
								//return -8;
								return 0;
							}
              				break;
            			//case "STRING" :
              			//	break;
						}
					}
					else{
						switch(jsonObj[i].fieldType.trim().toUpperCase()){
            			case "INTEGER" :
							//for(var x = 0; x < values.length; x++) {}
							if(!checkInt(value)) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入整数类型的值！");
								//return -5;
								return 0;
							}
							if(parseInt(value) == null || parseInt(value) == 'undefined' || parseInt(value) == 'NaN') {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入整数类型的值！");
								//return -5;
								return 0;
							}
							sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
							if(sizeNum > 32767) {
								sizeNum = 32767;
							}
							if(parseInt(value) > sizeNum || parseInt(value) < -32768) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入-32768～" + sizeNum.toString() + "范围的整数值！");
								//return -6;
								return 0;
							}
              				break;
            			case "FLOAT" :
							//for(var x = 0; x < values.length; x++) {}
							if(!checkFloat(value)) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入浮点数类型的值！");
								//return -7;
								return 0;
							}
							if(parseFloat(value) == null || parseFloat(value) == 'undefined' || parseFloat(value) == 'NaN') {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入浮点数类型的值！");
								//return -7;
								return 0;
							}
							sizeNum = toThePowerOf(10, jsonObj[i].fieldSize) - 1;
							if(sizeNum > toThePowerOf(2, 128)) {
								sizeNum = toThePowerOf(2, 128);
							}
							if(parseFloat(value) > sizeNum || parseFloat(value) < (0 - toThePowerOf(2, 128))) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入" + (0 - toThePowerOf(2, 128)).toString() + "～" + sizeNum.toString() + "范围的值！");
								//return -8;
								return 0;
							}
              				break;
            			case "STRING" :
							if(value.length > jsonObj[i].fieldSize) {
								alert(resMsg['022'] + fDsp + "字段的取值" + "请输入不多于" + jsonObj[i].fieldSize + "个字符的值！");
								return 0;
							}
              				break;
						}
					}
				}
				return resReal.length;
				//break;
			}
		}
	}
	return 0;
}

function createEpsRes(arrayCon, showRes, jsonObj) {
	alert("here1");
	var res = "";
	var cons = null;
	var newCons = null;
	var newCon = "";
	var tempCon = null;
	var tempCon2 = null;
	var isValid = null;
	var x = 0;
	var y = 0;
	var i = 0;
	var flag = -1;
	alert(arrayCon.length);
	for(x = 0; x < arrayCon.length; x++) {
		alert("here1"+x);
		if(arrayCon[x] == null || arrayCon[x].trim() == "") {
			alert("null1");
			continue;
		}
		
		cons = arrayCon[x].trim().split(",");
		if(cons == null || cons.length == 0) {
			alert("null2");
			continue;
		}
		alert("js");
		alert("js:"+jsonObj.length);
		for(i = 0; i < jsonObj.length; i++) {
			alert("in loop");
			if(jsonObj[i].fieldname != null && jsonObj[i].fieldname.trim() == cons[0]) {
				alert("in if1");
				if(jsonObj[i].fieldType.trim().toUpperCase() == "STRING") {
					flag = 0;
					alert("break1");
					break;
				}
				else {
					flag = 1;
					alert("break2");
					break;
				}
			}
			else{
				alert("in if1 else");
			}
		}
		
		alert("loop1 out "+x);
		
		if(flag == -1) {
			continue;
		}
//		isValid = checkRecValid(arrayCon[x].trim(), jsonObj);
//		if(isValid <= 0) {
//			return isValid;
//		}
		if(cons.length >= 4) {
			tempCon = "";
			tempCon2 = "";
			newCons = cons[2].trim().split("@**@");
			if(newCons == null || newCons.length == 0) {
				continue;
			}
			for(y = 0; y < newCons.length; y++) {
				if(newCons[y] != null && newCons[y].trim() != "") {
					newCon = cons[0] + "," + cons[1] + "," + newCons[y].trim() + "," + cons[cons.length - 2];
					isValid = checkRecValid(newCon.trim(), jsonObj);
					if(isValid <= 0) {
						return isValid;
					}
					if(tempCon != null && tempCon.trim() == "") {
						if(cons[cons.length - 1].trim() == "true") {
							tempCon += "NOT ";
						}
						tempCon += "(";
					}
					else if(tempCon != null && tempCon.trim() != "") {
						tempCon += " OR ";
					}
					tempCon += cons[0] + cons[1];
					if(flag == 0) {
						tempCon += '"' + newCons[y].trim() + '"';
					}
					else if(flag == 1) {
						tempCon += newCons[y].trim();
					}
				}
			}
			if(cons[3] != null && cons[3].trim() != "" && cons[3].trim().toUpperCase() != "OR" && cons[3].trim().toUpperCase() != "AND") {
				if(tempCon != null && tempCon.trim() == "") {
					if(cons[cons.length - 1].trim() == "true") {
						tempCon += "NOT ";
					}
					tempCon += "(";
				}
				else if(tempCon != null && tempCon.trim() != "") {
					tempCon += " AND ";
				}
				
				newCons = cons[4].trim().split("@**@");
				if(newCons == null || newCons.length == 0) {
					continue;
				}
				for(y = 0; y < newCons.length; y++) {
					if(newCons[y] != null && newCons[y].trim() != "") {
						newCon = cons[0] + "," + cons[3] + "," + newCons[y].trim() + "," + cons[cons.length - 1];
						isValid = checkRecValid(newCon.trim(), jsonObj);
						if(isValid <= 0) {
							return isValid;
						}
						if(tempCon2 != null && tempCon2.trim() != "") {
							tempCon2 += " OR ";
						}
						//else if(tempCon2 != null && tempCon2.trim() == "") {
						//	tempCon2 = "(";
						//}
						tempCon2 += cons[0] + cons[3];
						if(flag == 0) {
							tempCon2 += '"' + newCons[y].trim() + '"';
						}
						else if(flag == 1) {
							tempCon2 += newCons[y].trim();
						}
					}
				}
				if(tempCon2 != null && tempCon2.trim() != "") {
					//tempCon2 += ")";
					tempCon += tempCon2;
				}
			}
			
			if(tempCon != null && tempCon.trim() != "") {
				tempCon += ")";
				res += tempCon;
			}
			if(x != arrayCon.length - 1) {
				res += "  " + cons[cons.length - 2] + "  ";
			}
		}
	}
	alert("here2");
	showRes.innerHTML = res;
	return res.length;
}