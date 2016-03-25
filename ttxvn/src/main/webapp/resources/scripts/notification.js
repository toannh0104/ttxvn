jQuery(document).ready(function ($) {
    loadNotification('/ttxvn/news/getNotification');	
	setInterval(function(){
		loadNotification('/ttxvn/news/getNotification');	
	}, 30000);
});


function loadNotification(url){
	jQuery.ajax({
		cache: false,
		type: "get",
		url: url,
		dataType:'json',
		success: function(response){
			var count = 0;
			var content = '';
			var hasMsg = 'false';
			
			jQuery.each(response, function(key, value){
				if(key == 'isNotify')
					hasMsg = value;
				else{
					count++;
					var title = value;
					if (value != null && value.length > 30) {
						title = value.substring(0, 30) + "...";
					}
					content +="<li><a href='"+key+"' target='blank' title='"+value+"'>"+title+"</a></li>";
				}
			});
			
			if(count > 0){
				jQuery("#show-notification").empty();
				jQuery("#show-notification").append(content);
				if(hasMsg == 'true'){
					jQuery(".notification-count-value").parent().addClass("has-message");
					jQuery(".notification-count-value").text(count);
				}
			}
		}
	});
}

function removeNotification(){
	jQuery(".notification-count-value").parent().removeClass("has-message");
	jQuery(".notification-count-value").empty();
	var url = '/ttxvn/news/remove-notification';
	jQuery.ajax({
		cache: false,
		type: "get",
		url: url,
		dataType:'json',
	});
}