function getRealStyle(el,cssName)  
{      
	if(el === null || typeof el === "undefined"){
		return;
	}
    var len=arguments.length, sty, f, fv;  
                      
    'currentStyle' in el ? sty=el.currentStyle : 'getComputedStyle' in window   
                         ? sty=window.getComputedStyle(el,null) : null;  
                                          
    if(cssName==="opacity" && document.all){  
        f = el.filters;  
        f && f.length>0 && f.alpha ? fv=f.alpha.opacity/100 : fv=1;                    
        return fv;  
    }     
    cssName==="float" ? document.all ? cssName='styleFloat' : cssName='cssFloat' : cssName;   
    sty = (len==2) ? sty[cssName] : sty;                                  
    return sty;  
}     