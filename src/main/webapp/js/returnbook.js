$(document).ready(function(){
	
	$("#get-info").click(function(){
		var $table = $("#table");

		var info = {
				userId:$(".iframe-area #user-id").val()
			};

		$.ajax({
			type:"POST",
			url:"../../../pandastore/user/getInfById.do",
			data:info,
			success:function(json){
				if (json.status == "success"){
					$(".iframe-table").removeClass("none");
					$table.bootstrapTable({data:json.borrowbooks});
				}
				else{
					alert(json.message);
				}
			}
		});
	});
	
	window.detailImageOperateEvents={
		'click .edit' : function(e, value, row, index){
			var info = {
					userId:$(".iframe-area #user-id").val()
				};
			
			$.ajax({
				type:"POST",
				url:"../../../pandastore/user/getInfById.do",
				data:info,
				success:function(json){
					if (json.status == "success"){
						//alert(json.status);
						var $datas = json.borrowbooks;
						var $borrowId = $datas[index].borrowId;
						
						$(".rank-none").val($borrowId);
						
						var info_fine = {
								borrowId:$borrowId	
							};

						$.ajax({
							type:"POST",
							url:"../../../pandastore/borrow/getFine.do",
							data:info_fine,
							success:function(json_fine){
								if (json_fine.status == "success"){
									if (json_fine.fine > json.balance) {
										$(".btn-return").attr("disabled",true);
									}
								}
								else{
									alert(json_fine.message);
								}
							}
						});
					}
					else{
						alert(json.message);
					}
				}
			});
		}		
	};
	
	$(".btn-return").click(function(){
		var $borrowId = $(".rank-none").val();

		var info_fine = {
				borrowId:$borrowId	
			};

		$.ajax({
			type:"POST",
			url:"../../../pandastore/borrow/getFine.do",
			data:info_fine,
			success:function(json_fine){
				if (json_fine.status == "success"){
					var info_return = {
							borrowId:$borrowId,
							fine:json_fine.fine
					};

					$.ajax({
						type:"POST",
						url:"../../../pandastore/borrow/returnBook.do",
						data:info_return,
						success:function(json_return){
							if (json_return.status == "success") {
								//alert(json_return.status);
								alert(json_return.message);
								location.reload(true);
							}
							else{
								alert(json_return.message);
							}
						}
					});
				}
				else{
					alert(json_fine.message);
				}
			}
		});
		
	});
	
});



function detailImageManageFormatter(value, row, index){
	return [
		'<button class="edit return btn btn-danger" data-target="#manageDetailImageModal" data-toggle="modal" title="edit">',
		'归还',
		'</button>'
	].join('');
}