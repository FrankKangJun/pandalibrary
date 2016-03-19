$(document).ready(function(){
	
	$(".info-form-1 .input-box").attr("checked",true);
	$(".info-form-2 .input-box").attr("checked",false);
	
	$(".info-form-1 #add").attr("disabled",false);
	$(".info-form-1 #reset").attr("disabled",false);
	$(".info-form-1 #cancel").attr("disabled",false);
	
	$(".info-form-2 #add").attr("disabled",true);
	$(".info-form-2 #reset").attr("disabled",true);
	$(".info-form-2 #cancel").attr("disabled",true);
	
	$(".info-form-1 fieldset").attr("disabled",false);
	$(".info-form-2 fieldset").attr("disabled",true);
	
	$(".info-form-1 .input-box").click(function(){
		$(".info-form-1 .input-box").attr("checked",true);
		$(".info-form-2 .input-box").attr("checked",false);
		
		$(".info-form-1 #add").attr("disabled",false);
		$(".info-form-1 #reset").attr("disabled",false);
		$(".info-form-1 #cancel").attr("disabled",false);
		
		$(".info-form-2 #add").attr("disabled",true);
		$(".info-form-2 #reset").attr("disabled",true);
		$(".info-form-2 #cancel").attr("disabled",true);
		
		$(".info-form-1 fieldset").attr("disabled",false);
		$(".info-form-2 fieldset").attr("disabled",true);
	});
	
	$(".info-form-1 .icon-minus").click(function(){
		var $status = $(".info-form-1 fieldset").attr("disabled");
		
		if (typeof($status) == "undefined") {
			var $num = parseInt($(".info-form-1 .num-box").val());
	
			if ($num <= 0) {
				// 数量不能少于0
			}
			else {
				$num = $num - 1;
				$(this).next("input").val($num);
			}
		}
	});

	$(".info-form-1 .icon-plus").click(function(){
		var $status = $(".info-form-1 fieldset").attr("disabled");
		
		if (typeof($status) == "undefined") {
			var $num = parseInt($(".info-form-1 .num-box").val());
	
			$num = $num + 1;
			$(this).prev("input").val($num);	
		}
	});
	
	$(".info-form-2 .input-box").click(function(){
		$(".info-form-1 .input-box").attr("checked",false);
		$(".info-form-2 .input-box").attr("checked",true);
		
		$(".info-form-1 #add").attr("disabled",true);
		$(".info-form-1 #reset").attr("disabled",true);
		$(".info-form-1 #cancel").attr("disabled",true);
		
		$(".info-form-2 #add").attr("disabled",false);
		$(".info-form-2 #reset").attr("disabled",false);
		$(".info-form-2 #cancel").attr("disabled",false);
		
		$(".info-form-1 fieldset").attr("disabled",true);
		$(".info-form-2 fieldset").attr("disabled",false);
	});

	$(".info-form-2 .icon-minus").click(function(){
		var $status = $(".info-form-2 fieldset").attr("disabled");
		
		if (typeof($status) == "undefined") {
			var $num = parseInt($(".info-form-2 .num-box").val());
	
			if ($num <= 0) {
				// 数量不能少于0
			}
			else {
				$num = $num - 1;
				$(this).next("input").val($num);
			}
		}
	});

	$(".info-form-2 .icon-plus").click(function(){
		var $status = $(".info-form-2 fieldset").attr("disabled");
		
		if (typeof($status) == "undefined") {
			var $num = parseInt($(".info-form-2 .num-box").val());
	
			$num = $num + 1;
			$(this).prev("input").val($num);	
		}
	});
	
	$(".info-form-1 .btn-success").click(function(){
		var $bookId = $(".info-form-1 #bookId").val();
		var $num = $(".info-form-1 #num").val();
		
		var info = {
			bookId:	$bookId,
			num:$num
		};
		
		$.ajax({
			type:"POST",
			url:"../../../pandastore/book/addAnExistingBook.do",
			data:info,
			success:function(json){
					alert(json.message);
			}
		});
	});
	
	$(".info-form-2 .btn-success").click(function(){
		var $bookName = $(".info-form-2 #book-name").val();
		var $author = $(".info-form-2 #author").val();
		var $press = $(".info-form-2 #publisher").val();
		var $version = $(".info-form-2 #version").val();
		var $publicationYear = $(".info-form-2 #publish-date").val();
		var $introduction = $(".info-form-2 #introduction").val();
		var $bookType = $(".info-form-2 #book-type").val();
		var $price = $(".info-form-2 #price").val();
		var $categoryId = $(".info-form-2 #book-category").val();
		var $location = $(".info-form-2 #location").val();
		var $num = $(".info-form-2 #num").val();

		var info = {
			bookName:$bookName,
			author:$author,
			press:$press,
			version:$version,
			publicationYear:$publicationYear,
			introduction:$introduction,
			bookType:$bookType,
			price:$price,
			categoryId:$categoryId,
			location:$location,
			num:$num
		};
		
		$.ajax({
			type:"POST",
			url:"../../../pandastore/book/addABook.do",
			data:info,
			success:function(json){				
					alert(json.message);
			}
		});
	});
	
	$(".btn-warning").click(function(){
		location.reload(true);
	});

	$(".btn-danger").click(function(){
		location.reload(true);
	});
	
});