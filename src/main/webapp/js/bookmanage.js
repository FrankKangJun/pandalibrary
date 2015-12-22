$(document).ready(function(){
	window.detailImageOperateEvents={
		'click .edit' : function(e, value, row, index){
			$.ajax({
				type:"POST",
				url:"../../../pandastore/book/selectAllBook.do",
			    dataType:"json",
				success:function(json){
					if (json.status != 'failure') {
						var $books = json.book;
						var $bookId = $books[index].bookId;
						
						var info = {
							bookId:$bookId
						};

						$.ajax({
							type:"POST",
							url:"../../../pandastore/book/getBookById.do",
							data:info,
							success:function(bookdetail){
								if (bookdetail.status == "success") {
									var $book = bookdetail.book;
						
									$(".revise-area #book-name").val($book.bookName);
									$(".revise-area #author").val($book.author);
									$(".revise-area #press").val($book.press);
									$(".revise-area #version").val($book.version);
									$(".revise-area #publish-date").val($book.publicationYear);
									$(".revise-area #introduction").val($book.introduction);
									$(".revise-area #book-type").val($book.bookType);
									$(".revise-area #price").val($book.price);
									$(".revise-area #book-category").val($book.categoryId);
									$(".revise-area #location").val($book.location);
									
									$(".book-id-none").val($book.bookId);
								}
								else {
									alert(bookdetail.message);
								}
							}
						});
					}
					else {                                           
						alert(json.message);
					}
				}
			});

		},
		
		'click .delete' : function(e, value, row, index){
			$.ajax({
				type:"POST",
				url:"../../../pandastore/book/selectAllBook.do",
			    dataType:"json",
				success:function(json){
					if (json.status != 'failure') {
						var $books = json.book;
						var $bookId = $books[index].bookId;
						
						var info = {
							bookId:$bookId	
						};

						$.ajax({
							type:"POST",
							url:"../../../pandastore/book/getBooksById.do",
							data:info,
							success:function(result){
								if (result.status == "success") {
									var $books = result.books;
									
									var $select = $("#delete-rank");

									for (i = 0;i < $books.length;i++) {
										$select.append("<option>"+$books[i].rank+"</option>");
									}
								}
								else {
									alert(result.message);
								}
							}
						});
					}
					else {
						alert(json.message);
					}
				}
			});
		}
		
	};
	
	$(".btn-revise").click(function(){
		var $bookId = $(".book-id-none").val();
		var $bookName = $(".revise-area #book-name").val();
		var $author = $(".revise-area #author").val();
		var $press = $(".revise-area #press").val();
		var $version = $(".revise-area #version").val();
		var $publicationYear = $(".revise-area #publish-date").val();
		var $introduction = $(".revise-area #introduction").val();
		var $bookType = $(".revise-area #book-type").val();
		var $price = $(".revise-area #price").val();
		var $categoryId = $(".revise-area #book-category").val();
		var $location = $(".revise-area #location").val();

		var info = {
			bookId:$bookId,
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
			imgUrl:""
		};
		
		$.ajax({
			type:"POST",
			url:"../../../pandastore/book/updateABook.do",
			data:info,
			success:function(json) {
				if (json.status == "success") {
					location.reload(true);
				}
				else {
					alert(json.message);
				}
			}
		});
		
	});
	
	$(".btn-delete").click(function(){
		var $rank = $("#delete-rank").val();
		
		var info = {
			rank:$rank	
		};

		$.ajax({
			type:"POST",
			url:"../../../pandastore/book/deleteABook.do",
			data:info,
			success:function(json) {
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

function detailImageManageFormatter(value, row, index){
	return [
		'<button class="edit btn-revise btn btn-info" data-target="#manageDetailImageModal" data-toggle="modal" title="edit">',
		'修改',
		'</button>',
		'<button class="delete btn-delete btn btn-danger" data-target="#manageDeleteModal" data-toggle="modal" title="delete">',
		'删除',
		'</button>'
	].join('');
}