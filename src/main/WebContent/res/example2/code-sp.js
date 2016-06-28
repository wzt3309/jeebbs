	function $(id) {
		return document.getElementById(id);
	}
	function GetObjValue(objName)
{
	if(document.getElementById)
	{
		return eval('document.getElementById("'+objName+'")');
	}
	else
	{
		return eval('document.all.'+objName);
	}
}

	function gotoLookupN_0() {
		var value = $("no").value.replace(/s[hz]/, "");
		if (value != "上海深圳股票代码/名称/拼音" && value != "") {
		    var stringUrl = "";
			//var stringUrl = "http://www.chaguwang.cn/showsp.php?no=@CODE@&date="+GetObjValue("date").value;
			//window.open(stringUrl.replace("@CODE@", value),"_self");
		}
	}

	var suggestStock_0 = new Suggest("no", "上海深圳股票代码/名称/拼音", astock_suggest, null, StockSuggestConfiguration, gotoLookupN_0);


	//RecommondStock.init();tock.init();