$(document).ready(function(){
	
	$(".iframe-area #borrow").click(function(){
		var $bookId = $("#book-id").val();
		var $userId = $("#user-id").val();
		
		var info = {
			bookId:$bookId,
			userId:$userId
		};
		
		$.ajax({
			type:"POST",
			url:"../../../pandastore/book/borrowABook.do",
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