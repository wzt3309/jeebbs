 function after(){
		var pagsize=parseInt(document.getElementById("pagesize").value);
		var page_now=parseInt(document.getElementById("page").innerHTML);
		
		if(page_now<pagsize){
			turnToPage(page_now+1);
		}
		else if(page_now==pagsize){
			alert("已经是末页！");
		}
		
	}

	function before(){
		var pagsize=parseInt(document.getElementById("pagesize").value);
		var page_now=parseInt(document.getElementById("page").innerHTML);
		
		if(page_now>1){
			turnToPage(page_now-1);
		}
		else if(page_now==1){
			alert("已经是首页！");
		}
		
	}

	function turn(){
		
		
		if(document.getElementById("page_for_turn").value==null){
			alert("请输入页码！");
		}
		else{
			var pagsize=parseInt(document.getElementById("pagesize").value);
			
			page_to=parseInt(document.getElementById("page_for_turn").value);
			
			if(page_to<=pagsize){
				turnToPage(page_to);
			}
			else {
				alert("超出范围！");
			}
		}
		
	}

	function turnToPage(pageno){
		
		var i;
		
		var tr_to_deal=document.getElementsByName("show_or_not1");
		
		for(i=0;i<tr_to_deal.length;i++){
			
			if(tr_to_deal[i].id>=(pageno-1)*20+1 && tr_to_deal[i].id<=(pageno-1)*20+20){
				tr_to_deal[i].style.display="";
			}
			else{
				tr_to_deal[i].style.display="none";
			}
			
			document.getElementById("page").innerHTML=pageno.toString();
		}
		
		 tr_to_deal=document.getElementsByName("show_or_not2");
		
	for(i=0;i<tr_to_deal.length;i++){
			
			if(tr_to_deal[i].id>=(pageno-1)*20+1 && tr_to_deal[i].id<=(pageno-1)*20+20){
				tr_to_deal[i].style.display="";
			}
			else{
				tr_to_deal[i].style.display="none";
			}
		}
		}
		
	function color(){
		
		
		var tr_to_deal=document.getElementsByName("show_or_not2");
		var i=0;
		
		var i;
		for(i=0;i<tr_to_deal.length;i++){
			var tdtd=tr_to_deal[i].getElementsByTagName('td');
			var j=0;
			for(j;j<tdtd.length;j++){
				if(tdtd[j].id.trim()!="not"){
					if(parseFloat(tdtd[j].innerHTML)>=0){
						tdtd[j].className="colorRED textRight";
					}
					else{
						tdtd[j].className="colorGREEN textRight";
					}
					
					
				}
			}
			
		}
		
	}