$(function() 
{
	 $("#infoBox")
	.css( 
	{
	   "background":"none"
	})
	.dialog({ autoOpen: false, 
		show: { effect: 'fade', duration: 500 },
		hide: { effect: 'fade', duration: 500 } 
	});
	
	 $("#infoButton")
       .text("") // sets text to empty
	.css(
	{ "z-index":"2",
	  "background":"rgba(0,0,0,0)", "opacity":"0.9", 
	  "position":"absolute", "top":"4px", "left":"4px"
	}) // adds CSS
    .append("<img width='32' height='32' src='images/icon-info.png'/>")
    .button()
	.click( 
		function() 
		{ 
			$("#infoBox").dialog("open");
		});
});

$(function() 
{
	 $("#infoBox")
	.css( 
	{
	   "background":"none"
	})
	.dialog({ autoOpen: false, 
		show: { effect: 'fade', duration: 500 },
		hide: { effect: 'fade', duration: 500 } 
	});
	
	 $("#infoButton")
       .text("") // sets text to empty
	.css(
	{ "z-index":"2",
	  "background":"rgba(0,0,0,0)", "opacity":"0.9", 
	  "position":"absolute", "top":"4px", "left":"4px"
	}) // adds CSS
    .append("<img width='32' height='32' src='images/icon-info.png'/>")
    .button()
	.click( 
		function() 
		{ 
			$("#info-empleado").dialog("open");
		});
});