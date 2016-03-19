$(document).ready(function(){
	
	$(".iframe-area #recharge").click(function(){
		var $recharge = $("#balance").val();
		var $userId = $("#user-id").val();
		
		var info = {
			userId:$userId,
			money:$recharge
		};
		
		$.ajax({
			type:"POST",
			url:"../../../pandastore/user/deposit.do",
			data:info,
			success:function(json){
				if (json.status == "success") {
					alert(json.message);
					location.reload(true);
				}
				else {
					alert(json.message);
				}
			}
		});
	});
	
});