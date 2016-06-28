var selectNum = 0;
var total = 0;
var inputValue;
var lastValue;
var submitTimer;
var hideTimer;
function flushColor(num, color,highlightcolor, id){
	for(var i = 1;i<=num;i++){
		$("#"+i).css("color",color);
	}
	$("#"+selectNum).css("color",highlightcolor);
	if(id){
		setValue(id);
	}
}

function setValue(id){
	if(selectNum==0){
		$("#"+id).val(inputValue);
		lastValue = inputValue;
	}else{
		$("#"+id).val($("#"+selectNum).html());
		lastValue = $("#"+selectNum).html();
	}
}

function hide(obj){
	obj.hide();
	obj.html("");
	reset();
}

function reset(){
	selectNum = 0;
	total = 0;
	lastValue = "";
}

function suggest(url, id, container, count, delay, maxtime){
	var inputObj = $("#"+id);
	var containerObj = $("#"+container);
	inputObj.keyup(function(event){
		if(event.keyCode==38){
			if(total!=0){
				if(selectNum==0){
					selectNum = total;
				}else{
					selectNum--;
				}
				flushColor(total, "", "red", id);
			}
		}
		else if(event.keyCode==40){
			if(total!=0){
				if(selectNum==total){
					selectNum = 0;
				}else{
					selectNum++;
				}
				flushColor(total, "", "red", id);
			}
		}else{
			var value = $.trim(inputObj.val());
			if(value!=""){
				inputValue = value;
				if(lastValue!=inputObj.val()){
					clearTimeout(submitTimer);
					submitTimer = setTimeout(function(){
						$.post(url,{"username":value,"count":count},function(data){
							if(data==""){
								hide(containerObj);
							}else{
								reset();
								containerObj.html(data);
								containerObj.show();
								clearTimeout(hideTimer);
								hideTimer = setTimeout(function(){
									hide(containerObj);
    							},maxtime);
							}
							$("#"+container+">div").mouseover(function(){
								selectNum = $(this).attr("id");
								flushColor(total, "", "red");
							});
							$("#"+container+">div").click(function(){
								inputObj.val($("#"+selectNum).html());
								hide(containerObj);
							});
							lastValue = inputObj.val();
						});
					},delay);
				}
			}else{
				clearTimeout(submitTimer);
				hide(containerObj);
			}
		}
    });
}