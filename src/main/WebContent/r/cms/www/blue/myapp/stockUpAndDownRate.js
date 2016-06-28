
		
    	$(function () {  
		
    	var data=[];
		var data2=[];
    	var qrrate=[];
    	var udrate=[];
		var futureLeadingIndex=[];
		var financeLeadingIndex=[];
    	var ele=document.getElementsByTagName('a');
    	var  j=1;
    	for(var i=0;i<ele.length;i++){
    		 if(ele[i].className=="app01Date"){    			
    				 data.push(ele[i].innerHTML);	 
    			
        	}
			else if(ele[i].className=="app04Date"){    			
    				 data2.push(ele[i].innerHTML);	 
    			
        	}
        	else if(ele[i].className=="app01Rate"){
        		qrrate.push(parseFloat(ele[i].innerHTML));
        	}
        	else if(ele[i].className=="app02Rate"){
        		udrate.push(parseFloat(ele[i].innerHTML));
        	}
			else if(ele[i].className=="app03Rate"){
				futureLeadingIndex.push(parseFloat(ele[i].innerHTML));
			}
			else if(ele[i].className=="app04Rate"){
				financeLeadingIndex.push(parseFloat(ele[i].innerHTML));
			}
    	} 
    	data.reverse();
		data2.reverse();
    	Highcharts.setOptions({
    		lang: {
    	        resetZoom: '重置',
    	        resetZoomTitle:"重置缩放比例"
    		}
    	});
    	
    $('#stockQiangRuoRate').highcharts({
        chart: {
            type: 'line',
            zoomType: 'xy',
            panning: true,
			panKey: 'shift',
			events: {
                load: function () {
                    var label1 = this.renderer.label('鼠标框选的区域会放大', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
						
                    }, 3000);
                },
				selection: function () {
                    var label1 = this.renderer.label('按住shift，可左右拖动图表', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
						
                    }, 2000);
                }
				
				
            } 
           
            
        },
        title: {
            text: '股票强弱比'
        },
        subtitle: {
            text: 'Source: jeebbs.com'
        },
        xAxis: {
            categories: data,           
            tickmarkPlacement: 'on',
            	
            tickInterval:5
        },
        yAxis: {
            title: {
                text: '单位 (%)'
            },
			 tickPixelInterval:10  
			
        },
        tooltip: {
        	 valueSuffix: '%',
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'%';
            },
            crosshairs: [{            // 设置准星线样式
                width: 1.5,
                color: 'green'
            }, {
                width: 1,
                color: "#006cee",
                dashStyle: 'longdashdot',
                zIndex: 100 
            }]
        
        },      
       
        series: [{
            name: 'A股股票强弱比',
            data: qrrate.reverse()
            									
        						
        }]
    });
    
    
    $('#stockUpAndDownRate').highcharts({
        chart: {
        	 type: 'line',
             zoomType: 'xy',
             panning: true,
 panKey: 'shift',
 events: {
                load: function () {
                    var label1 = this.renderer.label('鼠标框选的区域会放大', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
						
                    }, 3000);
                },
				selection: function () {
                    var label1 = this.renderer.label('按住shift，可用鼠标拖动图表', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
					
                    }, 2000);
                }
		
				
            } 
        },
        title: {
            text: '股票每日涨跌比'
        },
        subtitle: {
            text: 'Source: jeebbs.com'
        },
        xAxis: {
            categories: data,         
            tickmarkPlacement: 'on',            	
            tickInterval:5
            
        },
        yAxis: {
            title: {
                text: '单位 (%)'
            },
			tickPixelInterval:5
        },
        tooltip: {
        	 valueSuffix: '%',
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'%';
            },
            crosshairs: [{            // 设置准星线样式
                width: 1.5,
                color: 'green'
            }, {
                width: 1,
                color: "#006cee",
                dashStyle: 'longdashdot',
                zIndex: 100 
            }]
        },       
        series: [{
            name: 'A股股票每日涨跌比',
            data: udrate.reverse()
            									
        						
        }]
    });
    //FutureLeadingIndex
	 $('#futureLeadingIndex').highcharts({
        chart: {
        	 type: 'line',
             zoomType: 'xy',
             panning: true,
 panKey: 'shift',
 events: {
                load: function () {
                    var label1 = this.renderer.label('鼠标框选的区域会放大', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
						
                    }, 3000);
                },
				selection: function () {
                    var label1 = this.renderer.label('按住shift，可用鼠标拖动图表', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
					
                    }, 2000);
                }
				
				
            } 
        },
        title: {
            text: '期指领先指数'
        },
        subtitle: {
            text: 'Source: jeebbs.com'
        },
        xAxis: {
            categories: data2,         
            tickmarkPlacement: 'on'          	
            
            
        },
        yAxis: {
            title: {
                text: '单位 (%)'
            },
			 tickPixelInterval:10  
			
        },
        tooltip: {
        	 valueSuffix: '%',
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'%';
            },
            crosshairs: [{            // 设置准星线样式
                width: 1.5,
                color: 'green'
            }, {
                width: 1,
                color: "#006cee",
                dashStyle: 'longdashdot',
                zIndex: 100 
            }]
        },       
        series: [{
            name: '期指每日领先指数',
            data: futureLeadingIndex.reverse()
            									
        						
        }]
    });
	 //FinanceLeadingIndex
	 $('#financeLeadingIndex').highcharts({
        chart: {
        	 type: 'line',
             zoomType: 'xy',
             panning: true,
 panKey: 'shift',
 events: {
                load: function () {
                    var label1 = this.renderer.label('鼠标框选的区域会放大', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
						
                    }, 3000);
                },
				selection: function () {
                    var label1 = this.renderer.label('按住shift，可用鼠标拖动图表', 100, 120)
                        .attr({
                            fill: Highcharts.getOptions().colors[0],
                            padding: 10,
                            r: 5,
                            zIndex: 8
                        })
                        .css({
                            color: '#FFFFFF'
                        })
                        .add();
					


                    setTimeout(function () {
                        label1.fadeOut();
					
                    }, 2000);
                }
				
				
            } 
        },
        title: {
            text: '融资融券领先指数'
        },
        subtitle: {
            text: 'Source: jeebbs.com'
        },
        xAxis: {
            categories: data2,         
            tickmarkPlacement: 'on'    ,      	
           tickInterval:5
            
        },
        yAxis: {
            title: {
                text: '单位 (亿元)'
            },
			 tickPixelInterval:10 
        },
        tooltip: {
        	 valueSuffix: '亿元',
            formatter: function() {
                return '<b>'+ this.series.name +'</b><br>'+this.x +': '+ this.y +'亿元';
            },
            crosshairs: [{            // 设置准星线样式
                width: 1.5,
                color: 'green'
            }, {
                width: 1,
                color: "#006cee",
                dashStyle: 'longdashdot',
                zIndex: 100 
            }]
        },       
        series: [{
            name: '融资融券每日领先指数',
            data: financeLeadingIndex.reverse()
            									
        						
        }]
    });
   
});
    	 


